package com.zhengzb.edu.utils.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * <ul>
 * <li>文件名称: BigDecimalUtil</li>
 * <li>文件描述:</li>
 * <li>版权所有: 版权所有(C) 2017</li>
 * <li>公 司: 厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要:</li>
 * <li>其他说明:</li>
 * <li>创建日期:2020年11月25日</li>
 * </ul>
 * <ul>
 * <li>修改记录:</li>
 * <li>版 本 号:</li>
 * <li>修改日期:</li>
 * <li>修 改 人:</li>
 * <li>修改内容:</li>
 * </ul>
 *
 * @author Huzb
 * @version 1.0.0
 */

public class BigDecimalUtil {

    /**
     * 千位分隔符方便查看金额具体大小
     */
    public static final String FORMAT_COMMA = "#,###,###,###,###,###,##0.00";
    /**
     * 不带千位分隔符
     */
    public static final String AMOUNT_FORMAT = "##################0.00";
    /**
     * 100常量
     */
    public static final String HUNDRED = "100";
    /**
     * 保留两位小数
     */
    public static final int AMOUNT_SCALE = 2;
    /**
     * 运算精度
     */
    private static final int DEF_DIV_SCALE = 10;

    /**
     * 这个类不能实例化
     */
    private BigDecimalUtil() {
    }

    /**
     * 高精度加法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2);
    }

    /**
     * 高精度减法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal sub(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2);
    }

    /**
     * 高精度乘法
     *
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal mul(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.multiply(b2);
    }

    /**
     * 高精度除法
     *
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static BigDecimal div(String v1, String v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 保留小数位
     *
     * @param v
     * @param scale
     * @return
     */
    public static BigDecimal round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
    }


    /**
     * 截取两位小数
     *
     * @param v
     * @return
     */
    public static BigDecimal amountFormat(String v){
        DecimalFormat df = new DecimalFormat(AMOUNT_FORMAT);
        return new BigDecimal(df.format(v));
    }

    /**
     * 分转换成元
     *
     * @param v
     * @return
     */
    public static BigDecimal penny2dollar(String v) {
        BigDecimal s = div(v, "100", 2);//保留两位小数
        return s;
    }

    /**
     * 元转换成分
     *
     * @param v
     * @return
     */
    public static BigDecimal dollar2penny(String v) {
        return mul(v, "100");
    }

    /**
     * 格式化金额
     * 千位分隔符 方便查看金额具体大小 FORMAT_COMMA = "#,###,###,###,###,###,##0.00"
     * 精确两位小数 .99 -> 0.99
     * 1111111.985 -> 1,111,111.99
     *
     * @param v
     * @return
     */
    public static String formatNumber(String v) {
        return formatNumber(v, FORMAT_COMMA);
    }

    /**
     * 格式化金额
     *
     * @param v
     * @param pattern BigDecimalUtil类中的常量
     * @return
     */
    public static String formatNumber(String v, String pattern) {
        return new DecimalFormat(pattern).format(new BigDecimal(v));
    }

    /**
     * 提供精确的小数位四舍五入处理。
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static BigDecimal round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 判断num1是否小于num2
     *
     * @param num1
     * @param num2
     * @return num1小于num2返回true
     */
    public static boolean lessThan(BigDecimal num1, BigDecimal num2) {
        return num1.compareTo(num2) == -1;
    }

    /**
     * 判断num1是否小于等于num2
     *
     * @param num1
     * @param num2
     * @return num1小于或者等于num2返回true
     */
    public static boolean lessEqual(BigDecimal num1, BigDecimal num2) {
        return (num1.compareTo(num2) == -1) || (num1.compareTo(num2) == 0);
    }

    /**
     * 判断num1是否大于num2
     *
     * @param num1
     * @param num2
     * @return num1大于num2返回true
     */
    public static boolean greaterThan(BigDecimal num1, BigDecimal num2) {
        return num1.compareTo(num2) == 1;
    }

    /**
     * 判断num1是否大于等于num2
     *
     * @param num1
     * @param num2
     * @return num1大于或者等于num2返回true
     */
    public static boolean greaterEqual(BigDecimal num1, BigDecimal num2) {
        return (num1.compareTo(num2) == 1) || (num1.compareTo(num2) == 0);
    }

    /**
     * 判断num1是否等于num2
     *
     * @param num1
     * @param num2
     * @return num1等于num2返回true
     */
    public static boolean equal(BigDecimal num1, BigDecimal num2) {
        return num1.compareTo(num2) == 0;
    }

}
