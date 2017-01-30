package com.el.spring.daoRepository;

import com.el.spring.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagDao extends JpaRepository<Tag, Integer>{
    Tag findTagById(int id);
    Tag findByTagname(String tagname);
}
