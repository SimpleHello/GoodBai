package com.good.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.good.entity.system.user.User;
import com.good.shiro.ShiroToken;

@Controller
public class UserLoginController {

	@RequestMapping("/login/loginsubmit.do")
	public ModelAndView  queryAll(User user ,ModelMap modelMap) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("获取了数据:" + user.getName() + " password:" + user.getPassword());
		try{
			tokenLogin(user,false);
		}catch(UnknownAccountException e){
			System.out.println("帐号密码失败");
		}catch(Exception e){
			System.out.println("登录失败");
		}
		
		return new ModelAndView("main");
	}

	private void tokenLogin(User user,boolean rememberMe){
		ShiroToken token = new ShiroToken(user.getName(), user.getPassword());
		token.setRememberMe(rememberMe);
		SecurityUtils.getSubject().login(token);
	}
}
