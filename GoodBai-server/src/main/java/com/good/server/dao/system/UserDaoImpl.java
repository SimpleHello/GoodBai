package com.good.server.dao.system;


import com.good.server.base.DaoHelper;
import com.good.server.base.Namespace;
import com.good.server.entity.system.UserInfo;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository("userDao")
public class UserDaoImpl {

	@Resource(name = "readDaoHelper")
	private DaoHelper readDao;


	public List<UserInfo> getUserList(UserInfo userInfo) throws Exception {
		return readDao.query(Namespace.SYS_USER,"getUserList",userInfo);
	}


	public void saveUser(UserInfo userInfo) throws Exception {
		readDao.insert(Namespace.SYS_USER,"saveUser",userInfo);
	}

}
