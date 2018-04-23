package com.good.server.entity.share;

/**
 * Created by Mg on 2018/3/20.
 */
public class ShareDetailInfo extends ShareBaseInfo {

    private static final long serialVersionUID = 8573243899721136474L;
    private Float opens;
    private Float ends;
    private Float ranges;
    private int pop;
    private int push;
    private int day;
    private int hour;
    private int tol;

    public Float getOpens() {
        return opens;
    }

    public void setOpens(Float opens) {
        this.opens = opens;
    }

    public Float getEnds() {
        return ends;
    }

    public void setEnds(Float ends) {
        this.ends = ends;
    }

    public Float getRanges() {
        return ranges;
    }

    public void setRanges(Float ranges) {
        this.ranges = ranges;
    }

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

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getTol() {
        return tol;
    }

    public void setTol(int tol) {
        this.tol = tol;
    }

    @Override
    public String toString() {
        return "ShareDetailInfo{" +
                "opens=" + opens +
                ", ends=" + ends +
                ", ranges=" + ranges +
                ", pop=" + pop +
                ", push=" + push +
                ", day=" + day +
                ", hour=" + hour +
                ", tol=" + tol +
                "} " + super.toString();
    }
}
