package com.zhengzb.edu.http.handler;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;


@Data
public class TxnHttpRequest {

    /**
     * 请求URL地址
     */
    private String url;
    /**
     * HTTP请求方法
     */
    private HttpMethod httpMethod;
    /**
     * 接口名称
     */
    private String interfaceName;
    /**
     * 请求头
     */
    private Map<String, String> headers = new HashMap<>(8);
    /**
     * 请求参数
     */
    private Map<String, Object> parameters = new HashMap<>(16);
    /**
     * 媒体类型
     */
    private HttpMediaType mediaType;
    /**
     * 请求参数传输方式
     */
    private BodyType bodyType;
    /**
     * 扩展参数
     */
    private Map<String, Object> extendParams = new HashMap<>(16);

}
