package com.example.springstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.module.Configuration;

@SpringBootApplication
public class SpringStoreApplication {

	public static void main(String[] args) {
	ConfigurableApplicationContext context= SpringApplication.run(SpringStoreApplication.class, args);
		PasswordEncoder encoder= context.getBean(PasswordEncoder.class);
		System.out.println(encoder.encode("admin"));
	}

}
