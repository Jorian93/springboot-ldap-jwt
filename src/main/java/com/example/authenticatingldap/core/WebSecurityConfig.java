// /*
//  * @Description: 
//  * @Author: jorian
//  * @Date: 2020-05-28 21:38:55
//  */ 
// package com.example.authenticatingldap.core;


// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

// 	@Override
// 	protected void configure(HttpSecurity http) throws Exception {
// 		http
// 			.authorizeRequests()
// 				.anyRequest().fullyAuthenticated()
// 				.and()
// 			.formLogin();
// 	}

// 	@Override
// 	public void configure(AuthenticationManagerBuilder auth) throws Exception {
// 		auth
// 			.ldapAuthentication()
// 				.userDnPatterns("uid={0},ou=people")
// 				.groupSearchBase("ou=groups")
// 				.contextSource()
// 					.url("ldap://localhost:8111/dc=springframework,dc=org")
// 					.and()
// 				.passwordCompare()
// 					.passwordAttribute("userPassword");
// 	}

// }
