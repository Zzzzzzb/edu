package com.zhengzb.edu.code.userinfo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class AccountRegisterReq implements Serializable {

	/**
	 * 账户号
	 */
	@ApiModelProperty("账户号")
	@NotBlank(message = "账户号不能为空")
	private String accessId;
	/**
	 * 账户类型*
	 */
	@ApiModelProperty("账户类型*")
	@NotBlank(message = "账户类型不能为空")
	private String accessType;
	/**
	 * 人员ID
	 */
	@ApiModelProperty("人员ID")
	private String userId;
	/**
	 * 手机号(登陆手机号)
	 */
	@ApiModelProperty("手机号(登陆手机号)")
	private String mobilePhone;
	/**
	 * 密码
	 */
	@ApiModelProperty("密码")
	private String password;
	/**
	 * 昵称
	 */
	@ApiModelProperty("昵称")
	private String nickname;
	/**
	 * 头像地址
	 */
	@ApiModelProperty("头像地址")
	private String userPhoto;

}




