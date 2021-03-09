package com.zhengzb.edu.code.userinfo.resp;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class AccountRegisterResp implements Serializable {

	@ApiModelProperty("账号ID")
	private Long accountId;

}
