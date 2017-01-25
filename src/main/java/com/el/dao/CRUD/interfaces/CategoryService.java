package com.el.dao.CRUD.interfaces;

import com.el.dao.entity.Category;
import com.el.dao.CRUD.GenericInterface;

public interface CategoryService extends GenericInterface<Category,Integer>{
    Category getByName(String name);
}
