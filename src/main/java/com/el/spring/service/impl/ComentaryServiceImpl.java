package com.el.spring.service.impl;

import com.el.spring.daoRepository.ComentaryDao;
import com.el.spring.entity.Comentary;
import com.el.spring.service.ComentaryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ComentaryServiceImpl implements ComentaryService{

    @Autowired
    ComentaryDao comentaryDao;

    @Override
    public void addComentary(Comentary comentary) {
        if(comentaryDao.findOne(comentary.getId()) == null)
            comentaryDao.save(comentary);
        else
            throw new NoSuchElementException();
    }

    @Override
    public List<Comentary> getSetComentaryByBookId(int id) {
        return comentaryDao.findAll().stream()
                .filter(a -> a.getBookId() == id)
                .collect(Collectors.toList());
    }
}
