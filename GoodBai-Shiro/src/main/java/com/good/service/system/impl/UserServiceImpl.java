package com.good.service.system.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.good.dao.system.UserDao;
import com.good.entity.system.user.User;
import com.good.service.system.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Resource(name="userDao")
	private UserDao userDao;
	
	@Override
	public void saveUser(User user) throws Exception {
		// TODO Auto-generated method stub
		userDao.saveUser(user);
	}

	@Override
	public List<User> getUserList(User user) throws Exception {
		// TODO Auto-generated method stub
		return userDao.getUserList(user);
	}

	@Override
	public User getUserById(int id) throws Exception {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}

	@Override
	public User getUserByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return userDao.getUserByName(name);
	}


}
