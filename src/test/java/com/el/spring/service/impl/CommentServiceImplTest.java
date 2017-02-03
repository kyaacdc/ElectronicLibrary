package com.el.spring.service.impl;

import com.el.spring.entity.Comment;
import com.el.spring.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/appconfig-root.xml")
public class CommentServiceImplTest {

    @Resource
    private CommentService commentService;

    @Test
    public void addComment() throws Exception {
        Comment comment = new Comment(1, 1,"comment111");
        commentService.addComment(comment);
        comment = commentService.getCommentByDescription("comment111");

        assertThat(comment.getDescription(), is(equalTo("comment111")));

        commentService.removeComment(comment.getId());
    }

    @Test
    public void updateComment() throws Exception {
        Comment comment = new Comment();
        comment.setDescription("dscrip1");
        commentService.addComment(comment);
        int id = commentService.getCommentByDescription("dscrip1").getId();
        comment = new Comment();
        comment.setDescription("dscrip2");
        comment.setId(id);
        commentService.updateComment(comment);
        comment = commentService.getCommentByDescription("dscrip2");

        assertTrue(comment.getId() == id);

        commentService.removeComment(id);
    }

    @Test
    public void removeComment() throws Exception {
        Comment comment = new Comment();
        comment.setDescription("dscrip1");
        commentService.addComment(comment);
        int id = commentService.getCommentByDescription("dscrip1").getId();
        commentService.removeComment(id);

        assertThat(commentService.getCommentById(id), is(nullValue()));
    }

    @Test
    public void getCommentById() throws Exception {
        Comment comment = new Comment();
        comment.setDescription("dscrip1");
        commentService.addComment(comment);
        int id = commentService.getCommentByDescription("dscrip1").getId();

        assertTrue(commentService.getCommentById(id).getDescription().equals("dscrip1"));

        commentService.removeComment(id);
    }

    @Test
    public void listCommentsByBookReversed() throws Exception {
        Comment comment = new Comment();
        comment.setDescription("dscrip1");
        comment.setBookId(1);
        commentService.addComment(comment);

        comment = new Comment();
        comment.setDescription("dscrip2");
        comment.setBookId(1);
        commentService.addComment(comment);

        List<Comment> comments = commentService.listCommentsByBookReversed(1);

        assertThat(comments.get(0).getDescription(), is(isOneOf("dscrip1", "dscrip2")));

        commentService.removeComment(commentService.getCommentByDescription("dscrip1").getId());
        commentService.removeComment(commentService.getCommentByDescription("dscrip2").getId());
    }

    @Test
    public void listComments() throws Exception {
        Comment comment = new Comment();
        comment.setDescription("dscrip1");
        commentService.addComment(comment);
        int id = commentService.getCommentByDescription("dscrip1").getId();

        assertThat(commentService.listComments(), is(notNullValue()));

        commentService.removeComment(id);
    }
}