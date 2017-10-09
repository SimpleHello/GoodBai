package com.good.server.entity.system;

import com.good.server.base.IEntity;

/**
 * Created by John on 2017/10/9.
 */
public class RoleInfo extends IEntity{

    private static final long serialVersionUID = -4516208413083714224L;

    private  String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
