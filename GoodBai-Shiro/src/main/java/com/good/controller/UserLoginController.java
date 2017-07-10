package com.good.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserLoginController {

	@RequestMapping("/login/loginsubmit.do")
	public ModelAndView  queryAll(ModelMap modelMap) throws Exception {
		// TODO Auto-generated method stub	
		modelMap.put("total", 1);
		modelMap.put("rows", null);
		modelMap.put("page", 2);
		System.out.println("----- UserLoginController-queryAll");
		//JSONUtil.toJsonString(map)
		return new ModelAndView("index2");
	}
}
