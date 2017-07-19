package com.good.entity.system;

import java.util.Date;
import java.util.List;

import com.good.base.IEntity;

public class User extends IEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = -1146628034025928299L;
	
	private String id;
	private String username ;
    private String password ;
    private String nickName ;
    private String mobilePhone ;
    private String email ;
    private Byte isDeleted ;
    private Date createTime ;
    private List<Role> roles ;
    
    
    public User(){}
    
    public User(User user){
    	this.id= user.getId();
    	this.username = user.getUsername();
    	this.password = user.getPassword();
    	this.nickName = user.getNickName();
    	this.mobilePhone = user.getMobilePhone();
    	this.email = user.getEmail();
    	this.isDeleted = user.getIsDeleted();
    	this.createTime = user.getCreateTime();
    	this.roles = user.getRoles();
    }
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
