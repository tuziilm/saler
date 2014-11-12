package com.zhanghui.saler.domain;

/**
 * 系统用户表
 * @author <a href="pangkunyi@gmail.com">Calvin Pang</a>
 *
 */
public class SysUser  extends RemarkId {
    /** 账号*/
    private String username;
    /** 密码*/
    private String passwd;
    /** 真实姓名*/
    private String realname;
    /** 电话号码*/
    private String phonenum;
    /** 邮箱*/
    private String email;
    /** 部门*/
    private String department;
    /** 职位*/
    private String position;
    /** 状态,1:正常,0:异常*/
    private Byte status;
    private Integer privilege;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname == null ? null : realname.trim();
	}

	public String getPhonenum() {
		return phonenum;
	}

	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum == null ? null : phonenum.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department == null ? null : department.trim();
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position == null ? null : position.trim();
	}

	public Byte getStatus() {
		return status;
	}

	public void setStatus(Byte status) {
		this.status = status;
	}

	public Integer getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}
    
}