package com.el.spring.controller;

import com.el.spring.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class BookController {

    private final BookService service;

    final
    ApplicationContext context;

    @Autowired
    public BookController(BookService service, ApplicationContext context) {
        this.service = service;
        this.context = context;
    }

    @RequestMapping(value = "/index2", method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView("/view/index2.jsp");
        return mav;
    }
}