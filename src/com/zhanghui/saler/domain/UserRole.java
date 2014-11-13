package com.zhanghui.saler.domain;

/**
 * 系统用户权限表
 */
public class UserRole  extends RemarkId {
    /** 用户ID*/
    private Integer userId;
    /** 数据录入权限 0无 1有 */
    private Integer dataEntry;
    /** 数据管理权限 0无 1有 */
    private Integer dataManage;
    /** 销售查询权限 0无 1有 */
    private Integer saleQuery;
    /** 用户管理权限 0无 1有 */
    private Integer userManage;
    /** imei查询权限 0无 1有 */
    private Integer imeiQuery;
    
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getDataEntry() {
		return dataEntry;
	}
	public void setDataEntry(Integer dataEntry) {
		this.dataEntry = dataEntry;
	}
	public Integer getDataManage() {
		return dataManage;
	}
	public void setDataManage(Integer dataManage) {
		this.dataManage = dataManage;
	}
	public Integer getSaleQuery() {
		return saleQuery;
	}
	public void setSaleQuery(Integer saleQuery) {
		this.saleQuery = saleQuery;
	}
	public Integer getUserManage() {
		return userManage;
	}
	public void setUserManage(Integer userManage) {
		this.userManage = userManage;
	}
	public Integer getImeiQuery() {
		return imeiQuery;
	}
	public void setImeiQuery(Integer imeiQuery) {
		this.imeiQuery = imeiQuery;
	}
}