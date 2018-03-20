package com.good.server.service.system;

import com.good.server.entity.system.UserInfo;

import java.util.List;

/**
 * Created by John on 2017/10/9.
 */
public interface UserService {

    List<UserInfo> getUserList(UserInfo userInfo) throws  Exception;

    void saveUser(UserInfo userInfo) throws  Exception;

}
