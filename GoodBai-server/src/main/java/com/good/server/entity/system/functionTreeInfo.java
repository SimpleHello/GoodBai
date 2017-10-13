package com.good.server.entity.system;

import com.good.server.base.IEntity;

import java.util.List;

/**
 * Created by John on 2017/10/12.
 */
public class FunctionTreeInfo extends IEntity{


    private static final long serialVersionUID = 3765342160479935410L;
    private String name;
    private boolean open = true;
    private boolean checked = false;
    private boolean nodeSelectNotAll = true;//新增参数，表示父框可以半钩状态
    private int parentId;
    private boolean visible = true;
    private boolean searched = false;

    private List<FunctionTreeInfo> children;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isNodeSelectNotAll() {
        return nodeSelectNotAll;
    }

    public void setNodeSelectNotAll(boolean nodeSelectNotAll) {
        this.nodeSelectNotAll = nodeSelectNotAll;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isSearched() {
        return searched;
    }

    public void setSearched(boolean searched) {
        this.searched = searched;
    }

    public List<FunctionTreeInfo> getChildren() {
        return children;
    }

    public void setChildren(List<FunctionTreeInfo> children) {
        this.children = children;
    }
}
