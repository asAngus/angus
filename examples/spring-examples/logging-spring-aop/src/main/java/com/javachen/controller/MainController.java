package com.javachen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javachen.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	private UserService service;

	@RequestMapping(value = "/bill.html")
	public String getBill(ModelMap model) {
		model.addAttribute("user", service.getBillGates());
		return "index";
	}
	
	@RequestMapping(value = "/steve.html")
	public String getSteve(ModelMap model) {
		model.addAttribute("user", service.getSteveJobs());
		return "index";
	}
}