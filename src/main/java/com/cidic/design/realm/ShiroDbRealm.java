package com.cidic.design.realm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.cidic.design.exception.CaptchaException;
import com.cidic.design.model.PermissionRole;
import com.cidic.design.model.User;
import com.cidic.design.model.UserRole;
import com.cidic.design.service.UserService;
import com.cidic.design.servlet.CaptchaServlet;
import com.cidic.design.shiro.UsernamePasswordCaptchaToken;

public class ShiroDbRealm extends AuthorizingRealm {
	 
	@Autowired
	@Qualifier(value = "userServiceImpl")
	private UserService userServiceImpl;
 
	/**
	 * ��֤�ص�����, ��¼ʱ����.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;
 
		String username = token.getUsername();
		if (username == null) {
			throw new AccountException(
					"Null usernames are not allowed by this realm.");
		}
		// �����ж���֤���߼�
		String captcha = token.getCaptcha();
		String exitCode = (String) SecurityUtils.getSubject().getSession()
				.getAttribute(CaptchaServlet.KEY_CAPTCHA);
		if (null == captcha || !captcha.equalsIgnoreCase(exitCode)) {
			throw new CaptchaException("��֤�����");
		}
 
		User user = userServiceImpl.findByUsername(username);
		if (null == user) {
			throw new UnknownAccountException("No account found for user ["
					+ username + "]");
		}
		return new SimpleAuthenticationInfo(new ShiroUser(user.getEmail(),
				user.getRealname()), user.getPassword(), getName());
 
	}
 
	/**
	 * ��Ȩ��ѯ�ص�����, ���м�Ȩ�����������û�����Ȩ��Ϣʱ����.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName())
				.iterator().next();
		User user = userServiceImpl.findByUsername(shiroUser.getLoginName());
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for (UserRole role : user.getUserRoles()) {
				// ����Permission��Ȩ����Ϣ
				List<String> permissions = new ArrayList<>();
				for (PermissionRole permissionRole : role.getRole().getPermissionRoles()){
					permissions.add(permissionRole.getPermission().getPermissionName());
				}
				info.addStringPermissions(permissions);
			}
			return info;
		} else {
			return null;
		}
	}
 
	/**
	 * �����û���Ȩ��Ϣ����.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}
 
	/**
	 * ��������û���Ȩ��Ϣ����.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}
 
 
	/**
	 * �Զ���Authentication����ʹ��Subject����Я���û��ĵ�¼���⻹����Я��������Ϣ.
	 */
	public static class ShiroUser implements Serializable {
 
		private static final long serialVersionUID = -1748602382963711884L;
		private String loginName;
		private String name;
 
		public ShiroUser(String loginName, String name) {
			this.loginName = loginName;
			this.name = name;
		}
 
		public String getLoginName() {
			return loginName;
		}
 
		/**
		 * �������������ΪĬ�ϵ�&lt;shiro:principal/&gt;���.
		 */
		@Override
		public String toString() {
			return loginName;
		}
 
		public String getName() {
			return name;
		}
	}
}
