package com.lxc.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

public class CustomRealmMD5 extends AuthorizingRealm{
//	设置realm的名称
	public void setName(String name) {
		super.setName("CustomerRealmMD5");
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
		if(!userCode.equals("zhangsansi")) {
			return null;
		}
//		模拟从数据库中查询到了密码
		String password="d742e15e7d488eebb14a8fe2f04cd59b";
//		盐
		String salt="fjsdbaguja";
//		查询到就返回       认证信息AuthenticationInfo
		SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo
								(userCode, password, ByteSource.Util.bytes(salt), this.getName());
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
		// TODO Auto-generated method stub
		return null;
	}


}
