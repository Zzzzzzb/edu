package com.zhengzb.edu.common.handler;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <ul>
 * <li>文件名称：ServletHandler</li>
 * <li>文件描述：获取当前请求对象、返回对象、会话</li>
 * <li>版权所有：版权所有(C) 2016</li>
 * <li>公 司：厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要：</li>
 * <li>其他说明：</li>
 * <li>创建日期：2018年11月22日</li>
 * </ul>
 *
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

public class ServletHandler {

	/**
	 * 获取HttpServletRequest
	 *
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (!(requestAttributes instanceof ServletRequestAttributes)) {
			throw new IllegalArgumentException(
					"RequestAttributes is not an ServletRequestAttributes: " + requestAttributes);
		}
		return ((ServletRequestAttributes) requestAttributes).getRequest();

	}

	/**
	 * 获取HttpServletResponse
	 *
	 * @return
	 */
	public static HttpServletResponse getResponse() {
		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		if (!(requestAttributes instanceof ServletRequestAttributes)) {
			throw new IllegalArgumentException(
					"RequestAttributes is not an ServletRequestAttributes: " + requestAttributes);
		}
		return ((ServletRequestAttributes) requestAttributes).getResponse();
	}

	/**
	 * 获取HttpSerssion
	 *
	 * @return
	 */
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 获取客户端真实IP地址
	 *
	 * @return
	 */
	public static String getIpAddr() {
		HttpServletRequest request = getRequest();
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
        // 多次反向代理后会有多个ip值，第一个ip才是真实ip
        if (ip.contains(",")) {
            ip = ip.split(",")[0];
        }
		return ip;
	}

}
