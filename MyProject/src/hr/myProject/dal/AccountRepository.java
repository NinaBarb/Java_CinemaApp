/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myProject.dal;

import hr.myProject.model.Account;
import java.util.Optional;

/**
 *
 * @author Nina
 */
public interface AccountRepository {
    int createAccount(Account account) throws Exception;
    Optional<Account> selectAccount(String id) throws Exception;
    boolean doesAccountExist(String id) throws Exception;
}
