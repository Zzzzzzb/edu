package com.zhengzb.edu.mybatis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <ul>
 * <li>文件名称: UserInfo</li>
 * <li>文件描述: 自动生成</li>
 * <li>版权所有: 版权所有(C) 2020</li>
 * <li>公   司: 厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要: </li>
 * <li>其他说明: </li>
 * <li>创建日期: 2020-11-15 17:23:28</li>
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
 * @author zhengzb
 * @version 1.0.0
 */
@ApiModel
@Data
@EqualsAndHashCode
@TableName(value = "user_info")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 人员ID
     */
	@TableId(type = IdType.AUTO)
    @ApiModelProperty("人员ID")
    private Long userId;
    /**
     * 姓名
     */
    @ApiModelProperty("姓名")
    private String name;
    /**
     * 性别*
     */
    @ApiModelProperty("性别*")
    private String sex;
    /**
     * 证件类型*
     */
    @ApiModelProperty("证件类型*")
    private String idType;
    /**
     * 证件号码
     */
    @ApiModelProperty("证件号码")
    private String idNo;
    /**
     * 出生日期
     */
    @ApiModelProperty("出生日期")
    private String birthday;
    /**
     * 拼音助记码
     */
    @ApiModelProperty("拼音助记码")
    private String pyMnemonic;
    /**
     * 五笔助记码
     */
    @ApiModelProperty("五笔助记码")
    private String wbMnemonic;
    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String telphone;
    /**
     * 有效标志*
     */
    @ApiModelProperty("有效标志*")
    private String validFlag;
    /**
     * 户口所在地
     */
    @ApiModelProperty("户口所在地")
    private String censusRegisterAddr;
    /**
     * 地址
     */
    @ApiModelProperty("地址")
    private String address;
    /**
     * 就诊人实名认证标志*
     */
    @ApiModelProperty("就诊人实名认证标志*")
    private String authFlag;
    /**
     * 注册来源*
     */
    @ApiModelProperty("注册来源*")
    private String registeredSource;
    /**
     * 经办日期
     */
    @ApiModelProperty("经办日期")
    private LocalDateTime date;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

	/**
	 * 注册来源*
	 */
	@ApiModelProperty("姓名脱敏*")
	private String nameFuzzy;
	/**
	 * 经办日期
	 */
	@ApiModelProperty("证件号码脱敏")
	private String idNoFuzzy;
	/**
	 * 备注
	 */
	@ApiModelProperty("联系电话脱敏")
	private String telphoneFuzzy;




}
