package com.good.server.util.system;

import com.good.server.entity.system.MenuInfo;
import com.good.server.entity.system.FunctionTreeInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by John on 2017/10/12.
 */
public class TreeUtil {

    public static List<MenuInfo> getMenuTree(List<MenuInfo> list, int pid, List<Integer> check){
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

    public static List<MenuInfo> getMenuTree(List<MenuInfo> list){
        List<Integer> check = new ArrayList<>();
        return getMenuTree(list,0,check);
    }

    public static List<FunctionTreeInfo> getFunTree(List<MenuInfo> list){
        List<Integer> check = new ArrayList<>();
        List<FunctionTreeInfo> value = new ArrayList<>();
        FunctionTreeInfo fun = new FunctionTreeInfo();
        fun.setId(0);
        fun.setParentId(-1);
        fun.setName("XXXX权限----");
        fun.setChildren(getFunTree(list,0,check));
        value.add(fun);
        return value;
    }

    public static List<FunctionTreeInfo> getFunTree(List<MenuInfo> list, int pid, List<Integer> check){
        List<FunctionTreeInfo> value = new ArrayList<>();
        if(list!=null&&list.size()>0){
            for(int i = 0; i < list.size(); i++){
                MenuInfo info = list.get(i);
                int parentId = info.getParentId();
                int id = info.getId();
                if(check.contains(id)){
                    continue;//被循环过的ID 不再检测
                }else if(parentId==pid){
                    FunctionTreeInfo fun = new FunctionTreeInfo();
                    fun.setName(info.getName());
                    fun.setId(id);
                    fun.setParentId(parentId);
                    check.add(id);
                    fun.setChildren(getFunTree(list,id,check));
                    value.add(fun);
                }
            }
        }
        return value;
    }

}
