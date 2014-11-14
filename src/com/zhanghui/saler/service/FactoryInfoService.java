package com.zhanghui.saler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhanghui.saler.domain.FactoryInfo;
import com.zhanghui.saler.persistence.FactoryInfoMapper;

/**
 * ϵͳ�û�Ȩ�޲���������
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
