package com.good.server.controller;

import com.good.server.base.JsonResult;
import com.good.server.entity.system.MenuInfo;
import com.good.server.entity.system.UserInfo;
import com.good.server.service.MenuService;
import com.good.server.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserLoginController {

	@Resource(name="menuService")
	private MenuService menuService;

	@Resource(name = "userService")
	private UserService userService;

	@RequestMapping("/loginsubmit.do")
	public @ResponseBody JsonResult  queryAll(@RequestBody UserInfo user,HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		List<MenuInfo> list = null;
		String name = user.getName();
		if(name==null||"".equals(name)){
			user.setName("admin");
		}
		try{
			request.getSession().setAttribute("user",user);
			list = menuService.getListByUser(getUserId(user.getName()));
			return new JsonResult(list);
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(-1,"出现异常:"+e.getMessage(),null);
		}
	}

	@RequestMapping("/getUserList.do")
	public @ResponseBody JsonResult  getUserList(@RequestBody UserInfo user) throws Exception {
		// TODO Auto-generated method stub
		List<UserInfo> list = null;
		try{
			list = userService.getUserList(user);
			return new JsonResult(list);
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(-1,"出现异常:"+e.getMessage(),null);
		}
	}

	@RequestMapping("/saveUser.do")
	public @ResponseBody JsonResult  saveUser(@RequestBody UserInfo user) throws Exception {
		// TODO Auto-generated method stub
		System.out.print(user.toString());
		try{
			userService.saveUser(user);
			return new JsonResult("新增成功");
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(-1,"出现异常:"+e.getMessage(),null);
		}
	}

	@RequestMapping("/editUser.do")
	public @ResponseBody JsonResult  editUser(@RequestBody UserInfo user) throws Exception {
		// TODO Auto-generated method stub
		System.out.print(user.toString());
		try{

			return new JsonResult("修改成功");
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
