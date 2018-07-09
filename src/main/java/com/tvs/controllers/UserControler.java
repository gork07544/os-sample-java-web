package com.tvs.controllers;

import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

//import com.tvs.dao.*;
import com.tvs.model.*;

@Controller
public class UserControler {

	
	/*
	@Autowired
	public UsersDAO usersDAO;

	@Autowired
	private AuthoritiesDAO authoritiesDAO;
	
	@Autowired
	private CompanyDAO companyDAO;
	
	@Autowired
	private ApplicationsDAO aplicationsDAO;

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public ModelAndView getUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Users user = usersDAO.getUserByUserName(name);
		return new ModelAndView("user", "message", user);
	}

	@RequestMapping(value = "/applications", method = RequestMethod.GET)
	public ModelAndView getApplication(@RequestParam("applicationId") int applicationId) {
		ModelAndView model = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Applications application = aplicationsDAO.getApplicationById(applicationId);
		model.addObject("message", "You are not authorized to see the resource.");
		for(Users applicationUser : application.getCompany().getUsers()){
			if(applicationUser.getUsername().equals(name)){
				model.addObject("message", application);
			}
		}
		model.setViewName("applications");
		return model;
	}
	
	@RequestMapping(value = "/attachuser", method = RequestMethod.POST)
	public ModelAndView attachUser(@RequestParam("username") String username,
			@RequestParam("authority") String authority) {
		Users user = usersDAO.getUserByUserName(username);
		Authorities aouth = null;
		String res = "The user do not excists.";
		if (user != null) {
			res = "The new authority " + authority + " is added to  the user.";
			aouth = new Authorities(user, authority);
			Authorities aouthResult = authoritiesDAO.insertAuthority(aouth);
			res = authority;
		}
		
		return new ModelAndView("user", "message", res);
	}

	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public ModelAndView createUser(@RequestParam("username") String username, @RequestParam("password") String password)
			throws ParseException {
		String res = "";
		if (!username.isEmpty() && username != null && !password.isEmpty() && password != null) {

			res = usersDAO.createUser(username, password);

		} else {
			res = "User name or password is empty!";
		}
		return new ModelAndView("createuser", "message", res);
	}

	@RequestMapping(value = "/createuser", method = RequestMethod.GET)
	public ModelAndView createUser() {

		String str = "";
		return new ModelAndView("createuser", "message", str);
	}
	*/
}
