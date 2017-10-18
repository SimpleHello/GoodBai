package com.good.server.entity;

import java.io.Serializable;

/**
 * Created by John on 2017/10/18.
 */
public class MessageInfo implements Serializable{

    private static final long serialVersionUID = 61425058121492757L;

    private String phone ;

    private String messgae;

    public MessageInfo() {
    }

    public MessageInfo(String phone, String messgae) {
        this.phone = phone;
        this.messgae = messgae;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "phone='" + phone + '\'' +
                ", messgae='" + messgae + '\'' +
                '}';
    }
}
