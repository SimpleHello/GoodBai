package com.good.server.entity;

import java.io.Serializable;

/**
 * Created by John on 2017/9/29.
 */
public class UserInfo implements Serializable{

    private static final long serialVersionUID = 3462953374395191542L;

    private int id;
    private String name;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

