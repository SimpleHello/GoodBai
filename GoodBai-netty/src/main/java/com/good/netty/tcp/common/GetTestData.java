package com.good.netty.tcp.common;

import com.good.netty.tcp.entity.model.Login;
import com.good.netty.tcp.entity.model.LoginAck;

public class GetTestData {
	
	
	 /**
     * 登录响应
     * @param login
     * @return
     */
    public static LoginAck getLoginAck(Login login){
    	String userName = login.getName();
    	String password = login.getPassword();
    	System.out.println(" userName:"+userName+" password:"+password);
    	return new LoginAck(2);
    }
    
}
