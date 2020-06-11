package com.example.authenticatingldap.config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * @author jorian
 */
@Configuration
public class CustomerMvcConfigurerAdapter implements WebMvcConfigurer {



	/**
	 * 添加静态资源文件，外部可以直接访问地址
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		//需要告知系统，这是要被当成静态文件的！
		//第一个方法设置访问路径前缀，第二个方法设置资源路径
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resurces/swagger-ui.html");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resurces/webjars/");
		
				
	}


}
