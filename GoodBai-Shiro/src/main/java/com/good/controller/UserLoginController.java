package com.good.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserLoginController {

	@RequestMapping("/login/loginsubmit.do")
	public @ResponseBody String queryAll() throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("total", 1);
		map.put("rows", null);
		map.put("page", 2);
		System.out.println("----- UserLoginController-queryAll");
		//JSONUtil.toJsonString(map)
		return "view/index2";
	}
}
