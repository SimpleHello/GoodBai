package com.good.server.service.impl;

import com.good.server.dao.RoleDaoImpl;
import com.good.server.entity.system.RoleInfo;
import com.good.server.entity.system.UserInfo;
import com.good.server.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by John on 2017/10/9.
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    @Resource(name="roleDao")
    private RoleDaoImpl roleDao;

    @Override
    public List<RoleInfo> getRoleList(UserInfo userInfo) throws Exception {
        return roleDao.getRoleList(userInfo);
    }
}
