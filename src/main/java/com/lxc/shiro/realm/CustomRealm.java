package com.lxc.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class CustomRealm extends AuthorizingRealm{
	
	
//	设置realm的名称
	public void setName(String name) {
		super.setName("CustomerRealm");
	}
/*
 * 用户认证(non-Javadoc) 
 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//		获取token中的用户认证信息
		String userCode=(String) token.getPrincipal();
//		根据用户输入的userCode从数据库中获取
//		...	
//		若查询不到返回null
		if(!userCode.equals("zhangsan")) {
			return null;
		}
//		模拟从数据库中查询到了密码
		String password="111";
//		查询到就返回       认证信息AuthenticationInfo
		SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(userCode, password, this.getName());
		return authenticationInfo;
	}
	
	/*
	 * 
	 * 用户授权
	 * (non-Javadoc)
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
/*
 * 从principals获取主身份信息
 * 将getPrimaryPrincipal方法返回值（subject）强转为真实的身份信息
 * （在上边的doGetAuthenticationInfo认证通过后填充到SimpleAuthenticationInfo中的身份类型信息）
 */
		String userCode=(String) principals.getPrimaryPrincipal();
		
		/*
		 * 根据身份信息获取权限信息
		 * 连接数据库...
		 * 模拟从数据库中获取到数据
		 */
//		这里的相当于数据库
		List<String> permission=new ArrayList<>();
		permission.add("user:create");
		permission.add("user:delete");
		
//		查到了权限数据，返回授权信息（要包括上边的permission）
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
//		返回查到的授权信息
		authorizationInfo.addStringPermissions(permission);		
		
		return authorizationInfo;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}


}
