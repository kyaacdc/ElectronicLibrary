package com.el.dao.CRUD.implementations;

import com.el.dao.CRUD.interfaces.CommentService;
import com.el.dao.entity.Comment;
import com.el.dao.entity.Tag;
import com.el.dao.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment addItem(Comment comment) {
        return commentRepository.saveAndFlush(comment);
    }

    @Override
    public void delete(Integer id) {
        commentRepository.delete(id);
    }

    @Override
    public Comment editItem(Comment comment) {
        return addItem(comment);
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteAll() {
        commentRepository.deleteAll();
    }

    @Override
    public Comment find(Integer id) {
        return commentRepository.getOne(id);
    }

    @Override
    public Comment findByName(String name) {
        return commentRepository.findByName(name);
    }
}
