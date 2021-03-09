package com.zhengzb.edu.utils.util;

import com.zhengzb.edu.common.constant.CodeMsg;
import com.zhengzb.edu.common.exception.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.HibernateValidator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class ValidationUtils {
	private static Pattern linePattern = Pattern.compile("_(\\w)");
	/**
	 * 使用hibernate的注解来进行验证
	 *
	 */
	private static Validator validator = Validation.byProvider(HibernateValidator.class).configure().failFast(true)
			.buildValidatorFactory().getValidator();

    /**
     * 功能描述: <br>
     * 〈注解验证参数〉
     *
     * @param obj
     *            校验类
     */
    public static <T> void validate(T obj, Class<?>...cls) {
        if (obj == null) {
            throw new ApiException(CodeMsg.ERROR_9999.setMsg("请传入正确的参数"));
        }
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(obj, cls);
        // 抛出检验异常
        if (constraintViolations.size() > 0) {
            CodeMsg codeMsg = CodeMsg.ERROR_9999.setMsg(constraintViolations.iterator().next().getMessage());
            throw new ApiException(codeMsg);
        }
    }

	/**
	 * 校验是否为合法的手机号
	 *
	 * @param str
	 *            手机号
	 * @return 校验结果
	 */
	public static boolean isMobilePhone(String str) {
		String phonePattern = "^1[34578]\\d{9}$";
		Pattern compile = Pattern.compile(phonePattern);
		return compile.matcher(str).matches();
	}

	/**
	 * 判断是否是base64编码
	 *
	 * @param string
	 * @return
	 */
	public static boolean isBase64(String string) {
		if (string == null || string.length() == 0) {
			return false;
		}
		String base64Pattern = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";
		return Pattern.matches(base64Pattern, string);
	}

	/** 下划线转驼峰 */
	public static String lineToHump(String str) {
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * mysql的模糊查询时特殊字符转义
	 */
	public static String escapeChar(String before){
		if(StringUtils.isNotBlank(before)){
			before = before.replaceAll("_", "/_");
			before = before.replaceAll("%", "/%");
		}
		return before.trim() ;
	}

	/**
	 * 校验结束日期大于开始日期
	 *
	 * @param beginDate 开始日期 yyyy-MM-dd
	 * @param endDate   结束日期 yyyy-MM-dd
	 * @return
	 */
	public static boolean cheakTimeRange(String beginDate, String endDate) {
		if (StringUtils.isNotBlank(beginDate) || StringUtils.isNotBlank(endDate)) {
			beginDate = beginDate.replace("-", "");
			endDate = endDate.replace("-", "");
			if (Long.parseLong(endDate) < Long.parseLong(beginDate)) {
				return false;
			}
		}
		return true;
	}
}
