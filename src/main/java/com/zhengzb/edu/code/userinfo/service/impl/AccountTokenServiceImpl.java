package com.zhengzb.edu.code.userinfo.service.impl;

import com.auth0.jwt.interfaces.DecodedJWT;

import com.zhengzb.edu.code.userinfo.req.AccountInfoQueryReq;
import com.zhengzb.edu.code.userinfo.resp.AccountInfoResp;
import com.zhengzb.edu.code.userinfo.service.AccountTokenService;
import com.zhengzb.edu.code.userinfo.service.UserBusinessService;
import com.zhengzb.edu.common.constant.GlobalConst;
import com.zhengzb.edu.common.constant.MedicalSerCodeMsg;
import com.zhengzb.edu.common.exception.ApiException;
import com.zhengzb.edu.common.result.Result;
import com.zhengzb.edu.mybatis.dto.UserAccountInfoDTO;
import com.zhengzb.edu.utils.util.Builder;
import com.zhengzb.edu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <pre>
 *
 * 文件名：AccountTokenServiceImpl
 * 创建时间：2020/11/12
 * 文件说明：无
 * 版本：v1.0.0
 *
 * </pre>
 *
 * @author xieyy
 */

@Service
public class AccountTokenServiceImpl implements AccountTokenService {
    

    @Autowired
    private UserBusinessService userBusinessService;
    
    /**
     * 获取/创建用户token
     *
     * @param identified  唯一标识
     * @param otherParams 需要token携带的其他参数
     * @return
     */
    @Override
    public Result<String> createToken(String identified, Map<String, String> otherParams) {
        return Result.success(JwtUtils.createToken(identified, otherParams));
    }
    
    /**
     * 根据用户token获取账户信息
     *
     * @param userToken
     * @return
     */
    @Override
    public Result<AccountInfoResp> getByToken(String userToken) {
        DecodedJWT jwt = JwtUtils.getJWT(userToken);
        if (!JwtUtils.validateToken(jwt)) {
            return Result.error(MedicalSerCodeMsg.ERROR_109701);
        }
        
        String userId = JwtUtils.getUserId(jwt);
        
        // 根据Token获取用户信息
        AccountInfoQueryReq accountInfoQueryReq = Builder.of(AccountInfoQueryReq::new)
                .with(AccountInfoQueryReq::setAccountId, userId)
                .build();
        Result<UserAccountInfoDTO> res = userBusinessService.accountInfoQuery(
                accountInfoQueryReq);
        if (res.isFailed()) {
            throw new ApiException(String.valueOf(res.getCode()), res.getMsg());
        }
        
        return Result.success(
                Builder.of(AccountInfoResp::new)
                        .with(AccountInfoResp::setNickName, res.getData().getNickname())// 昵称
                        .with(AccountInfoResp::setAuthFlag, res.getData().getAuthFlag())// 实名认证标志
                        .with(AccountInfoResp::setHeadImgUrl, res.getData().getUserPhoto())// 头像地址
                        .build()
        );
    }
}
