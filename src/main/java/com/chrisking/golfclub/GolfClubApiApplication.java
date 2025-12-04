package com.chrisking.golfclub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Golf Club API Spring Boot application.
 * This class initializes and runs the Spring Boot application with auto-configuration enabled.
 */
@SpringBootApplication
public class GolfClubApiApplication {

	/**
	 * Main method - starts the Spring Boot application.
	 * @param args command-line arguments passed to the application
	 */
	public static void main(String[] args) {
		SpringApplication.run(GolfClubApiApplication.class, args);
	}

}
