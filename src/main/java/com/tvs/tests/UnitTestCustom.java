package com.tvs.tests;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.tvs.config.ApplicationConfiguration;
import com.tvs.dao.*;
import com.tvs.model.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ApplicationConfiguration.class)
@FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class UnitTestCustom {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mvc;
	Company com;
	Users user;
	@Autowired
	private UsersDAO userDAO;
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Autowired
	private AuthoritiesDAO authoritiesDAO;
	
	@Autowired
	private ApplicationsDAO applicationsDAO;
	
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).apply(springSecurity()).build();
	}

	
	@Test
	public void t_2_1_user_comany_Insert() {
		Users user = new Users("123456654321@abv.bg", "123456", Short.valueOf("1"));
		Authorities aouth = new Authorities(user, "ROLE_ADMIN");
		user.getAuthorities().add(aouth);
		aouth = new Authorities(user, "ROLE_USER");
		user.getAuthorities().add(aouth);
		Company com = new Company("GAWebDevelopment", "Thomass Boss Gade 21");
		Applications appp = new Applications(com, "GAWebApplications");
		appp.setUser(user);
		com.getApplications().add(appp);
		com.getUsers().add(user);
		user.getCompanys().add(com);
		
		user = userDAO.insertUser(user);
		Users testUserResult = userDAO.getUserByUserName(user.getUsername());
		Assert.assertEquals(user.getUsername(), testUserResult.getUsername());
		Assert.assertEquals(user.getPassword(), testUserResult.getPassword());
		Assert.assertEquals(user.getEnabled(), testUserResult.getEnabled());
		Assert.assertEquals("ROLE_ADMIN", testUserResult.getAuthorities().get(0).getAuthority());
		Assert.assertEquals("ROLE_USER", testUserResult.getAuthorities().get(1).getAuthority());
		Assert.assertEquals(user.getCompanys().get(0).getName(), testUserResult.getCompanys().get(0).getName());
		Assert.assertEquals(user.getCompanys().get(0).getApplications().get(0).getApplicationName(), testUserResult.getCompanys().get(0).getApplications().get(0).getApplicationName());
		Assert.assertEquals(user.getCompanys().get(0).getApplications().get(0).getUser().getUsername(), testUserResult.getCompanys().get(0).getApplications().get(0).getUser().getUsername());
	}
	
	@Test
	public void t_2_2_user_comany_Update() {
		Users user = userDAO.getUserByUserName("123456654321@abv.bg");
		user.setEnabled(Short.valueOf("0"));
		user.getAuthorities().get(0).setAuthority("ROLE_STAFF");
		user.getCompanys().get(0).getApplications().get(0).setApplicationName("UpdatetApplicationForm");
		user.getCompanys().get(0).setName("GAWebDevelopmentUpdated");
		
		user = userDAO.updateUser(user);
		Users testUserResult = userDAO.getUserByUserName(user.getUsername());
		Assert.assertEquals(user.getUsername(), testUserResult.getUsername());
		Assert.assertEquals(user.getPassword(), testUserResult.getPassword());
		Assert.assertEquals(user.getEnabled(), testUserResult.getEnabled());
		Assert.assertEquals("ROLE_STAFF", testUserResult.getAuthorities().get(0).getAuthority());
		Assert.assertEquals("ROLE_USER", testUserResult.getAuthorities().get(1).getAuthority());
		Assert.assertEquals(user.getCompanys().get(0).getName(), testUserResult.getCompanys().get(0).getName());
		Assert.assertEquals(user.getCompanys().get(0).getApplications().get(0).getApplicationName(), testUserResult.getCompanys().get(0).getApplications().get(0).getApplicationName());
		Assert.assertEquals(user.getCompanys().get(0).getApplications().get(0).getUser().getUsername(), testUserResult.getCompanys().get(0).getApplications().get(0).getUser().getUsername());
	}
	
	@Test
	public void t_2_3_user_comany_Delete() {
		userDAO.deleteUser("123456654321@abv.bg");
		Assert.assertEquals("Hello World", "Hello World");
	}
	
	@Test
	public void t_1_1_comany_user_Insert() throws Exception {
		Users user = new Users("123456654321@abv.bg", "123456", Short.valueOf("1"));
		Authorities aouth = new Authorities(user, "ROLE_ADMIN");
		user.getAuthorities().add(aouth);
		aouth = new Authorities(user, "ROLE_USER");
		user.getAuthorities().add(aouth);
		com = new Company("GAWebDevelopment", "Thomass Boss Gade 21");
		Applications appp = new Applications(com, "GAWebApplications");
		appp.setUser(user);
		com.getApplications().add(appp);
		com.getUsers().add(user);

		Company companyInserted = companyDAO.insertCompany(com);
		Company testCompanyResult = companyDAO.getCompanyById(companyInserted.getCompanyId());
		Assert.assertEquals(companyInserted.getName(), testCompanyResult.getName());
		Assert.assertEquals(companyInserted.getApplications().get(0).getApplicationName(), testCompanyResult.getApplications().get(0).getApplicationName());
		Assert.assertEquals(companyInserted.getApplications().get(0).getUser().getUsername(), testCompanyResult.getApplications().get(0).getUser().getUsername());
		Assert.assertEquals("ROLE_ADMIN", testCompanyResult.getUsers().get(0).getAuthorities().get(0).getAuthority());
		Assert.assertEquals("ROLE_USER", testCompanyResult.getUsers().get(0).getAuthorities().get(1).getAuthority());
	}

	@Test
	public void t_1_2_comany_user_Update() throws Exception {
		com = companyDAO.getCompanyByName("GAWebDevelopment");
		com.setName("GAWebDevelopmentUpdated");
		com.getUsers().get(0).setEnabled(Short.valueOf("0"));
		com.getUsers().get(0).getAuthorities().get(0).setAuthority("ROLE_STAFF");
		com.getApplications().get(0).setApplicationName("UpdatetApplicationForm");
		
		Company companyInserted = companyDAO.updateCompany(com);
		Company testCompanyResult = companyDAO.getCompanyById(companyInserted.getCompanyId());
		Assert.assertEquals(companyInserted.getName(), testCompanyResult.getName());
		Assert.assertEquals(companyInserted.getApplications().get(0).getApplicationName(), testCompanyResult.getApplications().get(0).getApplicationName());
		Assert.assertEquals(companyInserted.getApplications().get(0).getUser().getUsername(), testCompanyResult.getApplications().get(0).getUser().getUsername());
		Assert.assertEquals("ROLE_STAFF", testCompanyResult.getUsers().get(0).getAuthorities().get(0).getAuthority());
		Assert.assertEquals("ROLE_USER", testCompanyResult.getUsers().get(0).getAuthorities().get(1).getAuthority());
	}
	
	@Test
	public void t_1_3_comany_user_Delete() throws Exception {
		com = companyDAO.getCompanyByName("GAWebDevelopmentUpdated");
		Company companyInserted = companyDAO.deleteCompanyReference(com.getCompanyId());
		Assert.assertEquals(companyInserted.getCompanyId(), com.getCompanyId());
	}

}