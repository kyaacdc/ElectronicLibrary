package com.el.spring.service.interfaces;

import com.el.spring.service.GenericInterface;
import com.el.spring.entity.Tag;

public interface TagService extends GenericInterface<Tag, Integer>{
    Tag getByName(String name);
}
