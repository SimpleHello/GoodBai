package com.good.server.entity.share;

/**
 * Created by Mg on 2018/3/20.
 */
public class ShareReportInfo extends ShareBaseInfo {
    private static final long serialVersionUID = 225979987805107420L;

    private int pop;
    private int push;
    private int day;
    private int num;

    public int getPop() {
        return pop;
    }

    public void setPop(int pop) {
        this.pop = pop;
    }

    public int getPush() {
        return push;
    }

    public void setPush(int push) {
        this.push = push;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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
                "pop=" + pop +
                ", push=" + push +
                ", day=" + day +
                ", num=" + num +
                "} " + super.toString();
    }
}
