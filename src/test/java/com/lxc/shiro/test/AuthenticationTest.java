package com.lxc.shiro.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.jupiter.api.Test;


//认证测试
public class AuthenticationTest {

//	用户登录
	@Test
	public void loginAndOut() {
//		创建securityManager工厂，通过ini配置文件创建securityManager工厂
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:config/shiro-first.ini");
//		创建SecurityManager
		SecurityManager securityManager=factory.getInstance();
//		将securityManager设置在当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);
//		从SecurityUtils里面创建一个subject
		Subject subject=SecurityUtils.getSubject();
//		在认证提交前准备token（令牌）
		UsernamePasswordToken token=new UsernamePasswordToken("zhangsan", "111");		
//		执行认证提交
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
//		是否认证通过
		boolean isAuthenticated=subject.isAuthenticated();
		System.out.println("是否认证通过"+isAuthenticated);
	}
	
	
//	自定义realm
	@Test
	public void realmTest() {
//		创建securityManager工厂，通过ini配置文件创建securityManager工厂
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:config/shiro-realm.ini");
//		创建SecurityManager
		SecurityManager securityManager=factory.getInstance();
//		将securityManager设置在当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);
//		从SecurityUtils里面创建一个subject
		Subject subject=SecurityUtils.getSubject();
//		在认证提交前准备token（令牌）
		UsernamePasswordToken token=new UsernamePasswordToken("zhangsan", "111");		
//		执行认证提交
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
//		是否认证通过
		boolean isAuthenticated=subject.isAuthenticated();
		System.out.println("是否认证通过"+isAuthenticated);
	
		
	}
	
	
//	自定义realmMD5散列算法
	@Test
	public void realmMD5Test() {
//		创建securityManager工厂，通过ini配置文件创建securityManager工厂
		Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:config/shiro-realm-MD5.ini");
//		创建SecurityManager
		SecurityManager securityManager=factory.getInstance();
//		将securityManager设置在当前的运行环境中
		SecurityUtils.setSecurityManager(securityManager);
//		从SecurityUtils里面创建一个subject
		Subject subject=SecurityUtils.getSubject();
//		在认证提交前准备token（令牌）
		UsernamePasswordToken token=new UsernamePasswordToken("zhangsan", "111");		
//		执行认证提交
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		是否认证通过
		boolean isAuthenticated=subject.isAuthenticated();
		System.out.println("是否认证通过"+isAuthenticated);
	
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
