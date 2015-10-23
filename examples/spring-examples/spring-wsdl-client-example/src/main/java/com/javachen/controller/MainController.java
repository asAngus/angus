package com.javachen.controller;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javachen.service.WeatherService;

@Controller
public class MainController {

	@Autowired
	private WeatherService weatherService;

	@RequestMapping(value = "/index.html", method=RequestMethod.GET)
	public String getIndex() {
		return "index";
	}

	@RequestMapping(value = "/index.html", method=RequestMethod.POST)
	public String postIndex(@RequestParam("country") String country, @RequestParam("city") String city, ModelMap model) {
		model.addAttribute("country", country);
		model.addAttribute("city", city);
		try {
			model.addAttribute("weather", weatherService.getCurrentWeather(country, city));
		} catch (JAXBException ex) {
			model.addAttribute("error", "Problemen met het uitlezen van de response");
		}
		return "result";
	}
}
