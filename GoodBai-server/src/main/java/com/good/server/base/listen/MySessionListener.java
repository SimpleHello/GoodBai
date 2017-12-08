package com.good.server.base.listen;


import com.good.server.entity.system.UserInfo;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Mg on 2017/12/6.
 */
public class MySessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se){}



    @Override
    public void sessionDestroyed(HttpSessionEvent se){
        System.out.println("------------------session 超时了 ");
        HttpSession hs=se.getSession();
        UserInfo user = (UserInfo) hs.getAttribute("user");
        System.out.println("超时的user:"+user.getName());
        //TODO
    }




}