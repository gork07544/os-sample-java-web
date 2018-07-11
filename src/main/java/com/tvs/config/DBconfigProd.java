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
import org.springframework.context.annotation.Profile;
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

import com.tvs.model.*;

@EnableAsync
@Configuration
@EnableWebMvc
@Profile("prod") //based on env variable spring_profiles_active=prod
public class DBconfigProd extends WebMvcConfigurerAdapter {

	private String host = "";
	private String port = "";
	private String username = "";
	private String password = "";
	private String url = "";
	private String hibernate_driver_class = "";
	private String hibernate_dialect = "";

	private void setLocalEnvironmentVariables() {
			this.host = "jws-app-mysql";// "127.0.0.1";
			this.port = "3306";
			this.username = System.getenv("DB_USERNAME");
			this.password = System.getenv("DB_PASSWORD");
			this.url = String.format("jdbc:mysql://%s:%s/root", host, port);
			this.hibernate_driver_class = "com.mysql.jdbc.Driver";
			this.hibernate_dialect = "org.hibernate.dialect.MySQLDialect";

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

	// ========== Initialize Session Factory for Hibernate ==============
	@Bean(name = "sessionFactory")
	public SessionFactory sessionFactory() {
		try {
			setLocalEnvironmentVariables();
			org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
			configuration.addAnnotatedClass(Users.class).addAnnotatedClass(Authorities.class)
					.addAnnotatedClass(Company.class).addAnnotatedClass(Applications.class)
					// .addResource("Users.hbm.xml").addResource("Authorities.hbm.xml")
					.setProperty("hibernate.connection.driver_class", hibernate_driver_class)
					.setProperty("hibernate.connection.url", url).setProperty("hibernate.connection.username", username)
					.setProperty("hibernate.connection.password", password)
					.setProperty("hibernate.id.new_generator_mappings", "false")
					.setProperty("hibernate.dialect", hibernate_dialect);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}


}