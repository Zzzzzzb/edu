package com.zhengzb.edu.code.userinfo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.collect.Maps;
import com.zhengzb.edu.code.userinfo.req.AccountInfoQueryReq;
import com.zhengzb.edu.code.userinfo.req.AccountRegisterReq;
import com.zhengzb.edu.code.userinfo.req.BasicUserInfoQueryReq;
import com.zhengzb.edu.code.userinfo.req.PersonRegisterReq;
import com.zhengzb.edu.code.userinfo.resp.AccountRegisterResp;
import com.zhengzb.edu.code.userinfo.resp.BasicUserInfoQueryResp;
import com.zhengzb.edu.code.userinfo.resp.PersonRegisterResp;
import com.zhengzb.edu.code.userinfo.service.UserBusinessService;
import com.zhengzb.edu.common.constant.MedicalSerCodeMsg;
import com.zhengzb.edu.common.exception.ApiException;
import com.zhengzb.edu.common.result.Result;
import com.zhengzb.edu.mybatis.dto.UserAccountInfoDTO;
import com.zhengzb.edu.mybatis.entity.UserAccountInfo;
import com.zhengzb.edu.mybatis.entity.UserInfo;
import com.zhengzb.edu.mybatis.mapper.UserInfoMapper;
import com.zhengzb.edu.mybatis.service.UserAccountInfoService;
import com.zhengzb.edu.mybatis.service.UserInfoService;
import com.zhengzb.edu.utils.util.CnToPY;
import com.zhengzb.edu.utils.util.CnToWB;
import com.zhengzb.edu.utils.util.EncryptUtils;
import com.zhengzb.edu.utils.util.IdCardUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * <ul>
 * <li>文件名称: UserBusinessServiceImpl</li>
 * <li>文件描述: 用户信息</li>
 * <li>版权所有: 版权所有(C) 2020</li>
 * <li>公   司: 厦门云顶伟业信息技术有限公司</li>
 * <li>内容摘要: </li>
 * <li>其他说明: </li>
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
@Service
public class UserBusinessServiceImpl implements UserBusinessService {

	@Autowired
	private UserAccountInfoService userAccountInfoService;

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private UserInfoMapper userInfoMapper;


	@Override
	public Result<PersonRegisterResp> personRegister(PersonRegisterReq req) {

		//新增用户信息表
		QueryWrapper<UserInfo> userInfoQueryWrapper = new QueryWrapper<>();
		userInfoQueryWrapper.eq("id_type", req.getIdType()).eq("id_no", EncryptUtils.encryptBody(req.getIdNo()));
		UserInfo userInfo = userInfoService.getOne(userInfoQueryWrapper);
		if (userInfo == null) {
			userInfo = new UserInfo();
			userInfo.setName(req.getName());
			userInfo.setNameFuzzy(IdCardUtils.nameDesensitization(req.getName()));
			userInfo.setSex(IdCardUtils.getSexByIdNo(req.getIdNo()));
			userInfo.setIdType(req.getIdType());
			userInfo.setIdNo(EncryptUtils.encryptBody(req.getIdNo()));
			userInfo.setIdNoFuzzy(IdCardUtils.idEncrypt(req.getIdNo()));
			userInfo.setBirthday(IdCardUtils.getBirthdayByIdNo(req.getIdNo()));
			userInfo.setPyMnemonic(CnToPY.getPinYinHeadChar(req.getName()));
			userInfo.setWbMnemonic(CnToWB.getWBCode(req.getName()));
			userInfo.setTelphone(req.getTelphone());
			userInfo.setTelphoneFuzzy(IdCardUtils.mobileEncrypt(req.getTelphone()));
			userInfo.setValidFlag("1");
			userInfo.setCensusRegisterAddr(req.getCensusRegisterAddr());
			userInfo.setAddress(req.getAddress());
			userInfo.setAuthFlag("0");
			//1人员信息注册//2添加就诊人注册
			userInfo.setRegisteredSource("1");
			userInfo.setDate(LocalDateTime.now());
		} else {
			BeanUtil.copyProperties(req, userInfo);
			userInfo.setIdNo(EncryptUtils.encryptBody(req.getIdNo()));
		}
		userInfoService.saveOrUpdate(userInfo);
		PersonRegisterResp resp = new PersonRegisterResp();
		resp.setUserId(userInfo.getUserId());
		return Result.success(resp);
	}

