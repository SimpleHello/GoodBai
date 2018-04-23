package com.good.server.entity.share;

import com.good.server.base.IEntity;

/**
 * Created by Mg on 2018/3/20.
 */
public class ShareBaseInfo extends IEntity {

    private String type;
    private String name;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ShareBaseInfo{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", code='" + code + '\'' +
                "} " + super.toString();
    }
}
