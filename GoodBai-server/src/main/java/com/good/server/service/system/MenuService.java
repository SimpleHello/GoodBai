package com.good.server.service.system;

import com.good.server.entity.system.MenuInfo;
import com.good.server.entity.system.FunctionTreeInfo;

import java.util.List;

/**
 * Created by John on 2017/9/29.
 */
public interface MenuService {

    List<MenuInfo> getListByUser(int userId) throws Exception;

    List<MenuInfo> getListByRoleId(int roleId) throws Exception ;

    List<MenuInfo> getList() throws Exception ;

    List<FunctionTreeInfo> getFunTree() throws Exception ;

    List<Integer> getFunByRoleId(int roleId) throws Exception ;
}
