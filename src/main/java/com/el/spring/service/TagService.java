package com.el.spring.service;

import com.el.spring.entity.Tag;
import java.util.List;

public interface TagService {
    void addTag(Tag tag);

    void updateTag(Tag tag);

    void removeTag(int id);

    Tag getTagById(int id);


    List<Tag> listTags();
}