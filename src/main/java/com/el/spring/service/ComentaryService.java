package com.el.spring.service;

import com.el.spring.entity.Comentary;

import java.util.List;
import java.util.Set;

public interface ComentaryService {

    void addComentary(Comentary comentary);

    List<Comentary> getSetComentaryByBookId(int id);
}
