package com.good.server.entity;

import java.io.Serializable;

/**
 * Created by John on 2017/10/20.
 */
public class RedisInfo implements Serializable{
    private static final long serialVersionUID = 5833226536882085475L;

    private String key;

    private String value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
