package com.example.logicproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
@SpringBootApplication
public class LogicProject {

	public static void main(String[] args) {
		SpringApplication.run(LogicProject.class, args);
	}
	  protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(LogicProject.class);
	    }
	
	
}
