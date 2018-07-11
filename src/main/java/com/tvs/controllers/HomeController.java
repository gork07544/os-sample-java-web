package com.tvs.controllers;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {

	@RequestMapping("/aboutus")
	public ModelAndView aboutUsF(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		return new ModelAndView("aboutus", "message", "");
	}
		
	@RequestMapping("/contacts")
	public ModelAndView contactGet(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		String s = "";
		return new ModelAndView("contacts", "message", s);
	}


		
	@RequestMapping("/projects")
	public ModelAndView projF(Device device, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {
		String str = "";
		//Device device = DeviceUtils.getCurrentDevice(request);
		if (device.isMobile()) {
			str = "Hello mobile client!";
        } else if (device.isTablet()) {
        	str = "Hello tablet client!";
        } else {
        	str = "Hello desktop client!";         
        }
		return new ModelAndView("projects", "message", str);
	}

	
}
