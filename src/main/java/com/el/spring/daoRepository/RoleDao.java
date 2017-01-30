package com.el.spring.daoRepository;

import com.el.spring.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Integer>{
}
