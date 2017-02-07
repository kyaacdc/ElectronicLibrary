package com.el.spring.daoRepository;

import com.el.spring.entity.Dislike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DislikeDao extends JpaRepository<Dislike, Integer> {
    List<Dislike> findDislikesByBookId(int bookId);
}
