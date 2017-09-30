package com.good.server.service.impl;

import com.good.server.dao.MenuDaoImpl;
import com.good.server.entity.system.MenuInfo;
import com.good.server.service.MenuService;
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
        return getMenuTree(list);
    }

    private static  List<MenuInfo> getMenuTree(List<MenuInfo> list,int pid,List<Integer> check){
        List<MenuInfo> value = new ArrayList<>();
        if(list!=null&&list.size()>0){
            for(int i = 0; i < list.size(); i++){
                MenuInfo info = list.get(i);
                int parentId = info.getParentId();
                int id = info.getId();
                if(check.contains(id)){
                    continue;//被循环过的ID 不再检测
                }else if(parentId==pid){
                    check.add(id);
                    info.setChildren(getMenuTree(list,id,check));
                    value.add(info);
                }
            }
        }
        return value;
    }

    private static List<MenuInfo> getMenuTree(List<MenuInfo> list){
        List<Integer> check = new ArrayList<>();
        return getMenuTree(list,0,check);
    }
}
