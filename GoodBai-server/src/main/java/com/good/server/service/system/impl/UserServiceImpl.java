package com.good.server.service.system.impl;

import com.good.server.dao.system.UserDaoImpl;
import com.good.server.entity.system.UserInfo;
import com.good.server.service.system.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by John on 2017/10/10.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource(name = "userDao")
    private UserDaoImpl userDao;

    @Override
    public List<UserInfo> getUserList(UserInfo userInfo) throws Exception {
        return userDao.getUserList(userInfo);
    }

    @Override
    public void saveUser(UserInfo userInfo) throws Exception {
        userDao.saveUser(userInfo);
    }
}
