package com.el.spring.repository;

import com.el.spring.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByName(String name);
    User findByIsAdmin(boolean isAdmin);
}
