package com.zhengzb.edu.code.userinfo.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <pre>
 *
 * 文件名：UserLoginReq
 * 创建时间：2020/11/11
 * 文件说明：无
 * 版本：v1.0.0
 *
 * </pre>
 *
 * @author xieyy
 */

@ApiModel("用户信息")
@Data
public class AccountInfoResp  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 账户token
     */
    @ApiModelProperty(value = "账户token", position = 1)
    private String token;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称", position = 2)
    private String nickName;
    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址", position = 3)
    private String headImgUrl;
    /**
     * 是否实名认证
     */
    @ApiModelProperty(value = "是否实名认证", position = 4)
    private String authFlag;
    
}
