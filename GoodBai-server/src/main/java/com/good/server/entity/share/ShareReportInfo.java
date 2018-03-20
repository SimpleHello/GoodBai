package com.good.server.entity.share;

/**
 * Created by Mg on 2018/3/20.
 */
public class ShareReportInfo extends ShareBaseInfo {
    private static final long serialVersionUID = 225979987805107420L;

    private int deltype;
    private int addtype;
    private int noDay;
    private int num;

    public int getDeltype() {
        return deltype;
    }

    public void setDeltype(int deltype) {
        this.deltype = deltype;
    }

    public int getAddtype() {
        return addtype;
    }

    public void setAddtype(int addtype) {
        this.addtype = addtype;
    }

    public int getNoDay() {
        return noDay;
    }

    public void setNoDay(int noDay) {
        this.noDay = noDay;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "ShareReportInfo{" +
                "deltype=" + deltype +
                ", addtype=" + addtype +
                ", noDay=" + noDay +
                ", num=" + num +
                "} " + super.toString();
    }
}
