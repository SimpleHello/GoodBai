package com.good.controller;

import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.good.entity.system.User;
import com.good.shiro.ShiroToken;

@Controller
public class UserLoginController {

	@RequestMapping("/login/loginsubmit.do")
	public ModelAndView  queryAll(User user ,ModelMap modelMap) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("获取了数据:" + user.getUsename() + " password:" + user.getPassword());
		tokenLogin(user,false);
		return new ModelAndView("index2");
	}

	private void tokenLogin(User user,boolean rememberMe){
		ShiroToken token = new ShiroToken(user.getUsename(), user.getPassword());
		token.setRememberMe(rememberMe);
		SecurityUtils.getSubject().login(token);
	}
}
