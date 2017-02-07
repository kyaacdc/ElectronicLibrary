package com.el.spring.controller;

import com.el.spring.entity.Book;
import com.el.spring.entity.Dislike;
import com.el.spring.entity.Like;
import com.el.spring.entity.User;
import com.el.spring.service.BookService;
import com.el.spring.service.LikeService;
import com.el.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@Controller
public class RateController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "likes", method = RequestMethod.GET)
    public String listLikes(Model model) {
        model.addAttribute("like", new Like());
        model.addAttribute("dislike", new Dislike());
        model.addAttribute("listLikes", likeService.listLikes());
        model.addAttribute("listDislikes", likeService.listDislikes());
        model.addAttribute("listUsers", userService.listUsers());
        model.addAttribute("listBooks", bookService.listBooks());

        return "likes";
    }

    @RequestMapping("/likes/remove/{id}")
    public String removeLike(@PathVariable("id") int id,
                             @RequestParam("bookId") int bookId,
                             @RequestParam("userId") int userId,
                             @RequestParam("islike") int islike) {

        boolean isLike = (islike == 1);
        Book book = bookService.getBookById(bookId);

        if(isLike) {
            List<Like> likesByBook = likeService.getLikesByBook(book);
            Optional<Like> likeOptional = likesByBook.stream().filter(a -> a.getUserId() == userId).findFirst();
            likeOptional.ifPresent(like -> book.setLikes(book.getLikes() - like.getAmount()));
            likeService.removeLike(id);
        } else {
            List<Dislike> dislikesByBook = likeService.getDislikesByBook(book);
            Optional<Dislike> dislikeOptional = dislikesByBook.stream().filter(a -> a.getUserId() == userId).findFirst();
            dislikeOptional.ifPresent(dislike -> book.setDislikes(book.getDislikes() - dislike.getAmount()));
            likeService.removeDislike(id);
        }

        bookService.updateBook(book);

        return "redirect:/likes";
    }
}



