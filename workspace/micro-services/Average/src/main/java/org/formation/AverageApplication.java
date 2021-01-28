package org.formation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(AverageStream.class)
public class AverageApplication {

	public static void main(String[] args) {
		SpringApplication.run(AverageApplication.class, args);
	}

}
