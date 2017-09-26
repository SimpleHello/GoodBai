package com.good.netty.common;

import com.good.netty.entity.model.Login;
import com.good.netty.entity.model.LoginAck;

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
