package com.good.server.service.impl;

import com.good.server.dao.MenuDaoImpl;
import com.good.server.entity.system.MenuInfo;
import com.good.server.service.MenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by John on 2017/9/29.
 */
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Resource(name="menuDao")
    private MenuDaoImpl menuDao;

    @Override
    public List<MenuInfo> getListByUser(int userId) throws Exception {
        List<MenuInfo> list = menuDao.getListByUser(userId);
        return null;
    }
}
