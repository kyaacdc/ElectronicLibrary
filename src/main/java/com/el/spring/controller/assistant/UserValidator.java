package com.el.spring.controller.assistant;

import com.el.spring.entity.User;
import com.el.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator{

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if(user.getUsername().length() < 4 || user.getUsername().length() > 32)
            errors.rejectValue("username", "Size.userform.username");
        if(userService.findByUsername(user.getUsername()) != null)
            errors.rejectValue("username", "Duplicate.userform.username");

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if(user.getPassword().length() < 4 || user.getPassword().length() > 16)
            errors.rejectValue("password", "Size.userform.password");
        if(!user.getConfirmPassword().equals(user.getPassword()))
            errors.rejectValue("password", "Different.userform.password");

    }
}
