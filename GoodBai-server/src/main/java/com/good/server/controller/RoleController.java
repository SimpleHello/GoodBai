package com.good.server.controller;

import com.good.server.base.JsonResult;
import com.good.server.entity.system.RoleInfo;
import com.good.server.entity.system.UserInfo;
import com.good.server.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

	@Resource(name = "roleService")
	private RoleService roleService;

	@RequestMapping("/getList.do")
	public @ResponseBody JsonResult  getList(@RequestBody UserInfo user) throws Exception {
		// TODO Auto-generated method stub
		List<RoleInfo> list = null;
		try{
			list = roleService.getRoleList(null);
			return new JsonResult(list);
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(-1,"出现异常:"+e.getMessage(),null);
		}


	}
}
