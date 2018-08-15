package com.lxc.shiro.md5;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

public class md5 {
	public static void main(String args[]) {
		
		String source="111111";
		String salt="eteokues";
		int  hashIterations=1;
//		迭代两次运行的结果为：d742e15e7d488eebb14a8fe2f04cd59b
//		迭代两次运行的结果为 :e3ea04e527e99092afc3d5191606cbb6
		
/*
 * 构造方法中：		
 * 第一个参数：source:明文，原始密码
 * 第二个参数：盐，通过使用随机数
 * 第三个参数：散列的次数，比如散列两次,相当于md5(md5(＇＇))
 */
		Md5Hash hash=new Md5Hash(source, salt, hashIterations);
		System.out.println(hash.toString());	
//		第一个参数：散列算法
		String algorithmName="md5";
		SimpleHash simpleHash=new SimpleHash(algorithmName, source, salt, hashIterations);
		System.out.println(simpleHash.toString());
	}
	
}
