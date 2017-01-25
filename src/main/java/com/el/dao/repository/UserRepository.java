package com.el.dao.repository;

import com.el.dao.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByName(String name);
    User findByIsAdmin(boolean isAdmin);
}
