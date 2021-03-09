package com.zhengzb.edu.code.userinfo.service;



import com.zhengzb.edu.code.userinfo.resp.AccountInfoResp;
import com.zhengzb.edu.common.exception.ApiException;
import com.zhengzb.edu.common.result.Result;

import java.util.Map;

/**
 * <pre>
 *
 * 文件名：UserTokenService
 * 创建时间：2020/11/12
 * 文件说明：无
 * 版本：v1.0.0
 *
 * </pre>
 *
 * @author xieyy
 */

public interface AccountTokenService {
    
    /**
     * 获取/创建用户token
     *
     * @param identified  唯一标识
     * @param otherParams 需要token携带的其他参数
     * @return
     */
    Result<String> createToken(String identified, Map<String, String> otherParams) throws ApiException;
    
    /**
     * 根据用户token获取账户信息
     *
     * @param userToken
     * @return
     */
    Result<AccountInfoResp> getByToken(String userToken) throws ApiException;
    
}
