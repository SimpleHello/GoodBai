package com.test;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.good.entity.system.user.User;
import com.good.service.system.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserTest {

	@Resource(name="userService")
	private UserService userService;
	
	@Before
	public void setUp() {
		System.out.println("测试开始进行-----");
	}

	@After
	public void tearDown() {
		System.out.println("测试 结束-----");
	}
	
	@Test
	public void test01(){
		User user = new User();
		user.setName("admin");
		user.setPassword("admin");
		try {
			userService.saveUser(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
