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

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<Like> getLikesByBook(Book book) {
        List<Like> likesByBookId = likeDao.findLikesByBookId(book.getId());
        Collections.reverse(likesByBookId);
        return likesByBookId;
    }

    @Override
    public List<Dislike> getDislikesByBook(Book book) {
        List<Dislike> dislikesByBookId = dislikeDao.findDislikesByBookId(book.getId());
        Collections.reverse(dislikesByBookId);
        return dislikesByBookId;
    }
}
