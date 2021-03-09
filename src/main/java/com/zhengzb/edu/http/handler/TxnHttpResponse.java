package com.zhengzb.edu.http.handler;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class TxnHttpResponse {

    /**
     * 响应头
     */
    private Map<String, String> headers = new HashMap<>(8);
    /**
     * 响应体内容
     */
    private String body;
    /**
     * 交易是否成功
     */
    private boolean successful;
    /**
     * HTTP响应码
     */
    private int code;

}
