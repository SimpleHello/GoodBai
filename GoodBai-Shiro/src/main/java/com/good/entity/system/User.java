package com.good.entity.system;

import java.util.Date;
import java.util.List;

import com.good.base.IEntity;

public class User extends IEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = -1146628034025928299L;
	
	private String usename ;
    private String password ;
    private String nickName ;
    private String mobilePhone ;
    private String email ;
    private Byte isDeleted ;
    private Date createTime ;
    private List<Role> roles ;
    
    

	
	
	public String getUsename() {
		return usename;
	}
	public void setUsename(String usename) {
		this.usename = usename;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Byte getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Byte isDeleted) {
		this.isDeleted = isDeleted;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
    
    
    
}
