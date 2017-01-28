package com.el.spring.daoRepository;

import com.el.spring.entity.Comentary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentaryDao extends JpaRepository<Comentary, Integer> {
    Comentary findById(int id);
}
