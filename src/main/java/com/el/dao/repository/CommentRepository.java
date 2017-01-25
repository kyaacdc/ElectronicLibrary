package com.el.dao.repository;

import com.el.dao.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    Comment findByName(String name);

}
