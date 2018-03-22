package com.good.server.entity.share;

import com.good.server.base.IEntity;

/**
 * Created by Mg on 2018/3/22.
 */
public class ShareHisInfo extends IEntity {

    private static final long serialVersionUID = -2687072725219105440L;
    private String share;
    private String code;
    private int day;
    private float value;

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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
