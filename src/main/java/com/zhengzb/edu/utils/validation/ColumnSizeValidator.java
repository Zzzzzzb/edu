package com.zhengzb.edu.utils.validation;



import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class ColumnSizeValidator implements ConstraintValidator<ColumnSize, String> {
    
    private long size;
    
    @Override
    public void initialize(ColumnSize columnSize) {
        this.size = columnSize.size();
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        // 为空，默认通过
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        // use in oracle
        // return value.getBytes(StandardCharsets.UTF_8).length <= this.size;
        // use in mysql
        return value.length() <= this.size;
    }
}
