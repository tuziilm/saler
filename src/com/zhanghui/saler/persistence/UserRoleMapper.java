package com.zhanghui.saler.persistence;

import com.zhanghui.saler.domain.UserRole;

/**
 * ibatis����ϵͳ�û�Ȩ�ޱ��Mapper�ӿ�
 */
public interface UserRoleMapper extends BaseMapper<UserRole>{
	UserRole selectByUserId(Integer userId);
}