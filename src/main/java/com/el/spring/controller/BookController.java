package com.el.spring.controller;

import com.el.spring.entity.Book;
import com.el.spring.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.NestedServletException;

import javax.validation.ConstraintViolationException;
import java.util.NoSuchElementException;

import static com.el.spring.service.impl.enums.EnumBookSort.getCriteriaByKey;
import static com.el.spring.service.impl.enums.EnumFindCriteria.*;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private TagService tagService;

    @Autowired
    private LikeService likeService;

    //Operations with Books

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String listBooks(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", bookService.listBooks());
        model.addAttribute("listTags", tagService.listTags());
        return "books";
    }

    @RequestMapping(value = "maincontent", method = RequestMethod.GET)
    public String listBooksForUsers(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", bookService.listBooks());
        return "maincontent";
    }

    @RequestMapping(value = "/books/add", method = RequestMethod.POST)
    public String addBook(@ModelAttribute("book") Book book){
        try {
            if (book.getId() == 0) {
                bookService.addBook(book);
            } else {
                bookService.updateBook(book);
            }
        }
        catch (TransactionSystemException | ConstraintViolationException e) {
            return "errorinput";
        }

        return "redirect:/books";
    }

    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id){
        bookService.removeBook(id);

        return "redirect:/books";
    }

    @RequestMapping("edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("listBooks", bookService.listBooks());

        return "books";
    }

    @RequestMapping("bookdata/{id}")
    public String bookData(@PathVariable("id") int id, Model model){
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("listComments", commentService.listCommentsByBookReversed(id));
        model.addAttribute("listUsers", userService.listUsers());

        return "/bookdata";
    }

    @RequestMapping("/findBooks")
    public String findBooks(@RequestParam("findOption") int findOption,
                            @RequestParam("searchValue") String searchValue,
                            Model model)
    {
        if(searchValue.equals(""))
            return "/notfound";

        try {
            switch (findOption) {
                case 1: {
                    model.addAttribute("book", bookService.getExactlyBookByTitle(searchValue));
                    return "bookdata";
                }
                case 2: {
                    model.addAttribute("listBooks", bookService.listBookByCriteria(searchValue, TITLE));
                    return "maincontent";
                }
                case 3: {
                    model.addAttribute("listBooks", bookService.listBookByCriteria(searchValue, DESCR));
                    return "maincontent";
                }
                default: throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            return "/notfound";
        }
    }

    @RequestMapping("/bookSortByCriteria")
    public String bookSortByCriteria(@RequestParam("criteria") int criteria, Model model){

        model.addAttribute("listBooks", bookService.listSortedBooks(getCriteriaByKey(criteria)));

        return "maincontent";
    }

    @RequestMapping("/changeRate")
    public String changeRate(@RequestParam("id") int id,
                             @RequestParam("islike") int islike,
                             @RequestParam("username") String username,
                             @RequestParam("setRate") int setRate,
                             Model model)
    {
        boolean isLike = (islike == 1);
        bookService.changeRate(id, islike, setRate);
        likeService.saveLike(isLike, bookService.getBookById(id), userService.findByUsername(username), setRate);
        model.addAttribute("listComments", commentService.listCommentsByBookReversed(id));
        return bookData(id, model);
    }

    @RequestMapping("/showbookrate")
    public String changeRate(@RequestParam("bookId") int bookId,
                             Model model)
    {
        model.addAttribute("book", bookService.getBookById(bookId));
        model.addAttribute("listLikes", likeService.getLikesByBook(bookService.getBookById(bookId)));
        model.addAttribute("listDislikes", likeService.getDislikesByBook(bookService.getBookById(bookId)));
        model.addAttribute("listUsers", userService.listUsers());
        return "bookrate";
    }


    @RequestMapping("/removeAllBooks")
    public String removeAllBooks(@RequestParam("isRemoveAllBooks") String isRemoveAllBooks, Model model){

        if(isRemoveAllBooks.equalsIgnoreCase("yes"))
            bookService.removeAllBooks();
        model.addAttribute("listBooks", bookService.listBooks());

        return "redirect:/books";
    }
}
