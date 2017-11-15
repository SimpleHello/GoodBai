package com.good.server.sms.util;

import java.io.Serializable;

/**
 * Created by John on 2017/10/19.
 * 基于短信猫的发送 参数
 * http://服务器地址:9618/User=,Password=,MsgID=,Phone=,Msg=
 * MsgID内容为数字,用于短信的标识
 * 各项参数的排列属性不可以颠倒
 */
public class SmsCatMessage implements Serializable{

    private String user;
    private String password;
    private  int msgId;
    private  String phone;
    private  String  msg;

    public SmsCatMessage(String user, String password, int msgId, String phone) {
        this.user = user;
        this.password = password;
        this.msgId = msgId;
        this.phone = phone;
    }

    public SmsCatMessage() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getParm(){
        String param = "User="+this.user
                        +",Password="+this.password
                        +",MsgID="+this.msgId
                        +",Phone="+this.phone
                        +",Msg="+this.msg;
        System.out.print("this param:"+param);
        return param;
    }
}
