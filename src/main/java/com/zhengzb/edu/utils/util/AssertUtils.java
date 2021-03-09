package com.zhengzb.edu.utils.util;

import com.zhengzb.edu.common.exception.ApiException;
import org.apache.commons.lang3.StringUtils;



public class AssertUtils {
    
    /**
     * 不允许为null
     *
     * @param obect
     * @param message
     */
    public static void notNull(Object obect, String message) {
        message = StringUtils.defaultString(message, "param can't be null");
        if (obect == null) {
            throw new ApiException("9999", message);
        }
    }
    
    /**
     * 字段串不允许为Blank
     *
     * @param string
     * @param message
     */
    public static void notBlank(String string, String message) {
        message = StringUtils.defaultString(message, "param can't be blank");
        if (StringUtils.isBlank(string)) {
            throw new ApiException("9999", message);
        }
    }
    
    /**
     * 字符串不允许为Empty
     *
     * @param string
     * @param message
     */
    public static void notEmpty(String string, String message) {
        message = StringUtils.defaultString(message, "param can't be empty");
        if (StringUtils.isEmpty(string)) {
            throw new ApiException("9999", message);
        }
    }
    
}
