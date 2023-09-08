package com.example.PowerUser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableCaching
@ComponentScan(basePackages = "com.example.PowerUser")
public class PowerUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerUserApplication.class, args);
	}

}
