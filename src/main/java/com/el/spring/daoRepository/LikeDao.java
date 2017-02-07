package com.el.spring.daoRepository;

import com.el.spring.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeDao extends JpaRepository<Like, Integer>{
    List<Like> findLikesByBookId(int bookId);
}
