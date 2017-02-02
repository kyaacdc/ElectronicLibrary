package com.el.spring.daoRepository;

import com.el.spring.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Integer> {
    Comment findCommentById(int id);
    Comment findCommentByDescription(String description);
}
