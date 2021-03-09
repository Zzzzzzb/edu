package com.zhengzb.edu.code.userinfo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class PersonRegisterReq implements Serializable {


	/**
	 * 姓名
	 */
	@ApiModelProperty("姓名")
	@NotBlank(message = "姓名不能为空")
	private String name;
	/**
	 * 证件类型*
	 */
	@ApiModelProperty("证件类型*")
	@NotBlank(message = "证件类型不能为空")
	private String idType;
	/**
	 * 证件号码
	 */
	@ApiModelProperty("证件号码")
	@NotBlank(message = "证件号码不能为空")
	private String idNo;


	/**
	 * 联系电话
	 */
	@ApiModelProperty("联系电话")
	@NotBlank(message = "手机号不能为空")
	@Pattern(regexp = "^1[356789]\\d{9}$", message = "手机号格式错误")
	private String telphone;

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




}



