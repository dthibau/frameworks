package org.formation.controller;

import org.formation.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {


	@Autowired
	EmailService emailService;
	
	@Value("${server.port}")
	int port;
	
	@RequestMapping(path = "/sendSimple", method = RequestMethod.POST)
	public String sendSimpleMessage(@RequestBody Email email) {
	
		emailService.sendSimpleMessage(email.getTo(), email.getSubject(), email.getText());
		
		return "OK from " + port;
//		return "OK";
		
	}
}
