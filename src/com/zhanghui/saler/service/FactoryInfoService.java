package com.zhanghui.saler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhanghui.saler.domain.FactoryInfo;
import com.zhanghui.saler.persistence.FactoryInfoMapper;

/**
 * 系统用户权限操作服务类
 */
@Service
public class FactoryInfoService  extends SimpleCacheSupportService<FactoryInfo> {
	private FactoryInfoMapper factoryInfoMapper;
	
	@Autowired
	public void setFactoryInfoMapper(FactoryInfoMapper factoryInfoMapper) {
		this.mapper = factoryInfoMapper;
		this.factoryInfoMapper=factoryInfoMapper;
	}
}
