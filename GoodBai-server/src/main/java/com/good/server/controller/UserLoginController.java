package com.good.server.controller;

import com.good.server.base.JsonResult;
import com.good.server.entity.system.MenuInfo;
import com.good.server.entity.system.UserInfo;
import com.good.server.service.MenuService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/login")
public class UserLoginController {

	@Resource(name="menuService")
	private MenuService menuService;

	@RequestMapping("/loginsubmit.do")
	public @ResponseBody JsonResult  queryAll(@RequestBody UserInfo user) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("获取了数据:" + user.getName() + " password:" + user.getPassword());
		List<MenuInfo> list = null;
		try{
			list = menuService.getListByUser(getUserId(user.getName()));
			return new JsonResult(list);
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(-1,"出现异常:"+e.getMessage(),null);
		}


	}

	private int getUserId(String userName){
		if("admin".equals(userName)){
			return 1;
		}else if("ceshi".equals(userName)){
			return 2;
		}else{
			return 0;
		}
	}
}
