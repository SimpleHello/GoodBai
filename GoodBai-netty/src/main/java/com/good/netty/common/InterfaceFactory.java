package com.good.netty.common;

import com.good.netty.entity.body.PacketType;
import com.good.netty.entity.model.Login;
import com.good.netty.io.server.Client;

/**
 * 客户端发送 命令 类
 * @author John
 *
 */
public class InterfaceFactory {

	private Client client;
	
	public InterfaceFactory(Client client){
		this.client = client;
	}
	/**
	 * 101 用户登录 - 登陆
	 */
	public boolean Login(String userName,String password)  throws Exception{
		Login login = new Login(userName,password);
		client.login(login, PacketType.LOGIN);
		return true;
	}
		
}
