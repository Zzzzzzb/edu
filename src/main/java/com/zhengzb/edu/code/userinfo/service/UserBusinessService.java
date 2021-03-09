package com.zhengzb.edu.code.userinfo.service;

import com.zhengzb.edu.code.userinfo.req.AccountInfoQueryReq;
import com.zhengzb.edu.code.userinfo.req.AccountRegisterReq;
import com.zhengzb.edu.code.userinfo.req.BasicUserInfoQueryReq;
import com.zhengzb.edu.code.userinfo.req.PersonRegisterReq;
import com.zhengzb.edu.code.userinfo.resp.AccountRegisterResp;
import com.zhengzb.edu.code.userinfo.resp.BasicUserInfoQueryResp;
import com.zhengzb.edu.code.userinfo.resp.PersonRegisterResp;
import com.zhengzb.edu.common.result.Result;
import com.zhengzb.edu.mybatis.dto.UserAccountInfoDTO;

import java.util.Map;

public interface UserBusinessService {


	/*
	 *
	 * 人员信息注册
	 * @param req
	 * @return YdResultp
	 */
	Result<PersonRegisterResp> personRegister(PersonRegisterReq req);


	/*
	 *
	 * 账号注册
	 * @param req
	 * @return YdResult
	 */
	Result<AccountRegisterResp> accountRegister(AccountRegisterReq req);


	/*
	 *
	 * 账号查询
	 * @param req
	 * @return YdResult
	 */
	Result<UserAccountInfoDTO> accountInfoQuery(AccountInfoQueryReq req);


	/**
	 * 人员基础信息查询，获取脱敏人员信息
	 *
	 * @param req
	 * @return
	 */
	Result<Map<Long, BasicUserInfoQueryResp>> basicUserInfoQuery(BasicUserInfoQueryReq req);


}