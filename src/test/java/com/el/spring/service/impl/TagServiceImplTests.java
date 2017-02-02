package com.el.spring.service.impl;

import com.el.spring.entity.Tag;
import com.el.spring.service.TagService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/appconfig-root.xml")
public class TagServiceImplTests {

    @Resource
    private TagService tagService;

    @Test
    public void testSaveTag() throws Exception {
        Tag tag = new Tag("tag1", 1);
        tagService.addTag(tag);
        tag = tagService.getTagByName("tag1");

        assertThat(tag.getTagname(), is(equalTo("tag1")));

        tagService.removeTag(tag.getId());
    }

    @Test
    public void testEditTag() throws Exception {
        Tag tag = new Tag("tag1", 1);
        tagService.addTag(tag);
        int id = tagService.getTagByName("tag1").getId();
        tag = new Tag("tag2", 2);
        tag.setId(id);
        tagService.updateTag(tag);
        tag = tagService.getTagByName("tag2");

        assertTrue(tag.getId() == id);

        tagService.removeTag(id);
    }

    @Test
    public void testDeleteTag() throws Exception {
        Tag tag = new Tag("tag1", 1);
        tagService.addTag(tag);
        int id = tagService.getTagByName("tag1").getId();
        tagService.removeTag(id);
        assertThat(tagService.getTagById(id), is(nullValue()));
    }

    @Test
    public void testGetAllTags() throws Exception {
        Tag tag = new Tag("tag1", 1);
        tagService.addTag(tag);

        assertThat(tagService.listTags(), is(not(nullValue())));

        int id = tagService.getTagByName("tag1").getId();
        tagService.removeTag(id);
    }

    @Test
    public void testGetTagById() throws Exception {
        Tag tag = new Tag("tag1", 1);
        tagService.addTag(tag);
        int id = tagService.getTagByName("tag1").getId();

        assertTrue(tagService.getTagById(id).getTagname().equals("tag1"));

        tagService.removeTag(id);
    }

    @Test
    public void testGetTagByName() throws Exception {
        Tag tag = new Tag("tag1", 1);
        tagService.addTag(tag);
        tag = tagService.getTagByName("tag1");
        assertTrue(tag.getTagname().equals("tag1"));

        tagService.removeTag(tag.getId());
    }
}

