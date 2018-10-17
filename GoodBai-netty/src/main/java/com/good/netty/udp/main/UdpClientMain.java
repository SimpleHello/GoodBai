package com.good.netty.udp.main;

import com.good.netty.udp.common.UdpClientConfig;
import com.good.netty.udp.common.UdpConfigInit;
import com.good.netty.udp.common.UdpFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by mag on 2017/07/14. C1 客户端 测试类
 */
public class UdpClientMain {

	public static void main(String[] args) {
		InputStreamReader inputStream = new InputStreamReader(System.in);
		BufferedReader keyboardInput = new BufferedReader(inputStream);
		String input = null;
		try {
			UdpClientConfig config = new UdpClientConfig();
			UdpConfigInit init = new UdpConfigInit("10.0.6.111", 1331);
			config.initServer(init);
			config.enbaleServer();
			UdpFactory factory = config.getUdpFactory();
			boolean flag = true;
			while (flag) {
				input = keyboardInput.readLine(); // 控制台输入命令 测试
				try {
					switch (input) {
					case "001":
						String descAddr = "";//20bytes	目标设备地址
						String srcAddr ="00";//8byte	源设备地址
						byte subDevAddr = 0x03;
						byte ver = 0x10;
						byte adr = 0x01;
						String str  = factory.OpenDoor(descAddr,srcAddr,subDevAddr,ver,adr);
						System.out.println("hello:"+ str);
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
