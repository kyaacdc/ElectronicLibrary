package com.el.spring.daoRepository;

import com.el.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findUserById(int id);
}
