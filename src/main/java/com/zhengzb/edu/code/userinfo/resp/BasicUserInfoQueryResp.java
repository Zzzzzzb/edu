package com.zhengzb.edu.code.userinfo.resp;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BasicUserInfoQueryResp implements Serializable {

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
	 * 出生日期
	 */
	@ApiModelProperty("出生日期")
	private String birthday;

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


	@ApiModelProperty("姓名脱敏*")
	private String nameFuzzy;
	@ApiModelProperty("证件号码脱敏")
	private String idNoFuzzy;

	@ApiModelProperty("联系电话脱敏")
	private String telphoneFuzzy;
}
