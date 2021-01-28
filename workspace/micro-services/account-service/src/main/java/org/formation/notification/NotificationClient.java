package org.formation.notification;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="notification-service", fallback = NotificationClientFallback.class)
public interface NotificationClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/sendSimple")
	public String envoiCourrier(Courriel courriel);

}
