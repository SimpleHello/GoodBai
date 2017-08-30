package com.good.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.good.entity.system.Permission;
import com.good.entity.system.Role;
import com.good.entity.system.User;
import com.good.shiro.TokenManager;

@Controller
public class UserLoginController {

	@RequestMapping("/login/loginsubmit.do")
	public ModelAndView queryAll(User user, ModelMap modelMap) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("获取了数据:" + user.getUsername() + " password:" + user.getPassword());
		TokenManager.login(user,false);
		return new ModelAndView("index2");
	}

	@RequestMapping("/login/success.do")
	public ModelAndView success(ModelMap modelMap) throws Exception {
		System.out.println("登录成功之后 跳转到此地");
		modelMap.put("total", 1);
		modelMap.put("rows", null);
		modelMap.put("page", 2);
		return new ModelAndView("index2");
	}

}
