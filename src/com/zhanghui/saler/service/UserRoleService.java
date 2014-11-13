package com.zhanghui.saler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhanghui.saler.domain.UserRole;
import com.zhanghui.saler.persistence.UserRoleMapper;

/**
 * ϵͳ�û�Ȩ�޲���������
 */
@Service
public class UserRoleService  extends SimpleCacheSupportService<UserRole> {
	private UserRoleMapper userRoleMapper;
	@Autowired
	public void setUserRoleMapper(UserRoleMapper userRoleMapper) {
		this.mapper = userRoleMapper;
		this.userRoleMapper=userRoleMapper;
	}

	public UserRole getByUserId(Integer userId) {
		return userRoleMapper.selectByUserId(userId);
	}
}
