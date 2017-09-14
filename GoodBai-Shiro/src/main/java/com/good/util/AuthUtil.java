package com.good.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.good.entity.system.user.Function;
import com.good.entity.system.user.Role;
import com.good.entity.system.user.User;

public class AuthUtil {
	 /**
     * 获取当前用户
     * @return
     */
    public static User  getCurrentUser(){ 
        Subject sub = SecurityUtils.getSubject();
        Session session = sub.getSession();
        User user = (User) session.getAttribute("CURRENT_USER");
        return user ;
    }


    /**
     * 在用户中获取权限信息
     * @return
     */
    public static List<Function>  getCurrentUserPermissions() {
        User user = AuthUtil.getCurrentUser();
        List<Function> perms = new ArrayList<Function>();
        for (Role role : user.getRoleList()) {
            for (Function permission : role.getFuncList()) {
                perms.add(permission);
            }
        }
        perms = filterRepAndSort(perms);
        return perms;
    }


    /**
     * 去重，排序
     * @param perms
     * @return
     */
    private static List<Function> filterRepAndSort(List<Function> perms) {

         for ( int i = 0 ; i < perms.size() - 1 ; i++ ) {  
             for ( int j = perms.size() - 1 ; j > i; j-- ) {  
               if (perms.get(j).getOrderNo() == perms.get(i).getOrderNo()) {  
                   perms.remove(j);  
               }   
              }   
            }   

        Collections.sort(perms, new Comparator<Function>() {

            @Override
            public int compare(Function p1, Function p2) {
                return p1.getOrderNo() - p2.getOrderNo();
            }

        });

        return perms;

    }
}