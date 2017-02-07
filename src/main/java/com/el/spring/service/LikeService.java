package com.el.spring.service;

import com.el.spring.entity.Book;
import com.el.spring.entity.Dislike;
import com.el.spring.entity.Like;
import com.el.spring.entity.User;

import java.util.List;

public interface LikeService {

    void saveLike(boolean islike, Book book, User user, int amount);

    List<Like> getLikesByBook(Book book);

    List<Dislike> getDislikesByBook(Book book);

    List<Like> listLikes();

    List<Dislike> listDislikes();

    void removeLike(int id);

    void removeDislike(int id);

}
