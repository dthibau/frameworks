package org.formation.service;

import org.formation.model.Order;
import org.formation.model.OrderRepository;
import org.formation.notification.Courriel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	CircuitBreakerFactory cbFactory;
	
	public Order processOrder(Order order ) {
		
		String response = _sendMail(order);
		System.out.println(response);
		return orderRepository.save(order);
	}
	
	private String _sendMail(Order order) {
		
		Courriel courriel = new Courriel();
		courriel.setTo(order.getClient().getEmail());
		courriel.setSubject("Commande du "+order.getDate());
		courriel.setText("Félicitations pour votre commande");
		
//		ResponseEntity<String> response =restTemplate.postForEntity("http://notification-service/sendSimple", courriel, String.class);
		
		return cbFactory.create("notification-service").run(() -> restTemplate.postForObject("http://notification-service/sendSimple", courriel, String.class)
				, throwable -> "FALL-BACK");
		

		
		
	}
}
