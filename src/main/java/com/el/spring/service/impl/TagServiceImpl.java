package com.el.spring.service.impl;

import com.el.spring.daoRepository.TagDao;
import com.el.spring.entity.Tag;
import com.el.spring.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TagServiceImpl implements TagService{

    @Autowired
    private TagDao tagDao;

    @Override
    @Transactional
    public Tag addTag(Tag tag) {
        if(tagDao.findOne(tag.getId()) == null)
            tagDao.save(tag);
        else
            throw new NoSuchElementException();
        return tagDao.getOne(tagDao.findByTagname(tag.getTagname()).getId());
    }

    @Override
    @Transactional
    public void updateTag(Tag tag) {
        tagDao.save(tag);
    }

    @Override
    @Transactional
    public void removeTag(int id) {
        Tag tag = tagDao.findOne(id);
        if(tag!=null){
            tagDao.delete(tag);
        }
    }

    @Override
    public Tag getTagById(int id) {
        return tagDao.findOne(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.findByTagname(name);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public List<Tag> listTags() {
        return tagDao.findAll();
    }
}
