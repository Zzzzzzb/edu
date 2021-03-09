package com.zhengzb.edu.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <ul>
 * <li>文件名称: AccCheckingIncident</li>
 * <li>文件描述: 对账事件信息</li>
 * <li>版权所有: 版权所有(C) 2020</li>
 * <li>公   司: 厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要: </li>
 * <li>其他说明: </li>
 * <li>创建日期: 2020年12月24日 </li>
 * <li>编辑工具: IntelliJ IDEA</li>
 * </ul>
 * <ul>
 * <li>修改记录: </li>
 * <li>版 本 号: </li>
 * <li>修改日期: </li>
 * <li>修 改 人:</li>
 * <li>修改内容:</li>
 * </ul>
 *
 * @author yeyj
 * @version 1.0.0
 */
@Data
@TableName(value = "acc_checking_incident")
public class AccCheckingIncident implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "商户订单号")
    private String outTradeNo;

    @ApiModelProperty(value = "交易类型 0：充值 1：退款")
    private String businessType;

    @ApiModelProperty(value = "支付渠道对账标志")
    private String accCheckingFlag;

    @ApiModelProperty(value = "支付渠道对账时间")
    private LocalDateTime accCheckingDate;

    @ApiModelProperty(value = "支付渠道异账处理类型")
    private String exceptionDisposeType;

    @ApiModelProperty(value = "支付渠道异账处理经办人")
    private String exceptionDisposeUserid;

    @ApiModelProperty(value = "异账处理时间")
    private LocalDateTime exceptionDisposeTime;

    @ApiModelProperty(value = "His对账标志")
    private String hisAccCheckingFlag;

    @ApiModelProperty(value = "His对账时间")
    private LocalDateTime hisAccCheckingDate;

    @ApiModelProperty(value = "His异账处理类型")
    private String hisExceptionDisposeType;

    @ApiModelProperty(value = "His异账处理经办人")
    private String hisExceptionDisposeUserid;

    @ApiModelProperty(value = "His异账处理时间")
    private LocalDateTime hisExceptionDisposeTime;
}
