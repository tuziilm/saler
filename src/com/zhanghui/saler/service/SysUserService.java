package com.zhanghui.saler.service;

import com.zhanghui.saler.domain.SysUser;
import com.zhanghui.saler.persistence.SysUserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ϵͳ�û����ݲ���������
 * @author <a href="pangkunyi@gmail.com">Calvin Pang</a>
 *
 */
@Service
public class SysUserService  extends SimpleCacheSupportService<SysUser> {
	private SysUserMapper sysUserMapper;
	@Autowired
	public void setSysUserMapper(SysUserMapper sysUserMapper) {
		this.mapper = sysUserMapper;
		this.sysUserMapper=sysUserMapper;
	}

	public SysUser getByUsername(String username) {
		return sysUserMapper.getByUsername(username);
	}
}