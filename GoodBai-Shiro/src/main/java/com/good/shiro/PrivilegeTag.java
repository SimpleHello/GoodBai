package com.good.shiro;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import javax.servlet.jsp.tagext.TagSupport;

import com.good.entity.system.Role;
import com.good.entity.system.User;
import com.good.util.AuthUtil;

public class PrivilegeTag  extends TagSupport {

    private static final long serialVersionUID = 1L;
    private String privilege; //标签属性

    @Override
    public int doStartTag() {
    	System.out.println("here is PrivilegeTag>doStartTag");
        User user =  AuthUtil.getCurrentUser();//获取登录用户信息
        if(user == null) return SKIP_BODY;
        if (isManager(user)) return EVAL_BODY_INCLUDE;  //超级管理员获取所有权限
        boolean bResult = SecurityUtils.getSubject().isPermitted(privilege);//根据标签属性判断用户是否有此菜单功能权限，isPermitted的调用会触发doGetAuthorizationInfo方法
        if(bResult){
            return EVAL_BODY_INCLUDE;
        }
        return SKIP_BODY;
    }


    /**
     * 判断用户是否超级管理员
     * @return
     */
    private boolean isManager(User user){

        List<Role> roles = user.getRoles();
        boolean b = false ;
        for (Role role : roles) { //遍历是否有超级管理员角色
            if (role.getIsManager()) {
                b = true ;
                break ;
            }
        }
        String accountName = user.getUserame();
        if (accountName.equals("admin") 
                || accountName.equals("sysadmin") 
                || b) {
            return true;
        }
        return false;

    }

    public String getPrivilege() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege = privilege;
    }
}