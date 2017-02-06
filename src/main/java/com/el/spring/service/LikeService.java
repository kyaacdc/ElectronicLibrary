package com.el.spring.service;

import com.el.spring.entity.Book;
import com.el.spring.entity.Dislike;
import com.el.spring.entity.Like;
import com.el.spring.entity.User;

import java.util.List;

public interface LikeService {

    void saveLike(int islike, Book book, User user, int amount);

    Like getLikeById(int id);

    List<Like> getLikesByBook(Book book);

    List<Dislike> getDislikesByBook(Book book);

    List<Like> getLikesByUser(User user);

    int getLikesByBookAndUser(Book book, User user);

    List<Like> listLikes();

}
