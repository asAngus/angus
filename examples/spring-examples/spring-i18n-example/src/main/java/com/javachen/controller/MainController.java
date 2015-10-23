package com.javachen.controller;

import com.javachen.domain.User;
import com.javachen.locale.LocaleStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.GregorianCalendar;

@Controller
public class MainController {
	
	@Autowired
	private LocaleStorage storage;

	@RequestMapping(value = "/index.html")
	public String getIndex(ModelMap model) {
		model.addAttribute("user", new User("Gates", "Bill",
				new GregorianCalendar(1955, 9, 28), true));
		return "index";
	}
	
	@RequestMapping(value = "/test.html")
	public String getTest(HttpServletRequest request, ModelMap model) {
		model.addAttribute("test", storage.get(RequestContextUtils.getLocale(request), "label.birthday"));
		return "test";
	}
}