package com.good.server.entity.share;

/**
 * Created by Mg on 2018/3/20.
 */
public class ShareDetailInfo extends ShareBaseInfo {

    private static final long serialVersionUID = 8573243899721136474L;
    private Float noStart;
    private Float noEnd;
    private Float amm;
    private int deltype;
    private int addtype;
    private int noDay;
    private int noHour;


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

    public int getNoHour() {
        return noHour;
    }

    public void setNoHour(int noHour) {
        this.noHour = noHour;
    }

    public Float getNoStart() {
        return noStart;
    }

    public void setNoStart(Float noStart) {
        this.noStart = noStart;
    }

    public Float getNoEnd() {
        return noEnd;
    }

    public void setNoEnd(Float noEnd) {
        this.noEnd = noEnd;
    }

    public Float getAmm() {
        return amm;
    }

    public void setAmm(Float amm) {
        this.amm = amm;
    }

    @Override
    public String toString() {
        return "ShareDetailInfo{" +
                "deltype=" + deltype +
                ", addtype=" + addtype +
                ", noDay=" + noDay +
                ", noHour=" + noHour +
                "} " + super.toString();
    }
}
