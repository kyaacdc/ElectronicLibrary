package com.el.dao.CRUD.interfaces;

import com.el.dao.CRUD.GenericInterface;
import com.el.dao.entity.User;

public interface UserService extends GenericInterface<User, String>{
    User getByName(String name);
    User getByRole(boolean isAdmin);
}
