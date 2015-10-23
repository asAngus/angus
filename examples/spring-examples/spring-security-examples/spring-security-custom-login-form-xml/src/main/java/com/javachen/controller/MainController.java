package com.javachen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class MainController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getIndexPage(Principal principal, ModelMap model) {
		if (principal != null) {
			model.addAttribute("name", principal.getName());
		}
		return "index";
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String getUserPage(Principal principal, ModelMap model) {
		return getIndexPage(principal, model);
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getAdminPage() {
		return "admin";
	}


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
							  @RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username and password!");
		}

		if (logout != null) {
			model.addObject("message", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

}
