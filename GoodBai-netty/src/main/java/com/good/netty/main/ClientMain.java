package com.good.netty.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.good.netty.common.CInterfaceConfig;
import com.good.netty.common.InterfaceFactory;
import com.good.netty.entity.body.ConeConfigInit;

/**
 * Created by mag on 2017/07/14. C1 客户端 测试类
 */
public class ClientMain {

	public static void main(String[] args) {
		InputStreamReader inputStream = new InputStreamReader(System.in);
		BufferedReader keyboardInput = new BufferedReader(inputStream);
		String input = null;
		try {
			CInterfaceConfig config = new CInterfaceConfig();
			ConeConfigInit init = new ConeConfigInit("10.0.6.135", 1234);
			init.setTimeLimit(2);
			config.initServer(init);
			config.enbaleServer();
			InterfaceFactory factory = config.getInterfaceFactory();
			boolean flag = true;
			while (flag) {
				input = keyboardInput.readLine(); // 控制台输入命令 测试
				try {
					switch (input) {
					case "001":
						factory.Login("admin", "12345admin");
						System.out.println("");
						break;
					case "bye":
						flag = false;
						break;
					default:
						System.out.println("Unknown command!");
						break;
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
			config.disableServer();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
