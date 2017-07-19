package com.good.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.good.entity.system.Permission;
import com.good.entity.system.Role;
import com.good.entity.system.User;

@Controller
public class UserLoginController {

	@RequestMapping("/login/loginsubmit.do")
	public ModelAndView  queryAll(User user ,ModelMap modelMap) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("获取了数据:"+user.getUsename()+" password:"+user.getPassword());
		Subject sub = SecurityUtils.getSubject();
        Session session = sub.getSession();
        User userInfo = getUser(user.getUsename());
        session.setAttribute("CURRENT_USER", userInfo);
		modelMap.put("total", 1);
		modelMap.put("rows", null);
		modelMap.put("page", 2);
        
		//JSONUtil.toJsonString(map)
		return new ModelAndView("index2");
	}
	
	private User getUser(String name){
		 boolean isM = false;
		 Role x = new Role(name);
		 List<Permission> li = new ArrayList<Permission>();
		 li.add(new Permission("query","001"));
		 li.add(new Permission("del","004"));
		 if("admin".equals(name)){
			 li.add(new Permission("update","002"));
			 li.add(new Permission("add","003"));
			 isM = true;
		 }
		 x.setIsManager(isM);
		 x.setPermissions(li);
		 User user = new User();
		 List<Role> lii =  new ArrayList<Role>();
		 lii.add(x);
		 user.setRoles(lii);
		 user.setPassword("123");
		 user.setNickName(name+"nick");
		 user.setUsename(name);
		 return user;
	 }
}
