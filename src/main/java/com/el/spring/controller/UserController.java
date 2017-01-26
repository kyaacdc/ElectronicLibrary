package com.el.spring.controller;

import com.el.spring.Util.UserValidator;
import com.el.spring.entity.User;
import com.el.spring.service.SecurityService;
import com.el.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userform, BindingResult bindingResult, Model model){
        userValidator.validate(userform, bindingResult);

        if(bindingResult.hasErrors())
            return "registration";

        userService.save(userform);

        securityService.autiLogin(userform.getUsername(), userform.getConfirmPassword());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout){
        if(error != null)
            model.addAttribute("error", "Username or password is incorrect!");
        if(logout != null)
            model.addAttribute("message", "Logout successfully");

        return "login";
    }

    @RequestMapping(value = {"/welcome", "/"}, method = RequestMethod.GET)
    public String welcome(Model model){
        return "welcome";
    }

    @RequestMapping(value = {"/admin"}, method = RequestMethod.GET)
    public String admin(Model model){
        return "admin";
    }
}
