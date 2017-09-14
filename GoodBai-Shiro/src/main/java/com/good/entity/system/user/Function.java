package com.good.entity.system.user;

import com.good.base.IEntity;

public class Function  extends IEntity{

    /**
	 * 
	 */
	private static final long serialVersionUID = 8095915721286328248L;
	
	private String name ;
	private String descript;
	private int parentId;
	private String url ;
    private Byte type ;         //权限类型 0一级菜单 1二级菜单 2功能菜单(功能菜单可以没有路径)
    private Integer orderNo ;      //菜单排序号，关系用户登录时候，关系到首页显示具体哪个菜单
    
    /**
     * 查询用
     */
    private int roleId;
    private int userId;
    private int pageLimit;
    private int pageSize;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Byte getType() {
		return type;
	}
	public void setType(Byte type) {
		this.type = type;
	}
	public Integer getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
	
    
   
    
    
    
}
