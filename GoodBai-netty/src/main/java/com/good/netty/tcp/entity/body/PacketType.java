package com.good.netty.tcp.entity.body;

import java.util.HashMap;
import java.util.Map;

import com.good.netty.tcp.entity.model.Login;
import com.good.netty.tcp.entity.model.LoginAck;

/**
 * Created by wuyadong on 2016/12/5.
 * 消息类型枚举定义，包含了消息值、消息名称和绑定的消息体类
 */
public enum PacketType {
    LOGIN(101, "login", Login.class,0),//登陆
    LOGIN_ACK(102, "login_ack", LoginAck.class,0),//登陆响应
    UNKNOWN(-1,  "unknown_type") ;

    private long type;
    private String name;
    private Class<? extends Body> bodyClass;
    private int right;//命令 所需的用户权限  0(null)：无权限;1：只读权限;2=读写权限 
    
    PacketType(long type, String name) {
        this.type = type;
        this.name = name;
        this.bodyClass = null;
        this.right = 0;
    }

    PacketType(long type, String name, Class<? extends Body> bodyClass) {
        this.type = type;
        this.name = name;
        this.bodyClass = bodyClass;
        this.right = 0;
    }

    PacketType(long type, String name, Class<? extends Body> bodyClass,int right) {
        this.type = type;
        this.name = name;
        this.bodyClass = bodyClass;
        this.right = right;
    }
    
    private static final Map<Long, PacketType> typeMap = new HashMap<>();
    static {
        for (PacketType pack : PacketType.values()) {
            typeMap.put(pack.type, pack);
        }
    }

    public static PacketType toType(long type_value) {
        PacketType type = typeMap.get(type_value);
        if (type == null)
            return PacketType.UNKNOWN;
        return type;
    }

    public long getType() {
        return type;
    }

    public PacketType getAck() {
        // TODO: check is req?
        // return ack
        PacketType type = typeMap.get(this.type+1);
        if (type == null)
            return PacketType.UNKNOWN;
        return type;
    }

    public Class<? extends Body> getBodyClass() {
        return bodyClass;
    }
    public int getRight() {
        return right;
    }

    public String toString() {
        return name;
    }
    
    public String getName() {
        return name;
    }

    
    
}
