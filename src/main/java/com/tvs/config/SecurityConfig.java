package com.tvs.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Profile("dev")
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
   
    @Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	//auth.authenticationProvider(customAuthenticationProvider);
    	//auth.userDetailsService(userDetailsService);
	  auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
			"select username,password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
			"select username, authority from authorities where username=?");
	}	
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
     
    	http
         .authorizeRequests()
          .antMatchers("/index*").permitAll()
         	.antMatchers("/sitemap.xml").permitAll()
         	.antMatchers("/robots.txt").permitAll()
         	 .antMatchers("/csss/*").permitAll()
         	 .antMatchers("/images/*").permitAll()
         	 .antMatchers("/images/above_front/*").permitAll()
         	 .antMatchers("/images/front_page_ban/*").permitAll()
         	 .antMatchers("/images/social/*").permitAll()
         	 .antMatchers("/images/team/*").permitAll()
         	.antMatchers("/createuser/*").permitAll()
         	.antMatchers("/createuser*").permitAll()
         	.antMatchers("/aboutus/*").permitAll()
         	.antMatchers("/aboutus*").permitAll()   
         	.antMatchers("/projects/*").permitAll()
         	.antMatchers("/projects*").permitAll() 
         	.antMatchers("/contacts/*").permitAll()
         	.antMatchers("/contacts*").permitAll()  
         	.antMatchers("/login/*").permitAll()
         	.antMatchers("/login*").permitAll() 
         	.antMatchers("/error/*").permitAll()
         	.antMatchers("/error*").permitAll()
         	 //.antMatchers("/welcome*").hasRole("ADMIN")
             .anyRequest().authenticated() 
             .and()
        .formLogin()
             .loginPage("/login").failureUrl("/login?error")
             .permitAll()
             //.defaultSuccessUrl("/welcome")

             .and()
         .logout().logoutSuccessUrl("/login?logout")                                   
             .permitAll()
             .and()
         .requiresChannel()
//         	 .antMatchers("/index*").requiresInsecure()
//         	 .antMatchers("/css/*").requiresInsecure()
//         	 .antMatchers("/login*").requiresSecure()         
//        	 .antMatchers("/aboutus*").requiresInsecure()
//         	 .antMatchers("/contacts*").requiresInsecure()
 			.anyRequest().requiresSecure()
//       	.anyRequest().requiresInsecure()
        	.and()
        .sessionManagement()
            .maximumSessions(1)
            .expiredUrl("/login?expired");
    }

    
    
    
}


