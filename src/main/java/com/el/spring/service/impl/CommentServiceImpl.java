package com.el.spring.service.impl;

import com.el.spring.daoRepository.CommentDao;
import com.el.spring.entity.Comment;
import com.el.spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    @Transactional
    public void addComment(Comment comment) {
        if(commentDao.findOne(comment.getId()) == null)
            commentDao.save(comment);
        else
            throw new NoSuchElementException();
    }

    @Override
    @Transactional
    public void updateComment(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    @Transactional
    public void removeComment(int id) {
        Comment comment = commentDao.findOne(id);
        if(comment!=null){
            commentDao.delete(comment);
        }
    }

    @Override
    public Comment getCommentById(int id) {
        return commentDao.findCommentById(id);
    }

    @Override
    public Comment getCommentByDescription(String description) {
        return commentDao.findCommentByDescription(description);
    }

    @Override
    public List<Comment> listCommentsByBookReversed(int id) {

        List<Comment> list = listComments().stream()
                .filter(a -> a.getBookId() == id)
                .collect(Collectors.toList());

        Collections.reverse(list);

        return list;
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Comment> listComments() {
        return commentDao.findAll();
    }
}
