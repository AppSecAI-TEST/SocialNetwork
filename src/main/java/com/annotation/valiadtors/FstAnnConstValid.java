package com.annotation.valiadtors;

import com.annotation.FstAnn;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by bohdan on 01.11.16.
 */
public class FstAnnConstValid implements ConstraintValidator<FstAnn, String> {
    @Override
    public void initialize(FstAnn field) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean isValid(String field, ConstraintValidatorContext cxt) {
        if(field == null) {
            return false;
        }
        return field.matches("[0-9()-\\.]*");
    }
}
