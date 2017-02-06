package com.el.spring.controller;

import com.el.spring.entity.Tag;
import com.el.spring.service.BookService;
import com.el.spring.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.ConstraintViolationException;

@Controller
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private BookService bookService;

    //Operations with Tags

    @RequestMapping(value = "tags", method = RequestMethod.GET)
    public String listTags(Model model) {
        model.addAttribute("tag", new Tag());
        model.addAttribute("listBooks", bookService.listBooks());
        model.addAttribute("listTags", tagService.listTags());

        return "tags";
    }

    @RequestMapping(value = "/tags/add", method = RequestMethod.POST)
    public String addTag(@ModelAttribute("tag") Tag tag) {
        try {
            if (tag.getId() == 0) {
                tagService.addTag(tag);
            } else {
                tagService.updateTag(tag);
            }
        } catch (TransactionSystemException  | ConstraintViolationException e) {
            return "errorinput";
        }

        return "redirect:/tags";
    }

    @RequestMapping("/tags/remove/{id}")
    public String removeTag(@PathVariable("id") int id) {
        tagService.removeTag(id);

        return "redirect:/tags";
    }

    @RequestMapping("/tags/edit/{id}")
    public String editTag(@PathVariable("id") int id, Model model) {
        model.addAttribute("tag", tagService.getTagById(id));
        model.addAttribute("listTags", tagService.listTags());

        return "tags";
    }
}


