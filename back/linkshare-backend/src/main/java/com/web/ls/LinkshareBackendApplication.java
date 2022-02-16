package com.web.ls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LinkshareBackendApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.properties,"
			+ "classpath:aws.yml";

	public static void main(String[] args) {
//		SpringApplication.run(LinkshareBackendApplication.class, args);
		new SpringApplicationBuilder(LinkshareBackendApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

}
