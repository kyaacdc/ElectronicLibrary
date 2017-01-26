package com.el.spring.service.interfaces;

import com.el.spring.service.GenericInterface;
import com.el.spring.entity.User;

public interface UserService extends GenericInterface<User, String>{
    User getByName(String name);
    User getByRole(boolean isAdmin);
}
