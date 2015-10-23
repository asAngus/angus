package com.javachen.controller;

import com.javachen.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class MainController {

	@Autowired
	private MailService service;

	@Autowired
	private SimpleMailMessage message;

	@RequestMapping(value = "/index.html", method = RequestMethod.GET)
	public String getIndex() {
		return "index";
	}

	@RequestMapping(value = "/index.html", method = RequestMethod.POST)
	public String postIndex(@RequestParam("receiver") String receiver,
			ModelMap model) {
		ModelMap mailModel = new ModelMap();
		SimpleDateFormat format = new SimpleDateFormat("mmmm dd, yyyy");

		mailModel.addAttribute("title", "Hello");
		mailModel.addAttribute("subtitle", "hello world");
		mailModel
				.addAttribute(
						"text",
						"XXXXXXXXXXXXX");
		mailModel.addAttribute("date", format.format(new Date()));

		SimpleMailMessage msg = new SimpleMailMessage(message);
		msg.setTo(receiver);
		service.sendMail(mailModel, msg);

		model.addAttribute("receiver", receiver);
		return "index";
	}
}
