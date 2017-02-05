package com.el.spring.entity.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsbnConstraintValidator implements ConstraintValidator<Isbn, String> {

    @Override
    public void initialize(Isbn isbn) {
    }

    @Override
    public boolean isValid(String isbnField, ConstraintValidatorContext cxt) {
        if (isbnField == null) {
            return false;
        }
        return isbnField.matches("[0-9()-\\.]*");
    }

}
