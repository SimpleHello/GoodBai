package com.good.entity.system.user;

import java.util.List;

import com.good.base.IEntity;

public class Role extends IEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 304340613044767686L;

	private String name ;
    private boolean manager;
	private List<Function> funcList;
	 /**
     * 查询用
     */
    private int userId;
    private int pageLimit;
    private int pageSize;
    
    public Role(){}
    
    public Role(String name){
    	this.name = name;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public List<Function> getFuncList() {
		return funcList;
	}

	public void setFuncList(List<Function> funcList) {
		this.funcList = funcList;
	}

	public boolean isManager() {
		return manager;
	}

	public void setManager(boolean manager) {
		this.manager = manager;
	}

	
    
    
}
