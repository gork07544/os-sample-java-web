package com.tvs.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.arrayWithSize;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import com.tvs.config.ApplicationConfiguration;
import com.tvs.dao.*;
import com.tvs.model.*;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = ApplicationConfiguration.class)
@FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class TVSTest {

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

	///=========   INTEGRATION TEST ( TEST ALL THE CONTROLERS )   ==================)
	@Test
	public void testAboutController() throws Exception {
		RequestBuilder request = get("/aboutus");
		mvc.perform(request).andExpect(status().isOk()).andExpect(model().attribute("message", ""));
	}
	@Test
	public void testContactsController() throws Exception {
		RequestBuilder request = get("/contacts");
		mvc.perform(request).andExpect(status().isOk()).andExpect(model().attribute("message", ""));
	}
	
	@Test
	public void testFileupload302() throws Exception {
		RequestBuilder request = get("/fileupload");
		mvc.perform(request).andExpect(status().isFound());
	}
	@Test
	@WithMockUser(username = "someuser", roles = { "USER" })
	public void testFileuploadControllerNotLoged() throws Exception {
		RequestBuilder request = get("/fileupload");
		mvc.perform(request).andExpect(status().isOk()).andExpect(model().attribute("message", "You can upload a file by click the botton"));
	}
	@Test
	public void testLogIn() throws Exception {
		RequestBuilder request = get("/login");
		mvc.perform(request).andExpect(status().isOk());
	}
	@Test
	public void testLogInError() throws Exception {
		RequestBuilder request = get("/login")
				.param("error", "");
		mvc.perform(request).andExpect(status().isOk()).andExpect(model().attribute("msg", "Invalid username or password!"));
	}
	@Test
	public void testLogInLogout() throws Exception {
		RequestBuilder request = get("/login")
				.param("logout", "");
		mvc.perform(request).andExpect(status().isOk()).andExpect(model().attribute("msg", "You've been logged out successfully."));
	}
	
	@Test
	public void testCreateUserGet() throws Exception {
		RequestBuilder request = get("/createuser");
		mvc.perform(request).andExpect(status().isOk()).andExpect(model().attribute("message", ""));
	}
	
	@Test
	public void testCreateUserPost() throws Exception {
		String username = "gorogoro@abv.bg";
		String password = "123456";
		Users us = new Users(username, password, Short.valueOf("1"));
		Authorities aouth = new Authorities(us, "ROLE_USER");
		us.getAuthorities().add(aouth);
		
		RequestBuilder request = post("/createuser")
				.param("username", username)
				.param("password", password)
				.with(csrf());
		mvc.perform(request).andExpect(status().isOk());
		
		
		Users testUserResult = userDAO.getUserByUserName(username);
		
		Users userDel = userDAO.deleteUser(username);
		
		Assert.assertEquals(us.getUsername(), testUserResult.getUsername());
		Assert.assertEquals(us.getPassword(), testUserResult.getPassword());
		Assert.assertEquals(us.getEnabled(), testUserResult.getEnabled());
		Assert.assertEquals("ROLE_USER", testUserResult.getAuthorities().get(0).getAuthority());
	}
	
	@Test
	@WithMockUser(username = "benza7545@abv.bg", roles = { "USER" , "ADMIN" } )
	public void testUser() throws Exception {
		RequestBuilder request = get("/user");
		Users testUserResult = userDAO.getUserByUserName("benza7545@abv.bg");
		mvc.perform(request).andExpect(status().isOk())
		.andExpect(model().attribute("message", any(Users.class)))
		.andExpect(model().attribute("message", hasProperty("username", equalTo("benza7545@abv.bg"))))
		.andExpect(model().attribute("message", hasProperty("enabled", equalTo(new Short("1")))))
		.andExpect(model().attribute("message", hasProperty("authorities", 
				hasItem(hasProperty("authority", is("ROLE_USER"))))));
		//List<Users> expectedPeople = asList(new Users());
	}
	
	@Test
	@WithMockUser(username = "someuser", roles = { "USER", "ADMIN"  })
	public void testSendEmil() throws Exception {
		RequestBuilder request = post("/sendemil")
				.with(csrf())
				.param("inputEmail", "asdddd@abv.bg")
				.param("inputName", "Jorko")
				.param("inputTitle", "Title")
				.param("comment", "Comment");
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isOk())
				.andExpect(request().asyncStarted()).andExpect(request().asyncResult(instanceOf(ModelAndView.class)))
				.andReturn();
		mvc.perform(asyncDispatch(mvcResult)).andExpect(status().isOk())
				.andExpect(model().attribute("message", "The function do not work."));
	}
	
	@Test
	public void testSendEmil302() throws Exception {
		RequestBuilder request = post("/sendemil")
				.with(csrf())
				.param("inputEmail", "asdddd@abv.bg")
				.param("inputName", "Jorko")
				.param("inputTitle", "Title")
				.param("comment", "Comment");
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isFound())
				.andReturn();
		mvc.perform(asyncDispatch(mvcResult)).andExpect(status().isOk());
	}
	@Test
	public void testSendEmil403() throws Exception {
		RequestBuilder request = post("/sendemil")
				.param("inputEmail", "asdddd@abv.bg")
				.param("inputName", "Jorko")
				.param("inputTitle", "Title")
				.param("comment", "Comment");
		MvcResult mvcResult = mvc.perform(request).andExpect(status().isForbidden())			
				.andReturn();
		mvc.perform(asyncDispatch(mvcResult)).andExpect(status().isForbidden());
	}

	@Test
	public void testSpringRest403() throws Exception {
		MvcResult mvcResult = mvc.perform(get("/springrest")).andExpect(status().isFound())
				.andReturn();
		mvc.perform(asyncDispatch(mvcResult)).andExpect(status().isOk());
	}


	
	//==============================================
	
	

	// Insert user with authorities
		@Test
		public void t_1_1_1_user_Insert() {
			Users user = new Users("123456654321@abv.bg", "123456", Short.valueOf("1"));
			Authorities aouth = new Authorities(user, "ROLE_ADMIN");
			user.getAuthorities().add(aouth);
			aouth = new Authorities(user, "ROLE_USER");
			user.getAuthorities().add(aouth);

			user = userDAO.insertUser(user);
			Users testUserResult = userDAO.getUserByUserName(user.getUsername());
			Assert.assertEquals(user.getUsername(), testUserResult.getUsername());
			Assert.assertEquals(user.getPassword(), testUserResult.getPassword());
			Assert.assertEquals(user.getEnabled(), testUserResult.getEnabled());
			Assert.assertEquals("ROLE_ADMIN", testUserResult.getAuthorities().get(0).getAuthority());
			Assert.assertEquals("ROLE_USER", testUserResult.getAuthorities().get(1).getAuthority());
		}

		// Insert authorities for a user
		@Test
		public void t_1_1_2_authority_Insert() {
			Users user = userDAO.getUserByUserName("123456654321@abv.bg");
			Authorities aouth = new Authorities(user, "ROLE_STUFF");
			// user.getAuthorities().add(aouth);

			Authorities aouthResult = authoritiesDAO.insertAuthority(aouth);

			Users testUserResult = userDAO.getUserByUserName(user.getUsername());
			Assert.assertEquals(user.getUsername(), testUserResult.getUsername());
			Assert.assertEquals(user.getPassword(), testUserResult.getPassword());
			Assert.assertEquals(user.getEnabled(), testUserResult.getEnabled());
			Assert.assertEquals("ROLE_ADMIN", testUserResult.getAuthorities().get(0).getAuthority());
			Assert.assertEquals("ROLE_STUFF", testUserResult.getAuthorities().get(1).getAuthority());
			Assert.assertEquals("ROLE_USER", testUserResult.getAuthorities().get(2).getAuthority());
		}

		// Insert authorities for a user
		@Test
		public void t_1_1_3_authority_Update() {
			Users user = userDAO.getUserByUserName("123456654321@abv.bg");
			Authorities aouthResult = authoritiesDAO.updateAuthority("123456654321@abv.bg", "ROLE_STUFF", "ROLE_STUFF2");
			Users testUserResult = userDAO.getUserByUserName("123456654321@abv.bg");
			Assert.assertEquals(user.getUsername(), testUserResult.getUsername());
			Assert.assertEquals(user.getPassword(), testUserResult.getPassword());
			Assert.assertEquals(user.getEnabled(), testUserResult.getEnabled());
			Assert.assertEquals("ROLE_ADMIN", testUserResult.getAuthorities().get(0).getAuthority());
			Assert.assertEquals("ROLE_STUFF2", testUserResult.getAuthorities().get(1).getAuthority());
			Assert.assertEquals("ROLE_USER", testUserResult.getAuthorities().get(2).getAuthority());
		}

		// Insert authorities for a user
		@Test
		public void t_1_1_4_authority_Delete() {
			Users user = userDAO.getUserByUserName("123456654321@abv.bg");
			Authorities aouth = new Authorities(user, "ROLE_STUFF");
			// user.getAuthorities().add(aouth);

			Authorities aouthResult = authoritiesDAO.deleteAuthority("123456654321@abv.bg", "ROLE_STUFF2");

			Users testUserResult = userDAO.getUserByUserName(user.getUsername());
			Assert.assertEquals(user.getUsername(), testUserResult.getUsername());
			Assert.assertEquals(user.getPassword(), testUserResult.getPassword());
			Assert.assertEquals(user.getEnabled(), testUserResult.getEnabled());
			Assert.assertEquals("ROLE_ADMIN", testUserResult.getAuthorities().get(0).getAuthority());
			Assert.assertEquals("ROLE_USER", testUserResult.getAuthorities().get(1).getAuthority());
		}

		
		
		// Insert Company with Application and  employee
		@Test
		public void t_1_2_comany_Insert() throws Exception {
			Users user = userDAO.getUserByUserName("123456654321@abv.bg");
			Company com = new Company("GAWebDevelopment" , "Thomas Boss Gade street 21");
			Applications appp = new Applications(com, "Start Application");
			appp.setUser(user);
			com.getApplications().add(appp);

			Company companyInserted = companyDAO.insertCompany(com);
			Company testCompanyResult = companyDAO.getCompanyById(companyInserted.getCompanyId());
			Assert.assertEquals(companyInserted.getName(), testCompanyResult.getName());
			//Assert.assertEquals(companyInserted.getApplications().get(0).getApplicationName(),
			//		testCompanyResult.getApplications().get(0).getApplicationName());
		}

		// Insert Application to the Company
		@Test
		public void t_1_3_1_application_Insert() throws Exception {
			Users user = userDAO.getUserByUserName("123456654321@abv.bg");
			Company com = companyDAO.getCompanyByName("GAWebDevelopment");
			Applications appp = new Applications(com, "TestApplicationGAWeb");
			appp.setUser(user);
			applicationsDAO.insertApplications(appp);
			
			Company testCompanyResult = companyDAO.getCompanyById(com.getCompanyId());
			Assert.assertEquals(appp.getApplicationName(),
					testCompanyResult.getApplications().get(1).getApplicationName());
		}
		
		// Update Application from the Company above
		@Test
		public void t_1_3_2_application_Update() throws Exception {
			Users user = userDAO.getUserByUserName("123456654321@abv.bg");
			Company com = companyDAO.getCompanyByName("GAWebDevelopment");
			com.getApplications().get(1).setApplicationName("Huuuioo");
			Applications appp = applicationsDAO.updateApplications(com.getApplications().get(1));

		}

		@Test
		public void t_1_3_3_application_Delete() throws Exception {
			Users user = userDAO.getUserByUserName("123456654321@abv.bg");
			Company com = companyDAO.getCompanyByName("GAWebDevelopment");
			Applications appp = applicationsDAO.deleteApplications(com.getApplications().get(1).getId());

		}

		// Add User to a Company.
		@Test
		public void t_1_4_comany_user_ATACH() throws Exception {
			Users user = userDAO.getUserByUserName("123456654321@abv.bg");
			Company com = companyDAO.getCompanyByName("GAWebDevelopment");
			user.getCompanys().add(com);
			com.getUsers().add(user);
			Company companyInserted = companyDAO.updateCompany(com);
			Company testCompanyResult = companyDAO.getCompanyById(companyInserted.getCompanyId());
			Assert.assertEquals(companyInserted.getName(), testCompanyResult.getName());
			Assert.assertEquals(companyInserted.getApplications().get(0).getApplicationName(),
					testCompanyResult.getApplications().get(0).getApplicationName());
		}

		// Remove User from a Company.
		@Test
		public void t_1_5_comany_user_DETACH() throws Exception {
			Users user = userDAO.getUserByUserName("123456654321@abv.bg");
			Company com = companyDAO.getCompanyByName("GAWebDevelopment");
			List<Users> users = com.getUsers();
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).getUsername().equals("123456654321@abv.bg")) {
					com.getUsers().remove(i);
				}
			}
			Company companyInserted = companyDAO.updateCompany(com);
			Company testCompanyResult = companyDAO.getCompanyById(companyInserted.getCompanyId());
			Assert.assertEquals(companyInserted.getName(), testCompanyResult.getName());
			Assert.assertEquals(companyInserted.getApplications().get(0).getApplicationName(),
					testCompanyResult.getApplications().get(0).getApplicationName());
		}

		// Add Company to a User.
		@Test
		public void t_1_6_user_comany_ATACH() throws Exception {
			Users user = userDAO.getUserByUserName("123456654321@abv.bg");
			Company com = companyDAO.getCompanyByName("GAWebDevelopment");
			com.getUsers().add(user);
			user.getCompanys().add(com);

			Users userUpdated = userDAO.updateUser(user);
			Users testUserResult = userDAO.getUserByUserName(user.getUsername());
			Assert.assertEquals(user.getUsername(), testUserResult.getUsername());
			Assert.assertEquals(user.getPassword(), testUserResult.getPassword());
			Assert.assertEquals(user.getEnabled(), testUserResult.getEnabled());
			Assert.assertEquals("ROLE_ADMIN", testUserResult.getAuthorities().get(0).getAuthority());
			Assert.assertEquals("ROLE_USER", testUserResult.getAuthorities().get(1).getAuthority());

		}

		// The detach is the same because it doesn't meter. We need user and company
		// to search.
		@Test
		public void t_1_7_comany_user_DETACH() throws Exception {
			Users user = userDAO.getUserByUserName("123456654321@abv.bg");
			Company com = companyDAO.getCompanyByName("GAWebDevelopment");
			List<Users> users = com.getUsers();
			for (int i = 0; i < users.size(); i++) {
				if (users.get(i).getUsername().equals("123456654321@abv.bg")) {
					com.getUsers().remove(i);
				}
			}
			Company companyInserted = companyDAO.updateCompany(com);
			Company testCompanyResult = companyDAO.getCompanyById(companyInserted.getCompanyId());
			Assert.assertEquals(companyInserted.getName(), testCompanyResult.getName());
			Assert.assertEquals(companyInserted.getApplications().get(0).getApplicationName(),
					testCompanyResult.getApplications().get(0).getApplicationName());
		}

		// Update Company records
		@Test
		public void t_1_8_comany_Update() throws Exception {
			Company com = companyDAO.getCompanyByName("GAWebDevelopment");
			com.setName("GAWebDevelopmentUpdated");
			com.getApplications().get(0).setApplicationName("UpdatetApplicationForm");

			Company companyInserted = companyDAO.updateCompany(com);
			Company testCompanyResult = companyDAO.getCompanyById(companyInserted.getCompanyId());
			Assert.assertEquals(companyInserted.getName(), testCompanyResult.getName());
			Assert.assertEquals(companyInserted.getApplications().get(0).getApplicationName(),
					testCompanyResult.getApplications().get(0).getApplicationName());
		}

		// Delete Company
		@Test
		public void t_1_9_comany_Delete() throws Exception {
			Company com = companyDAO.getCompanyByName("GAWebDevelopmentUpdated");
			Company companyInserted = companyDAO.deleteCompany(com.getCompanyId());
			Assert.assertEquals(companyInserted.getCompanyId(), com.getCompanyId());
		}

		// Update user
		@Test
		public void t_2_0_user_Update() {
			Users user = userDAO.getUserByUserName("123456654321@abv.bg");
			user.setEnabled(Short.valueOf("0"));
			user.getAuthorities().get(0).setAuthority("ROLE_STAFF");

			user = userDAO.updateUser(user);
			Users testUserResult = userDAO.getUserByUserName(user.getUsername());
			Assert.assertEquals(user.getUsername(), testUserResult.getUsername());
			Assert.assertEquals(user.getPassword(), testUserResult.getPassword());
			Assert.assertEquals(user.getEnabled(), testUserResult.getEnabled());
			Assert.assertEquals("ROLE_STAFF", testUserResult.getAuthorities().get(0).getAuthority());
			Assert.assertEquals("ROLE_USER", testUserResult.getAuthorities().get(1).getAuthority());
		}

		// Delete user and finish the test
		@Test
		public void t_2_1_user_Delete() {
			Users user = userDAO.getUserByUserName("123456654321@abv.bg");
			Users userDel = userDAO.deleteUser(user.getUsername());
			Assert.assertEquals(userDel.getUsername(), user.getUsername());
		}
		
		
		
		
		
		//==============================================		
		
		//==============================================
		
		
		
		
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