	@Override
	public Result<AccountRegisterResp> accountRegister(AccountRegisterReq req) {
		AccountRegisterResp resp = new AccountRegisterResp();
		QueryWrapper<UserAccountInfo> userAccountInfoQueryWrapper = new QueryWrapper<>();
		userAccountInfoQueryWrapper.eq("access_id", req.getAccessId()).eq("access_type", req.getAccessType());
		UserAccountInfo userAccountInfo = userAccountInfoService.getOne(userAccountInfoQueryWrapper);

		if (req.getUserId() != null) {
			UserInfo userInfo = userInfoService.getById(req.getUserId());
			if (userInfo == null) {
				return Result.error(MedicalSerCodeMsg.ERROR_109702);
			}
		}

		if (userAccountInfo == null) {
			userAccountInfo = new UserAccountInfo();
			userAccountInfo.setConnectedAccountId(UUID.randomUUID().toString().replace("-", ""));
			userAccountInfo.setAccessId(req.getAccessId());
			userAccountInfo.setAccessType(req.getAccessType());
			userAccountInfo.setUserId(StringUtils.isNotBlank(req.getUserId()) ? Long.valueOf(req.getUserId()) : null);
			userAccountInfo.setMobilePhone(req.getMobilePhone());
			userAccountInfo.setPassword(req.getPassword());
			userAccountInfo.setNickname(req.getNickname());
			userAccountInfo.setUserPhoto(req.getUserPhoto());
			userAccountInfo.setBindTime(LocalDateTime.now());
			userAccountInfo.setAuthFlag("0");
			userAccountInfo.setValidFlag("1");
			userAccountInfo.setRemark("账号注册");
		} else {
			BeanUtil.copyProperties(req, userAccountInfo, CopyOptions.create().setIgnoreNullValue(true));
		}
		userAccountInfoService.saveOrUpdate(userAccountInfo);
		resp.setAccountId(userAccountInfo.getId());
		return Result.success(resp);
	}

	@Override
	public Result<UserAccountInfoDTO> accountInfoQuery(AccountInfoQueryReq req) {
		if (StringUtils.isBlank(req.getAccessId()) && StringUtils.isBlank(req.getAccountId())) {
			throw new ApiException(MedicalSerCodeMsg.ERROR_109703);
		}
		if (StringUtils.isNotBlank(req.getAccessId()) && StringUtils.isBlank(req.getAccessType())) {
			throw new ApiException(MedicalSerCodeMsg.ERROR_109704);
		}
		QueryWrapper<UserAccountInfo> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotBlank(req.getAccessId())) {
			queryWrapper.eq("access_id", req.getAccessId());
			queryWrapper.eq("access_type", req.getAccessType());
		}
		if (StringUtils.isNotBlank(req.getAccountId())) {
			queryWrapper.eq("id", req.getAccountId());
		}
		List<UserAccountInfo> userAccountInfos = userAccountInfoService.list(queryWrapper);
		if (userAccountInfos.size() > 1) {
			throw new ApiException(MedicalSerCodeMsg.ERROR_109705);
		}
		if (userAccountInfos.size() == 0) {
			throw new ApiException(MedicalSerCodeMsg.ERROR_109706);
		}
		UserAccountInfo userAccountInfo = userAccountInfos.get(0);
		UserAccountInfoDTO dto = new UserAccountInfoDTO();
		BeanUtil.copyProperties(userAccountInfo, dto);
		return Result.success(dto);
	}


	@Override
	public Result<Map<Long, BasicUserInfoQueryResp>> basicUserInfoQuery(BasicUserInfoQueryReq req) {
		QueryWrapper<UserInfo> queryWrapper = new QueryWrapper<>();
		queryWrapper.in("user_id", req.getUserIds());
		List<UserInfo> userInfos = userInfoMapper.selectList(queryWrapper);
		Map<Long, BasicUserInfoQueryResp> map = Maps.newHashMap();
		userInfos.stream().forEach(x -> {
			BasicUserInfoQueryResp resp = new BasicUserInfoQueryResp();
			BeanUtil.copyProperties(x, resp);
			map.put(resp.getUserId(), resp);
		});
		return Result.success(map);
	}
}

