package com.el.spring.daoRepository;

import com.el.spring.entity.Dislike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DislikeDao extends JpaRepository<Dislike, Integer> {
}
