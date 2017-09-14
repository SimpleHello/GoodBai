package com.good.dao.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.good.base.DaoHelper;
import com.good.base.Namespace;
import com.good.dao.system.UserDao;
import com.good.entity.system.user.User;

@Repository("userDao")
public class UserDaoImpl implements UserDao {

	@Resource(name = "readDaoHelper")
	private DaoHelper readDao;
	
	@Override
	public void saveUser(User user) throws Exception {
		// TODO Auto-generated method stub
		readDao.insert(Namespace.SYS_USER,"saveUser", user);
	}

	@Override
	public List<User> getUserList(User user) throws Exception {
		// TODO Auto-generated method stub
		return readDao.query(Namespace.SYS_USER, "getUserList", user);
	}

	@Override
	public User getUserById(int id) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		user.setId(id);
		return (User)readDao.queryObject(Namespace.SYS_USER, "getUserById", user);
	}

	@Override
	public User getUserByName(String name) throws Exception {
		// TODO Auto-generated method stub
		User user = new User();
		user.setName(name);
		return (User)readDao.queryObject(Namespace.SYS_USER, "getUserByName", user);
	}

}
