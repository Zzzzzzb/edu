package com.zhengzb.edu.common.handler;


import com.zhengzb.edu.common.constant.ApiContext;



public class ApiContextHandler {

    private static ThreadLocal<ApiContext> apiContextThreadLocal = new ApiContextThreadLocal();

    static final class ApiContextThreadLocal extends ThreadLocal<ApiContext> {
        @Override
        public ApiContext get() {
            ApiContext apiContext = super.get();
            return apiContext == null ? new ApiContext() : apiContext;
        }
    }

    public static void setApiContext(ApiContext apiContext) {
        apiContextThreadLocal.set(apiContext);
    }

    public static ApiContext getApiContext() {
        return apiContextThreadLocal.get();
    }

    public static void removeApiContext() {
        apiContextThreadLocal.remove();
    }

}
