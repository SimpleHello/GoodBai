package com.good.shiro;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import com.good.entity.system.user.Function;
import com.good.entity.system.user.Role;
import com.good.entity.system.user.User;
import com.good.service.system.UserService;
import com.good.util.AuthUtil;


public class GoodBaiAuthRealm extends AuthorizingRealm {

	@Resource(name="userService")
	private UserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		System.out.println(" 验证阶段2 验证 验证啊 ");
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
        List<Role> roles = user.getRoleList();
        for (Role role : roles) {
            List<Function> _perms = role.getFuncList();
            for (Function _perm : _perms) {
                perms.add(_perm.getName());
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
        for (Role role : user.getRoleList()) {
            roles.add(role.getName());
        }
        return roles;
    }
    
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		// TODO Auto-generated method stub
		System.out.println("----> go here doGetAuthenticationInfo");
		UsernamePasswordToken token = (UsernamePasswordToken)arg0; 
		System.out.println(" 登录了:"+token.getUsername());
		if(token.getUsername()==null||token.getPassword()==null){
			return null;
		}
		User user = null;
		try{
			user = userService.getUserByName(token.getUsername());//userService.findByAccountName(token.getUsername()) ;//通过帐号获取用户实例
		}catch(Exception e){
			
		}
        if (user != null && ByteSource.Util.bytes(token.getPassword()).equals(ByteSource.Util.bytes(user.getPassword()))) {//用户校验
            setSessionInfo(user);
            return  new SimpleAuthenticationInfo(user.getName(), user.getPassword(), user.getName());   //验证成功之后进行授权
        }
        return null ;
	}
	

	 private void setSessionInfo(User user){
	        Subject sub = SecurityUtils.getSubject();
	        Session session = sub.getSession();
	        session.setTimeout(60*60*1000);
	        session.setAttribute("CURRENT_USER", user);
	    }
	 


}
