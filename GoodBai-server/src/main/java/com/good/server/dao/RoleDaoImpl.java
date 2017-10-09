package com.good.server.dao;


import com.good.server.base.DaoHelper;
import com.good.server.base.Namespace;
import com.good.server.entity.system.RoleInfo;
import com.good.server.entity.system.UserInfo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("roleDao")
public class RoleDaoImpl {

	@Resource(name = "readDaoHelper")
	private DaoHelper readDao;


	public List<RoleInfo> getRoleList(UserInfo userInfo) throws Exception {
		return readDao.query(Namespace.SYS_ROLE, "getRoleList", userInfo);
	}

}
