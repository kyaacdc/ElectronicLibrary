package com.el.spring.service;

import com.el.spring.entity.User;
import java.util.List;

public interface UserService{

    void save(User user);

    User findByUsername(String username);

    List<User> listUsers();

    void addUser(User user);

    void updateUser(User user);

    void removeUser(int id);

    User getUserById(int id);

}
