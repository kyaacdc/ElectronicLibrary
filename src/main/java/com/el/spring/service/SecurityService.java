package com.el.spring.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autiLogin(String username, String password);

}
