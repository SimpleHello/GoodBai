package com.good.server.base;

import java.io.Serializable;
import java.util.Date;

public abstract class IEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1238767244280796186L;
	
	private int id;
	private Date ctime;
	private Byte enable; //数据有效性 1 有效 0 无效 
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Byte getEnable() {
		return enable;
	}

	public void setEnable(Byte enable) {
		this.enable = enable;
	}
	
	
}

