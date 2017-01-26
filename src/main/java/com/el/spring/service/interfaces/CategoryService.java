package com.el.spring.service.interfaces;

import com.el.spring.entity.Category;
import com.el.spring.service.GenericInterface;

public interface CategoryService extends GenericInterface<Category,Integer>{
    Category getByName(String name);
}
