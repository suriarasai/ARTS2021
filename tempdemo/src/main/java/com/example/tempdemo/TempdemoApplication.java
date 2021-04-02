package com.example.tempdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.example.tempdemo")
public class TempdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TempdemoApplication.class, args);
	}

}
