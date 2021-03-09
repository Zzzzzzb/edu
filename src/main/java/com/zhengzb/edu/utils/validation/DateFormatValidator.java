package com.zhengzb.edu.utils.validation;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class DateFormatValidator implements ConstraintValidator<DateFormat, String> {
    private String pattern;

    @Override
    public void initialize(DateFormat constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        boolean result = true;
        if (StringUtils.isEmpty(value)) {
            return result;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            format.setLenient(false);
            format.parse(value);
        } catch (ParseException e) {
            result = false;
        }
        return result;
    }
}