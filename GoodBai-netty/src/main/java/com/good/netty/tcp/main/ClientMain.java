package com.good.netty.tcp.main;


import com.good.netty.tcp.common.CInterfaceConfig;
import com.good.netty.tcp.common.InterfaceFactory;
import com.good.netty.tcp.entity.body.ConeConfigInit;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by mag on 2017/07/14.
 * C1 客户端 测试类
 */
public class ClientMain {

	public static void main(String[] args) {
		InputStreamReader inputStream = new InputStreamReader(System.in);
		BufferedReader keyboardInput = new BufferedReader(inputStream);
		try {
			CInterfaceConfig config = new CInterfaceConfig();
			ConeConfigInit init = new ConeConfigInit("10.0.5.35", 1234);
			init.setTimeLimit(2);
			config.initServer(init);//本地 测试 服务开启 = ServerMain 5秒重连
			config.enbaleServer();
			InterfaceFactory factory = config.getInterfaceFactory();
			boolean flag = true;

            while (flag) {
				Thread.sleep(2000);
				factory.Login("123","123");
            	System.out.println("发送数据成功=================");
//				String input = keyboardInput.readLine(); //控制台输入命令 测试
//            	try{
//            		switch (input) {
//            		case "login":
//						factory.Login("123","123");
//            			System.out.println("返回结果>>");
//            			break;
//            		case "fail":
//            			System.out.println("返回结果>>");
//            			break;
//            		case "bye":
//            			flag = false;
//            			break;
//            		default:
//    					System.out.println("Unknown command!");
//    					break;
//    				}
//            	}catch(Exception e){
//            		System.out.println(e.getMessage());
//            		e.printStackTrace();
//            	}
            	
            	}
            config.disableServer();
            
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
