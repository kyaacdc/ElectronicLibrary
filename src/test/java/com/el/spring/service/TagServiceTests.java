package com.el.spring.service;

import com.el.spring.entity.Tag;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/appconfig-root.xml")
public class TagServiceTests {

    @Resource
    private TagService tagService;

    @Test
    public void testSaveTag() throws Exception {
        tagService.addTag(new Tag("tag1", 1));
    }

    @Test
    public void testEditTag() throws Exception {
        tagService.addTag(new Tag("tag1", 1));
        tagService.updateTag(new Tag("tag11", 1));
        tagService.removeTag(1);
    }

    @Test
    public void testDeleteTag() throws Exception {
        tagService.addTag(new Tag("tag1", 2));
        tagService.removeTag(2);
    }

    @Test
    public void testGetAllTags() throws Exception {
        tagService.addTag(new Tag("tag1", 1));
        tagService.addTag(new Tag("tag2", 2));
        tagService.addTag(new Tag("tag3", 3));

        List<Tag> list = tagService.listTags();
        list.forEach(System.out::println);

        list.forEach(a -> tagService.removeTag(a.getId()));
    }

    @Test
    public void testGetTagById() throws Exception {
        tagService.addTag(new Tag("tag1", 1));
        tagService.addTag(new Tag("tag2", 2));
        tagService.addTag(new Tag("tag3", 3));

        tagService.getTagById(2);

        tagService.listTags().forEach(a -> tagService.removeTag(a.getId()));

    }
}

