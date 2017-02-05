package com.el.spring.entity.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameConstraintValidator implements ConstraintValidator<Name, String> {

    @Override
    public void initialize(Name name) {
    }

    @Override
    public boolean isValid(String nameField, ConstraintValidatorContext cxt) {
        if (nameField == null) {
            return false;
        }
        return nameField.matches("[a-zA-Z \\.]*");
    }

}

