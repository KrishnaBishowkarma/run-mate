package com.krishna.runmate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RunMateApplication {

	private static final Logger log = LoggerFactory.getLogger(RunMateApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(RunMateApplication.class, args);
		log.info("RunMate application started successfully.");
	}
}