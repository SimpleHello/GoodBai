package com.good.dao.system;

import java.util.List;

import com.good.entity.system.user.User;

public interface UserDao {
	
	void saveUser(User user) throws Exception;
	
	List<User> getUserList(User user) throws Exception ;
	
	User getUserById(int id) throws Exception ;
	 
	User getUserByName(String name) throws Exception ;
}
