package com.zhengzb.edu.utils.validation;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * <ul>
 * <li>文件名称：DicValidator</li>
 * <li>文件描述：暂无描述</li>
 * <li>版权所有：版权所有(C) 2016</li>
 * <li>公 司：厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要：</li>
 * <li>其他说明：</li>
 * <li>创建日期：2019年06月13日</li>
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

@Slf4j
public class DicValidator implements ConstraintValidator<Dic, String> {

    private String name;
    private static IDicValidator iDicValidator;

    public static void setiDicValidator(IDicValidator iDicValidator) {
        DicValidator.iDicValidator = iDicValidator;
    }

    @Override
    public void initialize(Dic constraintAnnotation) {
        this.name = constraintAnnotation.name();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // 为空，默认通过
        if (StringUtils.isEmpty(value)) {
            return true;
        }
        if (iDicValidator == null) {
            throw new IllegalArgumentException("字典值校验器不存在");
        }
        return iDicValidator.isValid(name, value);
    }
}
