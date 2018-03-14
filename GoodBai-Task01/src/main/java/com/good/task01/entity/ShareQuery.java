package com.good.task01.entity;

import com.good.task01.base.IEntity;

import java.util.List;

/**
 * Created by Mg on 2018/3/14.
 */
public class ShareQuery extends IEntity {


    private Integer startDay;
    private Integer startHour;
    private Integer endDay;
    private Integer endHour;
    private List<Integer> ids;

    public ShareQuery(Integer startDay, Integer startHour, Integer endDay, Integer endHour) {
        this.startDay = startDay;
        this.startHour = startHour;
        this.endDay = endDay;
        this.endHour = endHour;
    }

    public ShareQuery() {
    }

    public Integer getStartDay() {
        return startDay;
    }

    public void setStartDay(Integer startDay) {
        this.startDay = startDay;
    }

    public Integer getStartHour() {
        return startHour;
    }

    public void setStartHour(Integer startHour) {
        this.startHour = startHour;
    }

    public Integer getEndDay() {
        return endDay;
    }

    public void setEndDay(Integer endDay) {
        this.endDay = endDay;
    }

    public Integer getEndHour() {
        return endHour;
    }

    public void setEndHour(Integer endHour) {
        this.endHour = endHour;
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
