package com.zhengzb.edu.code.userinfo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AccountInfoQueryReq implements Serializable {

	@ApiModelProperty("账号ID")
	private String accountId;

	@ApiModelProperty("账户号")
	private String accessId;

	@ApiModelProperty("账号类型(账户号不为空时账号类型不为空)")
	private String accessType;

}
