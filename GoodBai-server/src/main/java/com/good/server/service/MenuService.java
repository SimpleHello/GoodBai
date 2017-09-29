package com.good.server.service;

import com.good.server.entity.system.MenuInfo;

import java.util.List;

/**
 * Created by John on 2017/9/29.
 */
public interface MenuService {

    List<MenuInfo> getListByUser(int userId) throws Exception;
}
