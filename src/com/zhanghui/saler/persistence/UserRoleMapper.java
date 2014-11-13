package com.zhanghui.saler.persistence;

import com.zhanghui.saler.domain.UserRole;

/**
 * ibatis操作系统用户权限表的Mapper接口
 */
public interface UserRoleMapper extends BaseMapper<UserRole>{
	UserRole selectByUserId(Integer userId);
}