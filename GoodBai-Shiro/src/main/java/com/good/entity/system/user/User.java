package com.good.entity.system.user;

import java.util.List;

import com.good.base.IEntity;

public class User extends IEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = -1146628034025928299L;
	
	private String name ;
    private String password ;
    
    private List<Role> roleList;
    /**
     * 查询用
     */
    private int pageLimit;
    private int pageSize;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPageLimit() {
		return pageLimit;
	}
	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
    
    
    
    
    
}
