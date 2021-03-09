package com.zhengzb.edu.common.exception;


import com.zhengzb.edu.common.constant.CodeMsg;
import com.zhengzb.edu.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.sql.SQLException;

@ControllerAdvice
@ResponseBody
@Order(2)
@Configuration
public class ApiExceptionHandler {
    
    @Value("${spring.servlet.multipart.max-file-size:10MB}")
    private String maxFileSize;
    
    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);
    
    @ExceptionHandler(value = Exception.class)
    public Object exceptionHandler(Exception e) {
        logger.error("其他异常", e);
        return Result.error(CodeMsg.SERVER_BUSY);
    }
    
    @ExceptionHandler(value = ApiException.class)
    public Object apiException(ApiException e) {
        logger.error("接口异常", e);
        return Result.error(e.getCode(), e.getMsg());
    }
    
    @ExceptionHandler(value = SQLException.class)
    public Object sqlException(SQLException e) {
        logger.error("SQL异常", e);
        return Result.error(CodeMsg.ERROR_SERVER.setMsg("SQL异常"));
    }
    
    @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
    public Object httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        logger.error("媒体类型异常", e);
        return Result.error(CodeMsg.ERROR_SERVER.setMsg("不受支持的媒体类型，请确认Content-Type是否正确"));
    }
    
    @ExceptionHandler(value = MaxUploadSizeExceededException.class)
    public Object maxUploadSizeExceededException(MaxUploadSizeExceededException e) {
        logger.error("上传文件太大异常", e);
        return Result.error(CodeMsg.ERROR_SERVER.setMsg("文件过大，限制" + maxFileSize + "以内"));
    }
    
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Object httpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("Http消息不可读异常", e);
        return Result.error(CodeMsg.ERROR_SERVER.setMsg("Http消息不可读异常"));
    }
    
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Object httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("HTTP.method错误[{}]", e.getMethod(), e);
        return Result.error(CodeMsg.ERROR_SERVER.setMsg("HTTP.method错误[" + e.getMethod() + "]"));
    }
    
    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    public Object methodArgumentNotValidException(Exception e) {
        logger.error("参数校验异常", e);
        
        BindingResult bindingResult;
        if (e instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) e).getBindingResult();
        } else {
            bindingResult = ((BindException) e).getBindingResult();
        }
        StringBuilder stringBuilder = new StringBuilder("参数校验失败：");
        if (bindingResult.hasFieldErrors()) {
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                stringBuilder.append("【").append(fieldError.getDefaultMessage()).append("】、");
            }
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            return Result.error(CodeMsg.ERROR_9999.setMsg(stringBuilder.toString()));
        }
        return Result.error(CodeMsg.ERROR_9999);
    }
    
}
