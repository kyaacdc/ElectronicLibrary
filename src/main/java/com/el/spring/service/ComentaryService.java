package com.el.spring.service;

import com.el.spring.entity.Comentary;
import java.util.List;

public interface ComentaryService {

    void addComentary(Comentary comentary);

    List<Comentary> getSetComentaryByBookId(int id);
}
