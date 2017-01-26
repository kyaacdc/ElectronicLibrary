package com.el.spring.service.interfaces;

import com.el.spring.service.GenericInterface;
import com.el.spring.entity.Comment;

public interface CommentService extends GenericInterface<Comment, Integer>{
    Comment findByName(String name);
}
