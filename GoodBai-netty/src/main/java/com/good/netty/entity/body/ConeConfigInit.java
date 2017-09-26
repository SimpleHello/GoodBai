package com.good.netty.entity.body;

import java.io.Serializable;

/**
 * 设置 config 服务参数
 * @author John
 *
 */
public class ConeConfigInit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4714288116883110703L;
	
	private String host; //IP
	private int port; //端口
	private int timeLimit; // 单位 秒 ; netty 发送消息等待消息响应时间 ;默认 10秒
	private int reconnectTime;// 单位秒 ; FSU 重连间隔时间 ;默认10 秒
	private int disConnectTime;//单位秒; FSU 超时产生告警;默认10分钟
	
	public ConeConfigInit(){}
	/**
	 * @param host
	 * @param port
	 * @param timeLimit
	 * @param reconnectTime
	 * @param disConnectTime
	 */
	public ConeConfigInit(String host, int port, int timeLimit, int reconnectTime, int disConnectTime) {
		this.host = host;
		this.port = port;
		this.timeLimit = timeLimit;
		this.reconnectTime = reconnectTime;
		this.disConnectTime = disConnectTime;
	}
	
	public ConeConfigInit(String host, int port) {
		this.host = host;
		this.port = port;
		this.timeLimit = 10;
		this.reconnectTime = 10;
		this.disConnectTime = 600;
	}
	
	
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(int timeLimit) {
		this.timeLimit = timeLimit;
	}
	public int getReconnectTime() {
		return reconnectTime;
	}
	public void setReconnectTime(int reconnectTime) {
		this.reconnectTime = reconnectTime;
	}
	public int getDisConnectTime() {
		return disConnectTime;
	}
	public void setDisConnectTime(int disConnectTime) {
		this.disConnectTime = disConnectTime;
	}
	@Override
	public String toString() {
		return "ConeConfigInit [host=" + host + ", port=" + port + ", timeLimit=" + timeLimit + ", reconnectTime="
				+ reconnectTime + ", disConnectTime=" + disConnectTime + "]";
	}
	
	
	
	
	
	
}
