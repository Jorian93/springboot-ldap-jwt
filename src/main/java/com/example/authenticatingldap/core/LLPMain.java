package com.example.authenticatingldap.core;

import javax.naming.NamingException;

import org.springframework.http.ResponseEntity;

public class LLPMain {

	public static void main(String[] args) {
		// utils test	
//		try {
//				LDAPUtils.executeLogin("juyan.ye@ncsi.com.cn", "Yjy04160434");
//			} catch (NamingException e) {
//				// TODO Auto-generated catch block
//				System.out.print("登陆失败");
//			}
			
			
			// controller test
			AuthController authc = new AuthController();
			try {
				ResponseEntity<String> re =authc.authByAd("juyan.ye", "Yjy04160434");
				System.out.println(re);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				System.out.print("登陆失败");
			}
			
	}
	

}
