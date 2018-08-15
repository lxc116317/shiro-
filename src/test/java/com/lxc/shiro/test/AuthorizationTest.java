package com.lxc.shiro.test;

import java.util.Arrays;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.jupiter.api.Test;

public class AuthorizationTest {
	
	@Test
	public void test1() {

	Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:config/shiro-permission.ini");
	
	SecurityManager securityManager=factory.getInstance();
	
	SecurityUtils.setSecurityManager(securityManager);
	
	Subject subject=SecurityUtils.getSubject();
	
	UsernamePasswordToken token=new UsernamePasswordToken("zhangsan", "123");
	
	try {
//		登录
		subject.login(token);
	} catch (AuthenticationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	认证状态
	System.out.println("认证状态："+subject.isAuthenticated());
	
//	认证通过后授权
//	基于角色的授权
//	hasrole传入角色标识
	boolean ishasRole=subject.hasRole("role1");
	System.out.println("单个角色判断"+ishasRole);
	
	boolean ishasAllRole=subject.hasAllRoles(Arrays.asList("role1","role2"));
	System.out.println("多个角色判断"+ishasAllRole);
//	使用check方法进行授权，若授权不通过会抛出异常
//	subject.checkRole("role3131");	
//	基于资源的的授权
//	isPermitted传入资源标识符
	boolean isPermitted=subject.isPermitted("user:create");
	System.out.println("单个资源判断："+isPermitted);
	
	
	boolean isPermittedAll=subject.isPermittedAll("user:create","user:update");
	System.out.println("多个资源判断："+isPermittedAll);
	
//	使用check方法进行授权，若授权不通过会抛出异常
	subject.checkPermission("items:create:1");
	
	
	
	}
	@Test
	public void testRealm() {
	Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:config/shiro-realm.ini");
	SecurityManager securityManager=factory.getInstance();
	SecurityUtils.setSecurityManager(securityManager);
	Subject subject=SecurityUtils.getSubject();
	UsernamePasswordToken token=new UsernamePasswordToken("zhangsan", "111");
	try {
//		登录
		subject.login(token);
	} catch (AuthenticationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//	认证状态
	System.out.println("认证状态："+subject.isAuthenticated());
//	认证通过后授权

//	基于资源的的授权,调用isPermitted方法会调用CustomRealm方法从数据库中查询正确的权限数据
//	isPermitted传入资源标识符，判断user:create是否在CustomRealm查询权限范围之内
	boolean isPermitted=subject.isPermitted("user:create");
	System.out.println("单个资源判断："+isPermitted);
	
	
	boolean isPermittedAll=subject.isPermittedAll("user:create","user:update");
	System.out.println("多个资源判断："+isPermittedAll);
	}
	
	
	
}
