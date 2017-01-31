package com.el.spring.controller;

import com.el.spring.entity.Comment;
import com.el.spring.service.BookService;
import com.el.spring.service.CommentService;
import com.el.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    //Operations with Comment

    @RequestMapping(value = "comments", method = RequestMethod.GET)
    public String listComments(Model model) {
        model.addAttribute("comment", new Comment());
        model.addAttribute("listComments", commentService.listComments());
        model.addAttribute("listUsers", userService.listUsers());
        model.addAttribute("listBooks", bookService.listBooks());

        return "comments";
    }

    @RequestMapping(value = "/comments/add", method = RequestMethod.POST)
    public String addComment(@ModelAttribute("comment") Comment comment) {
        if (comment.getId() == 0) {
            commentService.addComment(comment);
        } else {
            commentService.updateComment(comment);
        }

        return "redirect:/comments";
    }

    @RequestMapping("/comments/remove/{id}")
    public String removeComment(@PathVariable("id") int id) {
        commentService.removeComment(id);

        return "redirect:/comments";
    }

    @RequestMapping("/comments/edit/{id}")
    public String editComment(@PathVariable("id") int id, Model model) {
        model.addAttribute("comment", commentService.getCommentById(id));
        model.addAttribute("listComments", commentService.listComments());

        return "comments";
    }

    public String bookData(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("listComments", commentService.listCommentsByBookReversed(id));
        model.addAttribute("listUsers", userService.listUsers());

        return "/bookdata";
    }

    @RequestMapping(value = "/addComment", method = RequestMethod.GET)
    public String addCommentToBookdataPage(@RequestParam("bookId") int bookId,
                                           @RequestParam("username") String username,
                                           @RequestParam("descr") String descr,
                                           Model model)
    {
        int userId = userService.findByUsername(username).getId();
        Comment comment = new Comment();
        comment.setUserId(userId);
        comment.setBookId(bookId);
        comment.setDescription(descr);

        commentService.addComment(comment);

        return bookData(bookId, model);
    }
}
