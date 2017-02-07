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

import java.util.Comparator;
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
    public void saveLike(boolean isLike, Book book, User user, int amount) {

        if(isLike) {
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
        return likeDao.findLikesByBookId(book.getId()).stream()
                .sorted(Comparator.comparing(Like::getAmount).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Dislike> getDislikesByBook(Book book) {
        return dislikeDao.findDislikesByBookId(book.getId()).stream()
                .sorted(Comparator.comparing(Dislike::getAmount).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Like> listLikes() {
        return likeDao.findAll();
    }

    @Override
    public List<Dislike> listDislikes() {
        return dislikeDao.findAll();
    }

    @Override
    public void removeLike(int id) {
        likeDao.delete(id);
    }

    @Override
    public void removeDislike(int id) {
        dislikeDao.delete(id);
    }
}
