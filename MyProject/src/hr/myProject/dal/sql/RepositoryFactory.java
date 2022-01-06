/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myProject.dal.sql;

/**
 *
 * @author Nina
 */
public class RepositoryFactory {

    public RepositoryFactory() {
    }
    
    public static SqlMovieRepository getSqlMovieRepository(){
        return new SqlMovieRepository();
    }
    
    public static SqlAccountRepository getSqlAccountRepository(){
        return new SqlAccountRepository();
    }
}
