package com.el.spring.entity;

import com.el.spring.entity.enums.UserRoleEnum;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserRole {

    @Id
    private int id;

    @ManyToMany(mappedBy = "userRoles")
    private Set<User> userSet = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private UserRoleEnum userRoleEnum;

    public UserRole() {
    }

    public UserRole(int id, Set<User> userSet, UserRoleEnum userRoleEnum) {
        this.id = id;
        this.userSet = userSet;
        this.userRoleEnum = userRoleEnum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public UserRoleEnum getUserRoleEnum() {
        return userRoleEnum;
    }

    public void setUserRoleEnum(UserRoleEnum userRoleEnum) {
        this.userRoleEnum = userRoleEnum;
    }
}
