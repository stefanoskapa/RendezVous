package com.rendezvous;

import com.rendezvous.repository.AppointmentRepository;
import com.rendezvous.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication


//@EnableJpaRepositories(basePackageClasses = AppointmentRepository.class)
//@EnableJpaRepositories(basePackages="com.rendezvous.repository")
public class RendezvousApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(RendezvousApplication.class, args);
	}
        
        @Bean
        public RestTemplate restTemplate(){
            return new RestTemplate();
        }

}
