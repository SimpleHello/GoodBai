package com.good.server.entity.system;

import com.good.server.base.IEntity;

import java.util.List;

/**
 * Created by John on 2017/9/29.
 */
public class MenuInfo extends IEntity{

    private static final long serialVersionUID = 447882257571107387L;

    private int parentId;
    private String name;
    private String url;
    private List<MenuInfo> children;
    private int orderNo;
    private String descript;
    private int type;//1 菜单 2 按钮

    public MenuInfo() {

    }

    public MenuInfo(int id, String name, int parentId) {
        super.setId(id);
        this.name = name;
        this.parentId = parentId;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<MenuInfo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuInfo> children) {
        this.children = children;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
