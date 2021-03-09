package com.zhengzb.edu.http.basic;

import com.alibaba.fastjson.JSON;

import com.zhengzb.edu.common.constant.CodeMsg;
import com.zhengzb.edu.common.exception.ApiException;
import com.zhengzb.edu.common.result.Result;
import com.zhengzb.edu.http.handler.*;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.Assert;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Slf4j
@Getter
@Setter
public abstract class AbstractTxnHandler<REQ, RESP> implements TxnHandler<REQ, RESP>, TxnHttpHandler<REQ, RESP>, ApplicationContextAware, InitializingBean {
    
    private final OkHttpClient okHttpClient;
    private ApplicationContext applicationContext;
    
    public AbstractTxnHandler(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(applicationContext, "applicationContext can't be null");
    }
    
    /**
     * 交易请求
     *
     * @param req
     * @return
     */
    @Override
    public Result<RESP> doTxn(REQ req) {
        // 请求前
        TxnHttpRequest txnHttpRequest = beforeRequest(req);
        // 发起请求
        TxnHttpResponse txnHttpResponse = call(txnHttpRequest);
        // 响应后
        return afterResponse(txnHttpRequest, txnHttpResponse);
    }
    
    /**
     * 请求前处理
     *
     * @param req
     * @return
     */
    protected abstract TxnHttpRequest beforeRequest(REQ req);
    
    /**
     * 响应后处理
     *
     * @param txnHttpRequest
     * @param txnHttpResponse
     * @return
     */
    protected abstract Result<RESP> afterResponse(TxnHttpRequest txnHttpRequest,
            TxnHttpResponse txnHttpResponse);
    
    /**
     * 接口调用
     *
     * @param txnHttpRequest
     * @return
     */
    protected TxnHttpResponse call(TxnHttpRequest txnHttpRequest) {
        // 构建请求
        Request.Builder requestbuilder = new Request.Builder();
        requestbuilder.url(txnHttpRequest.getUrl());
        // 设置请求信息
        setRequest(requestbuilder, txnHttpRequest);
        String httpInterfaceName = txnHttpRequest.getInterfaceName();
        try (Response response = okHttpClient.newCall(requestbuilder.build()).execute()) {
            log.info("{}接口响应头:{}", httpInterfaceName, response.headers());
            ResponseBody body = response.body();
            if (body == null) {
                throw new ApiException(CodeMsg.ERROR_10007, httpInterfaceName);
            }
            String responseBody = body.string();
            log.info("[{}]接收响应报文:{}", httpInterfaceName, responseBody);
            TxnHttpResponse txnHttpResponse = new TxnHttpResponse();
            txnHttpResponse.setCode(response.code());
            txnHttpResponse.setBody(responseBody);
            Map<String, List<String>> stringListMap = response.headers().toMultimap();
            Map<String, String> headers = new HashMap<>(stringListMap.size());
            stringListMap.forEach((key, list) -> headers.put(key, StringUtils.join(list.toArray(), ",")));
            txnHttpResponse.setHeaders(headers);
            txnHttpResponse.setSuccessful(response.isSuccessful());
            return txnHttpResponse;
        } catch (IOException e) {
            log.error("Okhttp3调用地址[{}]异常", txnHttpRequest.getUrl(), e);
            throw new ApiException(CodeMsg.ERROR_10008, httpInterfaceName);
        }
    }
    
    /**
     * 请求设置请求参数
     *
     * @param requestbuilder
     * @param txnHttpRequest
     */
    protected void setRequest(Request.Builder requestbuilder, TxnHttpRequest txnHttpRequest) {
        log.info("[{}]接口请求头:{}", txnHttpRequest.getInterfaceName(), txnHttpRequest.getHeaders());
        log.info("[{}]接口请求参数:{}", txnHttpRequest.getInterfaceName(), JSON.toJSONString(txnHttpRequest.getParameters()));
        
        // 设置请求头
        txnHttpRequest.getHeaders().forEach(requestbuilder::addHeader);
        // 请求方法
        HttpMethod method = txnHttpRequest.getHttpMethod();
        // 设置请求体
        Map<String, Object> parameters = txnHttpRequest.getParameters();
        switch (method) {
            case GET:
                HttpUrl.Builder urlBuilder = HttpUrl.get(txnHttpRequest.getUrl()).newBuilder();
                parameters.forEach(
                        (key, value) -> {
                            if (value == null) {
                                return;
                            }
                            urlBuilder.addQueryParameter(key, String.valueOf(value));
                        });
                requestbuilder.url(urlBuilder.build());
                break;
            case POST:
            case PUT:
            case DELETE:
                RequestBody requestBody = null;
                switch (txnHttpRequest.getBodyType()) {
                    case JSON:
                        requestBody = RequestBody.create(MediaType.get(txnHttpRequest.getMediaType().getValue()),
                                                         JSON.toJSONString(parameters));
                        break;
                    case Form:
                        StringBuilder stringBuilder = new StringBuilder();
                        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                            String key = entry.getKey();
                            Object value = entry.getValue();
                            String strValue;
                            if (value instanceof String) {
                                strValue = (String) value;
                            } else {
                                strValue = JSON.toJSONString(value);
                            }
                            stringBuilder.append(key).append("=").append(strValue).append("&");
                        }
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                        requestBody = RequestBody.create(
                                MediaType.parse(HttpMediaType.APPLICATION_FORM_URLENCODED_VALUE.getValue()),
                                stringBuilder.toString());
                        break;
                    case Multipart:
                        break;
                    default:
                        throw new ApiException(CodeMsg.ERROR_10010);
                }
                requestbuilder.method(method.name(), requestBody);
                requestbuilder.url(txnHttpRequest.getUrl());
                break;
            default:
                throw new ApiException(CodeMsg.ERROR_10009);
        }
    }
}
