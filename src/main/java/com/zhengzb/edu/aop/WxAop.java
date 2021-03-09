package com.zhengzb.edu.aop;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.zhengzb.edu.annotation.NeedToken;
import com.zhengzb.edu.code.userinfo.resp.AccountInfoResp;
import com.zhengzb.edu.code.userinfo.service.AccountTokenService;
import com.zhengzb.edu.code.userinfo.service.UserBusinessService;
import com.zhengzb.edu.common.constant.ApiContext;
import com.zhengzb.edu.common.constant.CodeMsg;
import com.zhengzb.edu.common.constant.GlobalConst;
import com.zhengzb.edu.common.constant.MedicalSerCodeMsg;
import com.zhengzb.edu.common.exception.ApiException;
import com.zhengzb.edu.common.handler.ApiContextHandler;
import com.zhengzb.edu.common.handler.ServletHandler;
import com.zhengzb.edu.common.properties.AopProperties;
import com.zhengzb.edu.common.result.Result;
import com.zhengzb.edu.utils.util.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * <ul>
 * <li>文件名称: WxAop</li>
 * <li>文件描述: 全局切面</li>
 * <li>版权所有: 版权所有(C) 2018</li>
 * <li>公   司: 厦门市云顶伟业信息技术有限公司<>
 * <li>内容摘要: <0/li>
 * <li>其他说明: </li>
 * <li>创建日期: 2020-11-16 17:09<>
 * </ul>
 * <ul>
 * <li>修改记录: </li>
 * <li>版 本 号: </li>
 * <li>修改日期: </li>
 * <li>修 改 人:</li>
 * <li>修改内容:</li>
 * </ul>
 *
 * @author gonglk
 * @version 1.0.0
 */
@Aspect
@Slf4j
@Component
public class WxAop {

    @Autowired
    private AopProperties aopProperties;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private AccountTokenService accountTokenService;
    @Autowired
    private UserBusinessService userBusinessService;

    /**
     * 共同切面
     */
    @Pointcut("execution( public * com.zhengzb..*Controller.*(..) )")
    public void apiPointCut(){}

    /**
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("apiPointCut()")
    public Object processApi(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest httpServletRequest = ServletHandler.getRequest();
        String uri = httpServletRequest.getRequestURI();
        log.info("============请求路径为[{}]============", uri);


        Class<?> clazz = joinPoint.getTarget().getClass();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();

        NeedToken needToken = null;
        // 默认使用类型注解
        if (clazz.isAnnotationPresent(NeedToken.class)) {
            needToken = clazz.getAnnotation(NeedToken.class);
        }

        if (needToken == null) {
            // 取方法上的注解
            Method m = clazz.getMethod(method.getName(), method.getParameterTypes());
            if (m.isAnnotationPresent(NeedToken.class)) {
                needToken = m.getAnnotation(NeedToken.class);
            }
        }
        ApiContext apiContext = new ApiContext();
        if (needToken != null) {
            // 校验token是否存在
            String authorization = httpServletRequest.getHeader("Token") == null?
                    httpServletRequest.getHeader("Authorization"):httpServletRequest.getHeader("Token");
            if (aopProperties.getCheckToken()) {
                apiContext.setUserId(this.vaildToken(authorization));
            }
        }
        ApiContextHandler.setApiContext(apiContext);

        // 获取参数
        Object[] objects = joinPoint.getArgs();
        if(objects.length >0) {
            Object param = objects[0];
            log.info("请求报文：{}", JSON.toJSONString(param));
        }
        Object result = joinPoint.proceed(objects);
        log.info("响应报文：{}", JSON.toJSONString(result));
        ApiContextHandler.removeApiContext();;
        return result;
    }

    /**
     * 检查Authorization是否合法
     * @param authorization
     * @return
     */
    public String vaildToken(String authorization){
        if(StringUtils.isBlank(authorization)){
            throw new ApiException(CodeMsg.ERROR_10000);
        }
        // 解析token
        final DecodedJWT jwt = JwtUtils.getJWT(authorization);
        if (!JwtUtils.validateToken(jwt)) {
            throw new ApiException(CodeMsg.ERROR_9998);
        }
        String token = jwt.getSubject();
        log.info("该账户ID为【{}】",token);
        // 检查token是否存在
        checkUserExist(authorization,token);
        if(aopProperties.getEnableSaveActive()) {
            this.saveDayActiveNumber(token);
        }
        return token;
    }



    /**
     * 校验token是否存在
     * @param token
     */
    public void checkUserExist(String authorization,String token){
        Long expireTime = redisTemplate.getExpire(GlobalConst.Redis.USER_ID+token);
        if(expireTime<=0) {
            Result<AccountInfoResp> respResult = accountTokenService.getByToken(authorization);
            if (respResult.isSuccess()) {
                redisTemplate.opsForValue().set(GlobalConst.Redis.USER_ID+token, JSON.toJSONString(respResult.getData()),24, TimeUnit.HOURS);
            } else {
                throw new ApiException(respResult.getCode(), respResult.getMsg());
            }
        }
    }

    /**
     * 记录每日用户活跃数-用于后期了解微信公众号用户每日活跃情况
     * @param token
     */
    public void saveDayActiveNumber(String token){
        String date = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        redisTemplate.opsForHyperLogLog().add(GlobalConst.Redis.ACTIVE_PERSON_DAY+date,token);
        // 用于设置过期时间
        if(redisTemplate.getExpire(GlobalConst.Redis.ACTIVE_PERSON_DAY+date) <= 0) {
            redisTemplate.expire(GlobalConst.Redis.ACTIVE_PERSON_DAY + date, 30, TimeUnit.DAYS);
        }
    }

}
