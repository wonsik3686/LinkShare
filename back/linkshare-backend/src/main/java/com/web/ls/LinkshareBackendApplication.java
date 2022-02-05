package com.web.ls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LinkshareBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(LinkshareBackendApplication.class, args);
	}

}
