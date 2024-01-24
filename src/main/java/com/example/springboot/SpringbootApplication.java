package com.example.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RestController
public class SpringbootApplication {

	@GetMapping
	public String message() {
		return "welcome to Mr. DevOps Youtube Channel";
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);

	}

}
