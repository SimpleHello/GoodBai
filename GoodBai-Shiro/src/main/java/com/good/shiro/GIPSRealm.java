package com.good.shiro;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.good.entity.system.Permission;
import com.good.entity.system.Role;
import com.good.entity.system.User;
import com.good.util.AuthUtil;

public class GIPSRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		System.out.println(" go here doGetAuthorizationInfo --------111");
		User user = AuthUtil.getCurrentUser();
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
        simpleAuthorInfo.addRoles(getRoles(user));
        simpleAuthorInfo.addStringPermissions(getPermCodes(user));
        return simpleAuthorInfo;
	}

	/**
     * 获取权限，string存放的是权限编码
     * @param user
     * @return
     */
    private List<String> getPermCodes(User user) {

        List<String> perms = new ArrayList<String>();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            List<Permission> _perms = role.getPermissions();
            for (Permission _perm : _perms) {
                perms.add(_perm.getPermCode());
            }
        }
        return perms;
    }


    /**
     * 获取角色集合，string存放的角色名称
     * @param user
     * @return
     */
    private List<String> getRoles(User user) {

        List<String> roles = new ArrayList<String>();
        for (Role role : user.getRoles()) {
            roles.add(role.getRoleName());
        }
        return roles;
    }
    
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		// TODO Auto-generated method stub

        return null ;
	}
	



}
