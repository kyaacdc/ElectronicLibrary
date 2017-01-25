package com.el.dao.CRUD.interfaces;

import com.el.dao.CRUD.GenericInterface;
import com.el.dao.entity.Comment;
import com.el.dao.entity.Tag;

public interface CommentService extends GenericInterface<Comment, Integer>{
    Comment findByName(String name);
}
