package com.el.dao.CRUD.interfaces;

import com.el.dao.CRUD.GenericInterface;
import com.el.dao.entity.Tag;

public interface TagService extends GenericInterface<Tag, Integer>{
    Tag getByName(String name);
}
