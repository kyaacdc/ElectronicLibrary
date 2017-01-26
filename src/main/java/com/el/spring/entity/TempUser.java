package com.el.spring.entity;

public class TempUser {

    private String login;
    private String password;

    public TempUser(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public TempUser() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
