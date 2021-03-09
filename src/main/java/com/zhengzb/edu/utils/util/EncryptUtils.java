package com.zhengzb.edu.utils.util;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.fastjson.annotation.JSONField;

import com.zhengzb.edu.common.constant.CodeMsg;
import com.zhengzb.edu.common.constant.MedicalSerCodeMsg;
import com.zhengzb.edu.common.exception.ApiException;
import com.zhengzb.edu.utils.sm3.SM3Utils;
import com.zhengzb.edu.utils.sm4.SM4Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.util.*;


@Slf4j
public class EncryptUtils {

    private static final String ENCRYPT_KEY = "242BFC1FB44B238986AB168D3610B771";// 默认密钥

    /**
     * 加密，默认日志输出
     *
     * @param body
     * @param encType
     * @param encKey
     * @return
     */
    public static String encrypt(String body, String encType, String encKey) {
        return encryptBody(body, encType, encKey);
    }

    /**
     * 加密
     *
     * @param body
     * @return
     */
    public static String encryptBody(String body) {
        log.info("待加密body：{}", body);
        String encKey = ENCRYPT_KEY;

        String encryptBody;

        try {

            encryptBody = SM4Utils.encryptData_ECB(body, encKey, false).toUpperCase();

        } catch (Exception e) {
            throw new ApiException(MedicalSerCodeMsg.ERROR_10001);
        }

        return encryptBody;
    }


    /**
     * 加密
     *
     * @param body
     * @param encType
     * @param encKey
     * @return
     */
    public static String encryptBody(String body, String encType, String encKey) {
        log.info("待加密body：{}", body);
        // key空值判断
        encKey = StringUtils.defaultIfBlank(encKey, ENCRYPT_KEY);

        String encryptBody;
        if ("AES".equalsIgnoreCase(encType) || "SM4".equalsIgnoreCase(encType) || "PLAIN".equalsIgnoreCase(
            encType)) {
            try {
                if ("AES".equalsIgnoreCase(encType)) {
                    // AES
                    AES aes = new AES(Mode.ECB, Padding.ZeroPadding, encKey.getBytes(
                        StandardCharsets.UTF_8));
                    encryptBody = aes.encryptHex(body).toUpperCase();
                } else if ("SM4".equalsIgnoreCase(encType)) {
                    // SM4
                    encryptBody = SM4Utils.encryptData_ECB(body, encKey, false).toUpperCase();
                } else {
                    // PLAIN明文
                    encryptBody = body;
                }
            } catch (Exception e) {
                throw new ApiException(CodeMsg.ERROR_10006);
            }
        } else {
            throw new ApiException(CodeMsg.ERROR_9999.setMsg("错误的加密类型"));
        }
        return encryptBody;
    }

    /**
     * 解密
     *
     * @param body
     * @param encType
     * @param encKey
     * @return
     */
    public static String decrypt(String body, String encType, String encKey) {
        return decryptBody(body, encType, encKey);
    }

    /**
     * 解密
     *
     * @param body
     * @param encType
     * @param encKey
     * @return
     */
    public static String decryptBody(String body, String encType, String encKey) {
        // key空值判断
        encKey = StringUtils.defaultIfBlank(encKey, ENCRYPT_KEY);

        String decryptBody;
        if ("AES".equalsIgnoreCase(encType) || "SM4".equalsIgnoreCase(encType) || "PLAIN".equalsIgnoreCase(
            encType)) {
            try {
                if ("AES".equalsIgnoreCase(encType)) {
                    // AES
                    AES aes = new AES(Mode.ECB, Padding.ZeroPadding, encKey.getBytes(
                        StandardCharsets.UTF_8));
                    decryptBody = aes.decryptStr(body);
                } else if ("SM4".equalsIgnoreCase(encType)) {
                    // SM4
                    decryptBody = SM4Utils.decryptData_ECB(body, encKey, false);
                } else {
                    // PLAIN明文
                    decryptBody = body;
                }
            } catch (Exception e) {
                throw new ApiException(CodeMsg.ERROR_10006);
            }
        } else {
            throw new ApiException(CodeMsg.ERROR_10003);
        }
        log.info("解密后body：{}", decryptBody);
        return decryptBody;
    }

    /**
     * 签名
     *
     * @param paramsMap
     * @param digestType
     * @param encKey
     * @return
     */
    public static String sign(Map<String, Object> paramsMap, String digestType, String encKey) {
        return sign(paramsMap, digestType, encKey, true);
    }

    /**
     * 签名
     *
     * @param paramsMap
     * @param digestType
     * @param encKey
     * @param isNeedLogPrint 是否需要日志输出  true:输出日志， false不输出
     * @return
     */
    public static String sign(Map<String, Object> paramsMap, String digestType, String encKey, boolean isNeedLogPrint) {
        // key空值判断
        encKey = StringUtils.defaultIfBlank(encKey, ENCRYPT_KEY);

        // 2、字典值排序拼接成a=1&b=2&c=3格式，再拼接上AES密钥key=aes_key
        TreeSet<String> treeSet = new TreeSet<>(paramsMap.keySet());
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : treeSet) {
            if ("digest".equals(key)) {
                continue;
            }
            Object value = paramsMap.get(key);
            // 空值不参与签名
            if (value != null && StringUtils.isNotBlank(value.toString())) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.append("&");
                }
                stringBuilder.append(key).append("=").append(value);
            }
        }
