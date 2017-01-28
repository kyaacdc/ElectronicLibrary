package com.el.spring.service.impl;

import com.el.spring.daoRepository.TagDao;
import com.el.spring.entity.Tag;
import com.el.spring.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.NoSuchElementException;

public class TagServiceImpl implements TagService{

    @Autowired
    TagDao tagDao;

    @Override
    public void addTag(Tag tag) {
        if(tagDao.findOne(tag.getId()) == null)
            tagDao.save(tag);
        else
            throw new NoSuchElementException();
    }

    @Override
    public Tag getTagById(int id) {
        return tagDao.findById(id);
    }

    @Override
    public List<Tag> getTags() {
        return tagDao.findAll();
    }
}
