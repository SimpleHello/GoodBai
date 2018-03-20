package com.good.server.entity.share;

import com.good.server.base.IEntity;

/**
 * Created by Mg on 2018/3/20.
 */
public class ShareBaseInfo extends IEntity {

    private String name;
    private String share;
    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
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
                ", share='" + share + '\'' +
                ", code='" + code + '\'' +
                "} " + super.toString();
    }
}
