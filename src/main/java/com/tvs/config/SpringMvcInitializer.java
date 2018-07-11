package com.tvs.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringMvcInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	private static final String DEFAULT_UPLOAD_LOCATION = null;
	//set enviremont variable spring_profiles_active=dev in /etc/enviremont
	//private static final String springProfilesActive = System.getenv("spring_profiles_active");
	
	// If the @Profile beans are loaded via root context
	/*
	@Override
	protected WebApplicationContext createRootApplicationContext() {
		WebApplicationContext context = (WebApplicationContext)super.createRootApplicationContext();
		((ConfigurableEnvironment)context.getEnvironment()).setActiveProfiles(springProfilesActive);
		//Set multiple active profiles
		//((ConfigurableEnvironment)context.getEnvironment()).setActiveProfiles(new String[]{"dev", "testdb"});
		return context;
	}
	*/
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { ApplicationConfiguration.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement(DEFAULT_UPLOAD_LOCATION));
	}

}
