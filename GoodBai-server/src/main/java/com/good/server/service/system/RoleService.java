package com.good.server.service.system;

import com.good.server.entity.system.RoleInfo;
import com.good.server.entity.system.UserInfo;

import java.util.List;

/**
 * Created by John on 2017/10/9.
 */
public interface RoleService {

    List<RoleInfo> getRoleList(UserInfo userInfo) throws  Exception;

}
