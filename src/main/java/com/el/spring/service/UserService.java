package com.el.spring.service;

import com.el.spring.entity.User;

public interface UserService{

    void save(User user);

    User findByUsername(String username);
}