//        stringBuilder.append("&key=").append(encKey);
        if (isNeedLogPrint) {
            log.info("待签名串：" + stringBuilder);
        }

        String digest;
        if ("MD5".equalsIgnoreCase(digestType)) {
            // 3、进行MD5签名，得到签名值
            digest = MD5.create().digestHex(stringBuilder.toString()).toUpperCase();
        } else if ("SM3".equalsIgnoreCase(digestType)) {
            // 3、进行SM3签名，得到签名值
            digest = SM3Utils.encrypt(stringBuilder.toString()).toUpperCase();
        } else {
            throw new ApiException(CodeMsg.ERROR_10004);
        }
        return digest;
    }

    /**
     * 验签
     *
     * @param paramsMap
     * @param digestType
     * @param encKey
     * @return
     */
    public static boolean verifySign(Map<String, Object> paramsMap, String digestType, String encKey) {
        return verifySign(paramsMap, digestType, encKey, true);
    }

    /**
     * 验签
     *
     * @param paramsMap
     * @param digestType
     * @param encKey
     * @param isNeedLogPrint 是否需要日志输出  true:输出日志， false不输出
     * @return
     */
    private static boolean verifySign(Map<String, Object> paramsMap,
                                      String digestType,
                                      String encKey,
                                      boolean isNeedLogPrint) {
        // key空值判断
        encKey = StringUtils.defaultIfBlank(encKey, ENCRYPT_KEY);

        // 2、字典值排序拼接成a=1&b=2&c=3格式，再拼接上AES密钥key=aes_key
        TreeSet<String> treeSet = new TreeSet<>(paramsMap.keySet());
        StringBuilder stringBuilder = new StringBuilder();
        for (String key : treeSet) {
            if ("digest".equals(key)) {
                continue;
            }
            Object value = paramsMap.get(key);
            // 空值不参与签名
            if (value != null && StringUtils.isNotBlank(value.toString())) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.append("&");
                }
                stringBuilder.append(key).append("=").append(value);
            }
        }
        stringBuilder.append("&key=").append(encKey);
        if (isNeedLogPrint) {
            log.info("待签名串：" + stringBuilder);
        }

        String digest;
        if ("MD5".equalsIgnoreCase(digestType)) {
            // 3、进行MD5签名，得到签名值
            digest = MD5.create().digestHex(stringBuilder.toString()).toUpperCase();
        } else if ("SM3".equalsIgnoreCase(digestType)) {
            // 3、进行SM3签名，得到签名值
            digest = SM3Utils.encrypt(stringBuilder.toString()).toUpperCase();
        } else {
            throw new ApiException(CodeMsg.ERROR_10004);
        }

        // 验签
        boolean validateResult = Objects.equals(digest, paramsMap.get("digest"));
        if (isNeedLogPrint) {
            log.info("验签结果：{}", validateResult);
        }
        return validateResult;
    }

    /**
     * bean转Map
     *
     * @param bean
     * @return
     */
    public static Map<String, Object> beanToMap(Object bean) {
        Field[] fields = bean.getClass().getFields();
        // 1、参数赋值
        Map<String, Object> apiReqMap = new LinkedHashMap<>();
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) || Modifier.isFinal(modifiers) || Modifier.isPrivate(modifiers)) {
                continue;
            }
            JSONField jsonField = field.getAnnotation(JSONField.class);
            String key = field.getName();
            if (jsonField != null && StringUtils.isNotBlank(jsonField.name())) {
                key = jsonField.name();
            }
            try {
                // 获取read方法
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), bean.getClass());
                Method readMethod = propertyDescriptor.getReadMethod();
                apiReqMap.put(key, readMethod.invoke(bean));
            } catch (IntrospectionException | IllegalAccessException | InvocationTargetException e) {
                log.error("获取HisApiInDTO的属性方法失败", e);
            }
        }
        return apiReqMap;
    }

    public static TreeMap<String, Object> convertBeanToMap(Object bean) {
        TreeMap<String, Object> map = new TreeMap<>();
        Class<?> clazz = bean.getClass();
        for(; clazz != Object.class;clazz = clazz.getSuperclass()){
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {// 获取bean的属性和值
                field.setAccessible(true);
                try {
                    if (field.get(bean) != null) {
                        map.put(field.getName(), field.get(bean));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new ApiException(CodeMsg.ERROR_9999.setMsg("数据转换失败"));
                }
            }
        }
        return map;
    }


    public static TreeMap<String, Object> convertBeanToMap(Object[] beans) {
        TreeMap<String, Object> map = new TreeMap<>();

        for(Object bean : beans) {
            Class<?> clazz = bean.getClass();
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {// 获取bean的属性和值
                    field.setAccessible(true);
                    try {
                        if (field.get(bean) != null) {
                            map.put(field.getName(), field.get(bean));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        throw new ApiException(CodeMsg.ERROR_9999.setMsg("数据转换失败"));
                    }
                }
            }
        }
        return map;
    }
}
