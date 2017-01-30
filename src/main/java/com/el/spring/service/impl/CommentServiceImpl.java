package com.el.spring.service.impl;

import com.el.spring.daoRepository.CommentDao;
import com.el.spring.entity.Comment;
import com.el.spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

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
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Comment> listComments() {
        return commentDao.findAll();
    }
}
