package com.tvs.config;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.List;
import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mobile.device.DeviceHandlerMethodArgumentResolver;
import org.springframework.mobile.device.DeviceResolverHandlerInterceptor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;



@EnableAsync
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.tvs.model",
								"com.tvs.dao",
								"com.tvs.controllers",
								"com.tvs.config",
								"com.tvs.services" })
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {


	private String host = "jws-app-mysql";
	private String port = "3306";
	private String username = System.getenv("MYSQL_USER");
	private String password = System.getenv("MYSQL_PASSWORD");
	private String url = String.format("jdbc:mysql://%s:%s/mytomcatapp", host, port);
	private String hibernate_driver_class = "com.mysql.jdbc.Driver";
	private String hibernate_dialect = "org.hibernate.dialect.MySQLDialect";
	
	private void setLocalEnvironmentVariables(){
		//If local testing environment we need to reset the DB configurations
		if(!local){
		//if(this.host == null || this.port == null || this.username == null || this.password == null || this.hibernate_driver_class == null || this.hibernate_dialect == null || this.url == null){
			this.host = "127.0.0.1";
			this.port = "3306";
			this.username = "root";//root";
			this.password = "GeorgiAndonov82!";//"GeorgiAndonov82!";
			this.url = String.format("jdbc:mysql://%s:%s/tvs", host, port);
			//this.url = String.format("jdbc:sqlserver://%s:%s;DatabaseName=ActionRA2Jmw7Ohg", host, port);
			//this.hibernate_driver_class = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			//this.hibernate_dialect = "org.hibernate.dialect.SQLServerDialect";
		}else {
		

		this.host = "jws-app-mysql";//"127.0.0.1";
		this.port = "3306";
		this.username = System.getenv("MYSQL_USER");
		this.password = System.getenv("MYSQL_PASSWORD");
		this.url = String.format("jdbc:mysql://%s:%s/root", host, port);
		}
	}
	
	/////VERY IMPORTANT SET THE ENVIREMONT TO LOCAL
	/// 1.change to false and add database cartage to the build job gear
	////2. change the configurations bellow
	//private boolean local = true;
	private boolean local = false;
	
	
	// ========== Initialize jsp ViewResolver ==============
@Bean
public ViewResolver viewResolver() {
	InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	viewResolver.setViewClass(JstlView.class);
	viewResolver.setPrefix("/WEB-INF/jsp/");
	viewResolver.setSuffix(".jsp");
	return viewResolver;
}

// ========== DATASOURCE for MySQL ==============
@Bean(name = "dataSource")
public DataSource dataSource() {
	setLocalEnvironmentVariables();
	DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
	driverManagerDataSource.setDriverClassName(hibernate_driver_class);
	driverManagerDataSource.setUrl(url);
	driverManagerDataSource.setUsername(username);
	driverManagerDataSource.setPassword(password);
	return driverManagerDataSource;
}


}