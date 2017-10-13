package com.good.server.service.impl;

import com.good.server.dao.MenuDaoImpl;
import com.good.server.entity.system.MenuInfo;
import com.good.server.entity.system.FunctionTreeInfo;
import com.good.server.service.MenuService;
import com.good.server.util.system.TreeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
        return TreeUtil.getMenuTree(list);
    }

    @Override
    public List<MenuInfo> getListByRoleId(int roleId) throws Exception {
        return menuDao.getListByRoleId(roleId);
    }

    @Override
    public List<MenuInfo> getList() throws Exception {
        return menuDao.getList();
    }

    @Override
    public List<FunctionTreeInfo> getFunTree() throws Exception {
        List<MenuInfo> list = getList();
        return TreeUtil.getFunTree(list);
    }

    @Override
    public List<Integer> getFunByRoleId(int roleId) throws Exception {
        List<MenuInfo> list = getListByRoleId(roleId);
        List<String> value = new ArrayList<>();
        if(list !=null && list.size()>0){
            for(MenuInfo info:list){
                value.s
            }
        }
        return null;
    }


}
