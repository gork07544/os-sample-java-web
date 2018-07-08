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

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.tvsconsult.model.Applications;
import com.tvsconsult.model.Authorities;
import com.tvsconsult.model.Company;
import com.tvsconsult.model.Users;

@EnableAsync
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.tvsconsult.model", "com.tvsconsult.dao", "com.tvsconsult.controller",
		"com.tvsconsult.config", "com.tvsconsult.services" })
public class ApplicationConfiguration extends WebMvcConfigurerAdapter {


	private String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
	private String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
	private String username = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
	private String password = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
	private String url = String.format("jdbc:mysql://%s:%s/mytomcatapp", host, port);
	private String hibernate_driver_class = "com.mysql.jdbc.Driver";
	private String hibernate_dialect = "org.hibernate.dialect.MySQLDialect";
	
	private final String storageConnectionString = "DefaultEndpointsProtocol=https;" + "AccountName=gastore;"
			+ "AccountKey=TU00yn4hULJT+bMGN9LfwvgjMaKD99yPJiolo1u+HlqVof9LuaGV5v0qhrNFTvO7qmKMFyqBjUV84S3UXqA++Q==";
	private final String zohoUsername = "georgi@tvsconsult.com";
	private final String zohoPassword = "8205257544Gg";
	
	/////VERY IMPORTANT SET THE ENVIREMONT TO LOCAL
	/// 1.change to false and add database cartage to the build job gear
	////2. change the configurations bellow
	private boolean local = false;
	
	

	/*
	
		// ========== Initialize jsp ViewResolver ==============
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	
	
	// ===============      Device detection Interseptor ==============
	@Bean
	public DeviceResolverHandlerInterceptor deviceResolverHandlerInterceptor() {
		return new DeviceResolverHandlerInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(deviceResolverHandlerInterceptor());
	}

	// ===== Pass the current Device as an argument to one of your @Controller
	// methods,
	@Bean
	public DeviceHandlerMethodArgumentResolver deviceHandlerMethodArgumentResolver() {
		return new DeviceHandlerMethodArgumentResolver();
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(deviceHandlerMethodArgumentResolver());
	}

	// ========== Register static ResourceHandler ==============
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/csss/**").addResourceLocations("/resources/css/");
		registry.addResourceHandler("/images/**").addResourceLocations("/resources/images/");
		registry.addResourceHandler("/**").addResourceLocations("/resources/");
	}
	
	private void setLocalEnvironmentVariables(){
		//If local testing environment we need to reset the DB configurations
		if(!local){
		//if(this.host == null || this.port == null || this.username == null || this.password == null || this.hibernate_driver_class == null || this.hibernate_dialect == null || this.url == null){
			this.host = "127.0.0.1";
			this.port = "3306";
			this.username = "userRv6";//root";
			this.password = "k7WDcGcE";//"GeorgiAndonov82!";
			this.url = String.format("jdbc:mysql://%s:%s/root", host, port);
			//this.url = String.format("jdbc:sqlserver://%s:%s;DatabaseName=ActionRA2Jmw7Ohg", host, port);
			//this.hibernate_driver_class = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			//this.hibernate_dialect = "org.hibernate.dialect.SQLServerDialect";
		}
		

		this.host = "jws-app-mysql";//"127.0.0.1";
		this.port = "3306";
		this.username = "root";
		this.password = "GeorgiAndonov82!";
		this.url = String.format("jdbc:mysql://%s:%s/tvs", host, port);
	}
	
	// ========== Initialize Session Factory for Hibernate ==============
	@Bean(name = "sessionFactory")
	public SessionFactory sessionFactory() {
		try {
			setLocalEnvironmentVariables();
			org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
			configuration.addAnnotatedClass(Users.class)
			.addAnnotatedClass(Authorities.class)
			.addAnnotatedClass(Company.class)
			.addAnnotatedClass(Applications.class)
					// .addResource("Users.hbm.xml").addResource("Authorities.hbm.xml")
					.setProperty("hibernate.connection.driver_class", hibernate_driver_class)
					.setProperty("hibernate.connection.url", url)
					.setProperty("hibernate.connection.username", username)
					.setProperty("hibernate.connection.password", password)
					.setProperty("hibernate.id.new_generator_mappings","false")
					.setProperty("hibernate.dialect", hibernate_dialect);
			ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			return configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
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
	
	

	// ========== Initialize zohoSessionSMTP ==============
	@Bean
	public Session zohoSessionSMTP() {
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.host", "smtp.zoho.com");
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallback", "false");
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.socketFactory.port", "465");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.debug", "false");
		properties.put("mail.store.protocol", "pop3");
		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.debug.auth", "false");
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(zohoUsername, zohoPassword);
			}
		});
		return session;
	}
	

	// ========== Initialize MultipartResolver for FileUpload ==============
	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}


	// ========== Initialize AzureBlobStorageClient ==============
	@Bean
	public CloudBlobClient azureBlobStorage() throws InvalidKeyException, URISyntaxException {
		// Retrieve storage account from connection-string.
		CloudStorageAccount storageAccount = CloudStorageAccount.parse(storageConnectionString);
		return storageAccount.createCloudBlobClient();
	}
	*/
}