package com.tcf.kid.smart.framework.mapper;

import com.tcf.kid.smart.framework.entity.UserInfo;

/***
 * TODO TCF 用户信息映射器接口
 * @author 71485
 *
 */
public interface UserInfoMapper {

	//TODO TCF 根据主键id查询用户信息
	public UserInfo selectById(String id);
	
}
