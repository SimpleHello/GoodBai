package com.good.netty.udp.common;

import com.good.netty.udp.io.UdpClient;

/**
 * 客户端发送 命令 类
 * @author John
 *
 */
public class UdpFactory {

	private UdpClient client;

	public UdpFactory(UdpClient client){
		this.client = client;
	}
	/**
	 * 远程控制开门
	 */
	public boolean OpenDoor(String descAddr, String srcAddr, byte subDevAddr,byte ver, byte adr)  throws Exception{
		client.OpenDoor(descAddr,srcAddr,subDevAddr,ver,adr);
		return true;
	}
		
}
