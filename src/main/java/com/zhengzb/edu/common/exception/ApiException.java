package com.zhengzb.edu.common.exception;

import com.zhengzb.edu.common.constant.CodeMsg;
import com.zhengzb.edu.common.result.Result;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class ApiException extends RuntimeException {
    
    private String code = "-2";
    private String msg;
    
    public ApiException() {
        super();
    }
    
    public ApiException(CodeMsg codemsg) {
        super(codemsg.getMsg());
        this.code = codemsg.getCode();
        this.msg = codemsg.getMsg();
    }
    
    public ApiException(CodeMsg codemsg, Object... objects) {
        super(String.format(codemsg.getMsg(), objects));
        this.code = codemsg.getCode();
        this.msg = String.format(codemsg.getMsg(), objects);
    }
    
    public ApiException(String code, String message) {
        super(message);
        this.code = code;
        this.msg = message;
    }
    
    public <T> ApiException(Result<T> result) {
        super(result.getMsg());
        this.code = result.getCode();
        this.msg = result.getMsg();
    }
    
    private ApiException(String message) {
        super(message);
        this.msg = message;
    }
    
    private ApiException(String message, Throwable cause) {
        super(message, cause);
        this.msg = message;
    }
    
    private ApiException(Throwable cause) {
        super(cause);
        this.msg = cause.getMessage();
    }
    
    private ApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.msg = message;
    }
    
}
