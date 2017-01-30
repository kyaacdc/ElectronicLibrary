package com.el.spring.controller;

import com.el.spring.entity.Book;
import com.el.spring.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.NoSuchElementException;

import static com.el.spring.service.impl.enums.EnumFindCriteria.DESCR;
import static com.el.spring.service.impl.enums.EnumFindCriteria.TITLE;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    //Operations with Books

    @RequestMapping(value = "books", method = RequestMethod.GET)
    public String listBooks(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", bookService.listBooks());

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
        if(book.getId() == 0){
            bookService.addBook(book);
        }else {
            bookService.updateBook(book);
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

        return "/bookdata";
    }

    @RequestMapping("/bookfind")
    public String exactlyBookFindByTittle(@RequestParam("bookTitle") String bookTitle, Model model){
        try {
            model.addAttribute("book", bookService.getExactlyBookByTitle(bookTitle));
        } catch (NoSuchElementException e) {
            return "/notfound";
        }

        return "/bookfind";
    }

    @RequestMapping("/bookfindDescr")
    public String bookFindByDescr(@RequestParam("descr") String descr, Model model){
        try {
            model.addAttribute("book", new Book());
            model.addAttribute("listBooks", bookService.listBookByCriteria(descr, DESCR));
        } catch (NoSuchElementException e) {
            return "/notfound";
        }

        return "maincontent";
    }

    @RequestMapping("/bookfindTitle")
    public String bookFindByTitle(@RequestParam("bookTitle") String title, Model model){
        try {
            model.addAttribute("book", new Book());
            model.addAttribute("listBooks", bookService.listBookByCriteria(title, TITLE));
        } catch (NoSuchElementException e) {
            return "/notfound";
        }

        return "maincontent";
    }

    @RequestMapping("/addLike")
    public String addLike(@RequestParam("id") int id,
                          @RequestParam("addLike") String addLike,
                          Model model)
    {
        bookService.addLike(id, addLike);
        return bookData(id, model);
    }
}
