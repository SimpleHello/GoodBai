package com.good.shiro;

import java.util.ArrayList;
import java.util.List;

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
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import com.good.entity.system.Permission;
import com.good.entity.system.Role;
import com.good.entity.system.User;
import com.good.util.AuthUtil;

public class GIPSRealm extends AuthorizingRealm {

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		// TODO Auto-generated method stub
		System.out.println("27 go here doGetAuthorizationInfo --------111");
		User user = AuthUtil.getCurrentUser();
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		simpleAuthorInfo.addRoles(getRoles(user));
		simpleAuthorInfo.addStringPermissions(getPermCodes(user));
		return simpleAuthorInfo;
	}

	/**
	 * 获取权限，string存放的是权限编码
	 * 
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
	 * 
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
		System.out.println("----> go here doGetAuthenticationInfo");
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		System.out.println(" 登录了:" + token.getUsername());
		User user = getUser(token.getUsername());// userService.findByAccountName(token.getUsername()) 通过帐号获取用户实例
		if (user != null && ByteSource.Util.bytes(token.getPassword()).equals(ByteSource.Util.bytes(user.getPassword()))) {// 用户校验
			setSessionInfo(user);
//			return new SimpleAuthenticationInfo(user,user.getUsername(), user.getPassword()); // 验证成功之后进行授权
			return new SimpleAuthenticationInfo(user, user.getPassword(),getName()); // 验证成功之后进行授权
		}
		return null;
	}

	private void setSessionInfo(User user) {
		Subject sub = SecurityUtils.getSubject();
		Session session = sub.getSession();
		session.setAttribute("CURRENT_USER", user);
	}

	private User getUser(String name) {
		boolean isM = false;
		Role x = new Role(name);
		List<Permission> li = new ArrayList<Permission>();
		li.add(new Permission("query", "001"));
		li.add(new Permission("del", "004"));
		if ("admin".equals(name)) {
			li.add(new Permission("update", "002"));
			li.add(new Permission("add", "003"));
			isM = true;
		}
		x.setIsManager(isM);
		x.setPermissions(li);
		User user = new User();
		List<Role> lii = new ArrayList<Role>();
		lii.add(x);
		user.setRoles(lii);
		user.setPassword("123");
		user.setNickName(name + "nick");
		user.setUsername(name);
		return user;
	}
	 /**
     * 清空当前用户权限信息
     */
	public  void clearCachedAuthorizationInfo() {
		PrincipalCollection principalCollection = SecurityUtils.getSubject().getPrincipals();
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
	/**
	 * 指定principalCollection 清楚
	 */
	public void clearCachedAuthorizationInfo(PrincipalCollection principalCollection) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principalCollection, getName());
		super.clearCachedAuthorizationInfo(principals);
	}
}
