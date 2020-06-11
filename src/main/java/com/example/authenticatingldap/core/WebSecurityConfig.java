 package com.example.authenticatingldap.core;


 import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
 import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
 import org.springframework.security.config.annotation.web.builders.HttpSecurity;
 import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
 import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.alibaba.fastjson.JSON;
import com.example.authenticatingldap.common.response.ResponseResult;
 
 @Configuration
 @EnableWebSecurity
 public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

 	@Override
 	protected void configure(HttpSecurity http) throws Exception {
 		http
 			.authorizeRequests()
	 			.antMatchers("/doc.html","/static/*","/swagger-ui.html","/webjars/*")//要放行的资源，对swagger放行
	 			.permitAll()
	 			.anyRequest()
	 			.fullyAuthenticated()
	 		.and()
 			.formLogin()
 			.and()
 			.csrf().disable().exceptionHandling()
 			.authenticationEntryPoint(getAuthenticationEntryPoint())
 			; 

 	}

 	/**
 	 * 
 	 * 去除前后端分离中的Security默认跳转页面
 	 * @return
 	 */
 	AuthenticationEntryPoint getAuthenticationEntryPoint() {
 		return new AuthenticationEntryPoint() {

			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
				response.setHeader("Content-Type", "application/json;charset=utf-8");
				try {
		            response.getWriter().write(JSON.toJSONString(ResponseResult.builder()
		                    .code(203)
		                    .msg("请登录")
		                    .build()));
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
				
			}
 			
 		};
 	}
 }
