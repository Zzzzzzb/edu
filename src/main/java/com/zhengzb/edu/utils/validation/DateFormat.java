package com.zhengzb.edu.utils.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = { DateFormatValidator.class })
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormat {
    String message() default "";

    String pattern();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}