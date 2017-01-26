package com.el.spring.service.implementations;

import com.el.spring.service.interfaces.TagService;
import com.el.spring.entity.Tag;
import com.el.spring.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TagServiceImpl implements TagService{

    private final TagRepository tagRepository;

    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Tag addItem(Tag tag) {
        return tagRepository.saveAndFlush(tag);
    }

    @Override
    public void delete(Integer id) {
        tagRepository.delete(id);
    }

    @Override
    public Tag editItem(Tag tag) {
        return addItem(tag);
    }

    @Override
    public List<Tag> getAll() {
        return tagRepository.findAll();
    }

    @Override
    public void deleteAll() {
        tagRepository.deleteAll();
    }

    @Override
    public Tag find(Integer id) {
        return tagRepository.findOne(id);
    }

    @Override
    public Tag getByName(String name) {
        return tagRepository.findByName(name);
    }
}
