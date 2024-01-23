package com.diagnomind.web_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for starting the Spring Boot application.
 * The application serves as a web server.
 */
@SpringBootApplication
public class WebServer {
	
	/**
     * The main method to run the Spring Boot application.
     *
     * @param args Command-line arguments passed to the application.
     */
	public static void main(String[] args) {
		SpringApplication.run(WebServer.class, args);
	}
}
