package com.vidya.tools.email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class EmailValidatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailValidatorApplication.class, args);
	}
}
