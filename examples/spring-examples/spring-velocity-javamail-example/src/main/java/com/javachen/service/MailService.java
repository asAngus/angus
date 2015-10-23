package com.javachen.service;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailService {

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private JavaMailSender mailSender;

	public void sendMail(final ModelMap model, final SimpleMailMessage msg) {
		mailSender.send(new MimeMessagePreparator() {

			@Override
			public void prepare(MimeMessage mimeMessage)
					throws MessagingException {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
				message.setTo(msg.getTo());
				message.setFrom(msg.getFrom());
				message.setSubject(msg.getSubject());

				String body = VelocityEngineUtils.mergeTemplateIntoString(
						velocityEngine, "/template.vm", model);
				message.setText(body, true);
				
				message.addInline("facebook", new ClassPathResource(
						"images/facebook.gif"));
				message.addInline("header", new ClassPathResource(
						"images/header.jpg"));
				message.addInline("pic1", new ClassPathResource(
						"images/pic1.jpg"));
				message.addInline("tweet", new ClassPathResource(
						"images/tweet.gif"));
			}
		});
	}
}
