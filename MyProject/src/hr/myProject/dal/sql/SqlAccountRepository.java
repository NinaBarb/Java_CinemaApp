/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myProject.dal.sql;

import hr.myProject.dal.AccountRepository;
import hr.myProject.model.Account;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Optional;
import javax.sql.DataSource;


public class SqlAccountRepository implements AccountRepository {

    private static final String ID_USER_ACCOUNT = "IDUserAccount";
    private static final String USERNAME = "Username";
    private static final String PASSWORD = "Pswd";
    private static final String IS_ADMIN = "IsAdmin";

    private static final String CREATE_ACCOUNT = "{ CALL CreateUserAccount (?,?,?,?) }";
    private static final String SELECT_ACCOUNT = "{ CALL SelectUserAccount (?) }";
    
    @Override
    public int createAccount(Account account) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(CREATE_ACCOUNT)) {

            stmt.setString(1, account.getUserName());
            stmt.setString(2, account.getPassword());
            stmt.setBoolean(3, account.isAdmin());
            stmt.registerOutParameter(4, Types.INTEGER);

            stmt.executeUpdate();
            return stmt.getInt(4);
        }
    }

    @Override
    public Optional<Account> selectAccount(String userName) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACCOUNT)) {

            stmt.setString(1, userName);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(new Account(
                            rs.getInt(ID_USER_ACCOUNT),
                            rs.getString(USERNAME),
                            rs.getString(PASSWORD),
                            rs.getBoolean(IS_ADMIN)));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean doesAccountExist(String userName) throws Exception {
        DataSource dataSource = DataSourceSingleton.getInstance();
        try (Connection con = dataSource.getConnection();
                CallableStatement stmt = con.prepareCall(SELECT_ACCOUNT)) {

            stmt.setString(1, userName);
            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return true;
                }
            }
        }
        return false;
    }
    
}
