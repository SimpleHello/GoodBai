package com.good.server.dao;


import com.good.server.base.DaoHelper;
import com.good.server.base.Namespace;
import com.good.server.entity.system.RoleInfo;
import com.good.server.entity.system.UserInfo;
import com.good.server.entity.system.MenuInfo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("menuDao")
public class MenuDaoImpl{

	@Resource(name = "readDaoHelper")
	private DaoHelper readDao;

	public List<MenuInfo> getListByUser(int userId) throws Exception {
		// TODO Auto-generated method stub
		UserInfo user = new UserInfo();
		user.setId(userId);
		return readDao.query(Namespace.SYS_MENU, "getListByUser", user);
	}

	public List<MenuInfo> getListByUserName(String userName) throws Exception {
		// TODO Auto-generated method stub
		UserInfo user = new UserInfo();
		user.setName(userName);
		return readDao.query(Namespace.SYS_MENU, "getListByUserName", user);
	}

	public List<MenuInfo> getListByRoleId(int roleId) throws Exception {
		// TODO Auto-generated method stub
		RoleInfo role = new RoleInfo();
		role.setId(roleId);
		return readDao.query(Namespace.SYS_MENU, "getListByRoleId", role);
	}

	public List<MenuInfo> getList() throws Exception {
		// TODO Auto-generated method stub
		return readDao.query(Namespace.SYS_MENU, "getList", null);
	}
}
