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
 * <li>文件名称: UserAccountInfo</li>
 * <li>文件描述: 自动生成</li>
 * <li>版权所有: 版权所有(C) 2020</li>
 * <li>公   司: 厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要: </li>
 * <li>其他说明: </li>
 * <li>创建日期: 2020-11-15 17:24:01</li>
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
@TableName(value = "user_account_info")
public class UserAccountInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	@TableId(type = IdType.AUTO)
    @ApiModelProperty("ID")
    private Long id;
    /**
     * 关联账户标识
     */
    @ApiModelProperty("关联账户标识")
    private String connectedAccountId;
    /**
     * 账户号
     */
    @ApiModelProperty("账户号")
    private String accessId;
    /**
     * 账户类型*
     */
    @ApiModelProperty("账户类型*")
    private String accessType;
    /**
     * 人员ID
     */
    @ApiModelProperty("人员ID")
    private Long userId;
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
    /**
     * 绑定时间
     */
    @ApiModelProperty("绑定时间")
    private LocalDateTime bindTime;
    /**
     * 账户实名认证标志*
     */
    @ApiModelProperty("账户实名认证标志*")
    private String authFlag;
    /**
     * 有效标志*
     */
    @ApiModelProperty("有效标志*")
    private String validFlag;
    /**
     * 备注
     */
    @ApiModelProperty("备注")
    private String remark;

}
