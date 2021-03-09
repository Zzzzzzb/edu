package com.zhengzb.edu.http.handler;


public interface TxnHttpHandler<REQ, RESP> {

    /**
     * 获取请求数据类型
     *
     * @return
     */
    default BodyType getBodyType() {
        return BodyType.JSON;
    }

    /**
     * 获取HTTP请求方法
     *
     * @return
     */
    default HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    /**
     * 用于子类实现自定义的媒体类型
     *
     * @return
     */
    default HttpMediaType getMediaType() {
        return HttpMediaType.APPLICATION_JSON_UTF8_VALUE;
    }

    /**
     * 获取请求地址
     *
     * @return
     */
    default String getUrl(REQ req) {
        return null;
    }

    /**
     * 获取接口名称
     *
     * @return
     */
    String getInterfaceName();

}
