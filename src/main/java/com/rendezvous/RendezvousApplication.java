package com.rendezvous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class RendezvousApplication {

	public static void main(String[] args) {
		SpringApplication.run(RendezvousApplication.class, args);
	}

}
