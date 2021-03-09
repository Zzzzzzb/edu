package com.zhengzb.edu.code.userinfo.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class BasicUserInfoQueryReq implements Serializable {

	@ApiModelProperty("人员ID")
	@NotNull(message = "人员ID不能为空")
	private List<Long> userIds;

}
