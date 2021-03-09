package com.zhengzb.edu.code.userinfo.resp;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class PersonRegisterResp implements Serializable {

	@ApiModelProperty("用户ID")
	private Long userId;

}
