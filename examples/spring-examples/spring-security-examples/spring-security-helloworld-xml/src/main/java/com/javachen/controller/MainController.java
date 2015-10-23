package com.javachen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	
	@RequestMapping(value = "/logout-success", method = RequestMethod.GET)
	public String getLogoutPage(ModelMap model) {
		model.addAttribute("message", "Logout success.");
		return "index";
	}
}
