package com.el.spring.service;

import com.el.spring.entity.Comment;
import java.util.List;

public interface CommentService {

    void addComment(Comment comment);

    void updateComment(Comment comment);

    void removeComment(int id);

    Comment getCommentById(int id);

    Comment getCommentByDescription(String description);

    List<Comment> listCommentsByBookReversed(int id);

    List<Comment> listComments();

}
