package com.good.task01.entity;

import com.good.task01.base.IEntity;

/**
 * Created by Mg on 2018/3/14.
 */
public class ShareDetailEntity extends IEntity {

    private String name;
    private String share;
    private String code;
    private int noDay;
    private int noHour;
    private int type;// 1 是新增 -1 是删除

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

    public int getNoDay() {
        return noDay;
    }

    public void setNoDay(int noDay) {
        this.noDay = noDay;
    }

    public int getNoHour() {
        return noHour;
    }

    public void setNoHour(int noHour) {
        this.noHour = noHour;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
