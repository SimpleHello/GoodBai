package com.good.server.entity.system;

import com.good.server.base.IEntity;

/**
 * Created by John on 2017/9/29.
 */
public class UserInfo extends IEntity{

    private static final long serialVersionUID = 3462953374395191542L;


    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

