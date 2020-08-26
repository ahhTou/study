package com.ahhTou.validator;

import com.ahhTou.annotation.MailUnique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MailValidator implements ConstraintValidator<MailUnique, String> {

    @Override
    public void initialize(MailUnique mailUnique) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("==================================");
        return false;
    }
}
