package com.web.ls.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailAuthConfig {
	
	@Bean
	public JavaMailSenderImpl getJavaMailSender() {
		Properties properties = new Properties();
		properties.put("mail.smtp.socketFactory.port", true);
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.starttls.required", true);
//		properties.put("mail.transport.protocol", "smtp");
		properties.put("mail.smtp.socketFactory.fallback", true);
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.debug", true);
		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("email");
		mailSender.setPassword("password");
		mailSender.setDefaultEncoding("utf-8");
		mailSender.setJavaMailProperties(properties);

		return mailSender;

	}

}