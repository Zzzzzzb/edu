package com.zhengzb.edu.utils.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <ul>
 * <li>文件名称：JwtUtils</li>
 * <li>文件描述：jwt生成token,校验token</li>
 * <li>版权所有：版权所有(C) 2016</li>
 * <li>公 司：厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要：</li>
 * <li>其他说明：使用token的好处:</li>
 * <li>创建日期：2017年10月30日</li>
 * </ul>
 * <p>
 * <ul>
 * <li>修改记录：</li>
 * <li>版 本 号：</li>
 * <li>修改日期：</li>
 * <li>修 改 人：</li>
 * <li>修改内容：</li>
 * </ul>
 *
 * @author xieyy
 * @version 1.0.0
 */

public class JwtUtils {

    private static Logger log = LoggerFactory.getLogger(JwtUtils.class);

    private static final String SECRET = "XMYDWYXXJSYXGS";
    // private static int second = 604800;// 7天
    private static final int SECOND = 86400;// 1天
    public static final int DAYS = SECOND /60/60/24;// 1天

    /**
     * 生成token
     *
     * @return token
     */
    public static String createToken(String userId) {
        return createToken(userId, null, SECOND);
    }

    /**
     * 生成token
     *
     * @return token
     */
    public static String createToken(String userId, Map<String, String> parameterMap) {
        return createToken(userId, parameterMap, SECOND);
    }

    /**
     * 生成token
     *
     * @param second 秒
     * @return token
     */
    public static String createToken(String userId, Map<String, String> parameterMap, int second) {
        // Header 头部
        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        Calendar calendar = Calendar.getInstance();

        JWTCreator.Builder builder = JWT.create().withHeader(map);
        // Payload 负载
        builder.withIssuer("app");// iss(Issuser)：代表这个JWT的签发主体；
        builder.withSubject(userId);// sub(Subject)：代表这个JWT的主体，即它的所有人；
        builder.withAudience("app");// aud(Audience)：代表这个JWT的接收对象；
        builder.withJWTId(DateFormatUtils.format(calendar.getTime(), "yyyyMMddHHmmss") + (int) (Math.ceil(
                Math.random() * 10000)));// jti(JWT ID)：是JWT的唯一标识。

        // 这里减60秒，是为了解决多台服务器的情况下，因时间差导致的token提前失效的问题
        Date date = new Date(calendar.getTime().getTime() - 60 * 1000);
        // 时间设置
        builder.withIssuedAt(date);// iat(Issued at)：是一个时间戳，代表这个JWT的签发时间；
        builder.withNotBefore(date);// nbf(Not Before)：是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的；
        calendar.add(Calendar.SECOND, second);// 有效期
        builder.withExpiresAt(calendar.getTime());// exp(Expiration time)：是一个时间戳，代表这个JWT的过期时间；
        // 自定义claim
        if (parameterMap != null) {
            for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
                builder.withClaim(entry.getKey(), entry.getValue());// 用户个人ID
            }
        }

        return builder.sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 获取用户ID
     *
     * @param jwt
     * @return
     */
    public static String getUserId(DecodedJWT jwt) {
        return jwt.getSubject();
    }

    /**
     * 获取jwt
     *
     * @param token
     * @return
     */
    public static DecodedJWT getJWT(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            return verifier.verify(token);
        } catch (JWTVerificationException e) {
            if (e.getMessage().startsWith("The Token has expired on")) {
                log.error("token已过期");
            } else {
                log.error("token验证失败", e);
            }
        } catch (Exception e) {
            log.error("token错误", e);
            return null;
        }
        return null;
    }

    /**
     * 校验token有效性
     *
     * @return map对象
     */
    public static boolean validateToken(DecodedJWT jwt) {
        return jwt != null;
    }

}
