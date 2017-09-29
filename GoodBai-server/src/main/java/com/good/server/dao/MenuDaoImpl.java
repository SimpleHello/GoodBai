package com.good.server.dao;


import com.good.server.base.DaoHelper;
import com.good.server.base.Namespace;
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

}
