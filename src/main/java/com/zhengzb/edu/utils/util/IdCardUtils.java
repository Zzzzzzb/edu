package com.zhengzb.edu.utils.util;

import org.apache.commons.lang3.StringUtils;

/**
 * <ul>
 * <li>文件名称: IdCardUtils</li>
 * <li>文件描述:</li>
 * <li>版权所有: 版权所有(C) 2016</li>
 * <li>公 司: 厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要:</li>
 * <li>其他说明:</li>
 * <li>创建日期: 2017/9/5</li>
 * </ul>
 * <ul>
 * <li>修改记录:</li>
 * <li>版 本 号:</li>
 * <li>修改日期:</li>
 * <li>修 改 人:</li>
 * <li>修改内容:</li>
 * </ul>
 *
 * @author xieyy
 * @version 1.0.0
 */
public class IdCardUtils {

    /**
     * 根据身份证号获取性别
     */
    public static String getSexByIdNo(String idNo){
        if (StringUtils.isBlank(idNo) || (idNo.length() != 15 && idNo.length() != 18)) {
            return null;
        }
        if (idNo.length() == 15) {
            return Integer.parseInt(String.valueOf(idNo.charAt(idNo.length() - 1))) % 2 == 0 ? "2" : "1";
        }
        return Integer.parseInt(String.valueOf(idNo.charAt(idNo.length() - 2))) % 2 == 0 ? "2" : "1";
    }

    /**
     * 根据身份证号获取出生日期
     */
    public static String getBirthdayByIdNo(String idNo) {
        if (StringUtils.isBlank(idNo) || (idNo.length() != 15 && idNo.length() != 18)) {
            return null;
        }
        if (idNo.length() == 15) {
            return "19" + idNo.substring(6, 12);
        }
        return idNo.substring(6, 14);
    }

	//身份证前三后四脱敏
	public static String idEncrypt(String id) {
		if (StringUtils.isEmpty(id) || (id.length() < 8)) {
			return id;
		}
		return id.replaceAll("(?<=\\w{3})\\w(?=\\w{4})", "*");
	}

	// 手机号码前三后四脱敏
	public static String mobileEncrypt(String mobile) {
		if (StringUtils.isEmpty(mobile) || (mobile.length() != 11)) {
			return mobile;
		}
		return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
	}

	/**
	 * 名字脱敏
	 * 规则，张三丰，脱敏为：张*丰
	 * @param name
	 * @return
	 */
	public static String nameDesensitization(String name){
		if(name==null || name.isEmpty()){
			return "";
		}
		String myName = null;
		char[] chars = name.toCharArray();
		if(chars.length==1){
			myName=name;
		}
		if(chars.length==2){
			myName=name.replaceFirst(name.substring(1), "*");
		}
		if(chars.length>2){
			myName=name.replaceAll(name.substring(1, chars.length-1), "*");
		}
		return myName;
	}
}
