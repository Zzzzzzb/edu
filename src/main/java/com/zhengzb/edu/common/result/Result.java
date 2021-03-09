package com.zhengzb.edu.common.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.zhengzb.edu.common.constant.CodeMsg;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel
@JsonPropertyOrder({"code", "msg", "data"})
public class Result<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "响应码", position = 1)
    private final String code;
    
    @ApiModelProperty(value = "响应消息", position = 2)
    private final String msg;
    
    @ApiModelProperty(value = "响应数据", position = 3)
    private final T data;
    
    private Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    
    @JsonIgnore
    public boolean isSuccess() {
        return CodeMsg.SUCCESS.getCode().equals(this.code);
    }
    
    @JsonIgnore
    public boolean isFailed() {
        return !isSuccess();
    }
    
    public static <T> Result<T> success() {
        return success(null);
    }
    
    public static <T> Result<T> success(T data) {
        return success(data,CodeMsg.SUCCESS.getMsg());
    }
    
    public static <T> Result<T> success(T data, String msg) {
        return new Result<T>(CodeMsg.SUCCESS.getCode(), msg, data);
    }

    public static <T> Result<T> error(String msg) {
        return new Result<T>("-1", msg, null);
    }

    public static <T> Result<T> error(CodeMsg codeMsg) {
        return new Result<T>(codeMsg.getCode(), codeMsg.getMsg(), null);
    }

    public static <T> Result<T> error(CodeMsg codeMsg, Object... objects) {
        return error(codeMsg.getCode(), codeMsg.getMsg(), objects);
    }

    public static <T> Result<T> error(String code, String msg) {
        return new Result<T>(code, msg, null);
    }

    public static <T, E> Result<T> error(Result<E> result) {
        return new Result<T>(result.getCode(), result.getMsg(), null);
    }

    public static <T> Result<T> error(String code, String msg, Object... objects) {
        return new Result<T>(code, String.format(msg, objects), null);
    }

    public static <T> Result<T> error(String code, String msg, T data) {
        return new Result<T>(code, msg, data);
    }
    
    public String getCode() {
        return this.code;
    }
    
    public String getMsg() {
        return this.msg;
    }
    
    public T getData() {
        return this.data;
    }
}
