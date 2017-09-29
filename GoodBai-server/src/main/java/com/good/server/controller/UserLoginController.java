package com.good.server.controller;

import com.good.server.base.JsonResult;
import com.good.server.entity.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserLoginController {

	@RequestMapping("/login/loginsubmit.do")
	public @ResponseBody JsonResult  queryAll(@RequestBody UserInfo user) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("获取了数据:" + user.getName() + " password:" + user.getPassword());
		return new JsonResult("登录成功");
	}

}
