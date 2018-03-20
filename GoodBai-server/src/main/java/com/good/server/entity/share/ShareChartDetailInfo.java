package com.good.server.entity.share;

import com.good.server.base.IEntity;

/**
 * Created by Mg on 2018/3/20.
 */
public class ShareChartDetailInfo extends IEntity{

    private static final long serialVersionUID = 141281451265208110L;

    private int time;
    private double value;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
