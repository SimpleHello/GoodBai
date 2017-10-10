package com.good.server.entity.system;

import com.good.server.base.IEntity;

/**
 * Created by John on 2017/9/29.
 */
public class UserInfo extends IEntity{

    private static final long serialVersionUID = 3462953374395191542L;


    private String name;
    private String password;
    private int sex;
    private String tel;

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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", tel='" + tel + '\'' +
                '}';
    }
}

