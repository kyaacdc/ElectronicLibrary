package com.el.dao.CRUD.implementations;

import com.el.dao.CRUD.interfaces.UserService;
import com.el.dao.entity.User;
import com.el.dao.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addItem(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(String s) {
        userRepository.delete(s);
    }

    @Override
    public User editItem(User user) {
        return addItem(user);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public User find(String s) {
        return userRepository.findOne(s);
    }

    @Override
    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User getByRole(boolean isAdmin) {
        return userRepository.findByIsAdmin(isAdmin);
    }
}
