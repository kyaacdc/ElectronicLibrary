package com.el.spring.service.impl;

import com.el.spring.daoRepository.DislikeDao;
import com.el.spring.daoRepository.LikeDao;
import com.el.spring.entity.Book;
import com.el.spring.entity.Dislike;
import com.el.spring.entity.Like;
import com.el.spring.entity.User;
import com.el.spring.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    private LikeDao likeDao;

    @Autowired
    private DislikeDao dislikeDao;

    @Override
    @Transactional
    public void saveLike(int isLike, Book book, User user, int amount) {
        if(isLike == 1) {
            Optional<Like> likeOptional = likeDao.findAll().stream()
                    .filter(a -> a.getBookId() == book.getId() && a.getUserId() == user.getId())
                    .distinct().findFirst();
            if (likeOptional.isPresent()) {
                Like like = likeOptional.get();
                like.setAmount(like.getAmount() + amount);
                likeDao.save(like);
            } else
                likeDao.save(new Like(book.getId(), user.getId(), amount));
        }
        else {
            Optional<Dislike> dislikeOptional = dislikeDao.findAll().stream()
                    .filter(a -> a.getBookId() == book.getId() && a.getUserId() == user.getId())
                    .distinct().findFirst();
            if (dislikeOptional.isPresent()) {
                Dislike dislike = dislikeOptional.get();
                dislike.setAmount(dislike.getAmount() + amount);
                dislikeDao.save(dislike);
            } else
                dislikeDao.save(new Dislike(book.getId(), user.getId(), amount));
        }
    }

    @Override
    public Like getLikeById(int id) {
        return likeDao.findOne(id);
    }

    @Override
    public List<Like> getLikesByBook(Book book) {
        return likeDao.findLikesByBookId(book.getId());
    }

    @Override
    public List<Dislike> getDislikesByBook(Book book) {
        return dislikeDao.findDislikesByBookId(book.getId());
    }

    @Override
    public List<Like> getLikesByUser(User user) {
        return likeDao.findLikeByUserId(user.getId());
    }

    @Override
    public int getLikesByBookAndUser(Book book, User user) {
        return likeDao.findAll().stream()
                .filter(a -> a.getBookId() == book.getId() && a.getUserId() == user.getId())
                .distinct().findFirst().map(Like::getAmount).orElse(0);
    }

    @Override
    public List<Like> listLikes() {
        return likeDao.findAll();
    }
}
