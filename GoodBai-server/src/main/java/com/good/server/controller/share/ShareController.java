package com.good.server.controller.share;

import com.good.server.base.JsonResult;
import com.good.server.entity.system.FunctionTreeInfo;
import com.good.server.entity.system.RoleInfo;
import com.good.server.entity.system.UserInfo;
import com.good.server.service.system.MenuService;
import com.good.server.service.system.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/role")
public class ShareController {

	@Resource(name = "roleService")
	private RoleService roleService;

	@Resource(name="menuService")
	private MenuService menuService;

	@RequestMapping("/getList.do")
	public @ResponseBody JsonResult  getList(@RequestBody UserInfo user) throws Exception {
		// TODO Auto-generated method stub
		List<RoleInfo> list;
		try{
			list = roleService.getRoleList(null);
			return new JsonResult(list);
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(-1,"出现异常:"+e.getMessage(),null);
		}
	}

	@RequestMapping("/getFunTree.do")
	public @ResponseBody JsonResult  getFunTree() throws Exception {
		// TODO Auto-generated method stub
		List<FunctionTreeInfo> list ;
		try{
			list = menuService.getFunTree();
			return new JsonResult(list);
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(-1,"出现异常:"+e.getMessage(),null);
		}
	}

	@RequestMapping("/getFunByRoleId.do")
	public @ResponseBody JsonResult  getFunByRoleId(@RequestBody RoleInfo roleInfo) throws Exception {
		// TODO Auto-generated method stub
		List<Integer> list ;
		try{
			list = menuService.getFunByRoleId(roleInfo.getId());
			return new JsonResult(list);
		}catch (Exception e){
			e.printStackTrace();
			return new JsonResult(-1,"出现异常:"+e.getMessage(),null);
		}
	}
}
