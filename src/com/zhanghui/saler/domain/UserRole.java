package com.zhanghui.saler.domain;

/**
 * ϵͳ�û�Ȩ�ޱ�
 */
public class UserRole  extends RemarkId {
    /** �û�ID*/
    private Integer userId;
    /** ����¼��Ȩ�� 0�� 1�� */
    private Integer dataEntry;
    /** ���ݹ���Ȩ�� 0�� 1�� */
    private Integer dataManage;
    /** ���۲�ѯȨ�� 0�� 1�� */
    private Integer saleQuery;
    /** �û�����Ȩ�� 0�� 1�� */
    private Integer userManage;
    /** imei��ѯȨ�� 0�� 1�� */
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