/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.myProject.model;

/**
 *
 * @author Nina
 */
public class Account {
    private int id;
    private String userName;
    private String password;
    private boolean admin;

    public Account() {
    }

    public Account(String userName, String password, boolean role) {
        this.userName = userName;
        this.password = password;
        this.admin = role;
    }

    public Account(int id, String userName, String password, boolean role) {
        this(userName, password, role);
        this.id = id;
    }    

    public Account(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }    
    
    public Account(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }
    
}
