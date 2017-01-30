package com.el.spring.service.impl;


import com.el.spring.daoRepository.RoleDao;
import com.el.spring.daoRepository.UserDao;
import com.el.spring.entity.Role;
import com.el.spring.entity.User;
import com.el.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDao.getOne(1));
        user.setRoles(roles);
        userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        return userDao.findAll();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        save(user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        User user = userDao.findUserById(id);
        if(user!=null){
            userDao.delete(user);
        }
    }

    @Override
    public User getUserById(int id) {
        return userDao.findUserById(id);
    }
}

