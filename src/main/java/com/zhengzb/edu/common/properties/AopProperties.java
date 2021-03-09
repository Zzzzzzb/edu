package com.zhengzb.edu.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <ul>
 * <li>文件名称: AopProperties</li>
 * <li>文件描述: </li>
 * <li>版权所有: 版权所有(C) 2018</li>
 * <li>公   司: 厦门市云顶伟业信息技术有限公司<>
 * <li>内容摘要: <0/li>
 * <li>其他说明: </li>
 * <li>创建日期: 2020-11-16 20:41<>
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
@Component
@ConfigurationProperties(prefix = "director.wx.aop")
@Data
public class AopProperties {

    /** 是否检查token */
    private Boolean checkToken = true;
    /** 是否检查患者his编码 */
    private Boolean checkHosPatientId = true;
    /** 是否记录每日活跃人数 */
    private Boolean enableSaveActive;
}
