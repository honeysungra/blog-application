package com.springboot.blogapp;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition (
		info = @Info(
					title = "Spring Boot Blogging Application REST APIs",
					description = "Spring Boot Blogging Application REST APIs Documentation",
					version = "v1.0",
					contact = @Contact(
								name = "Honey Sungra",
								email = "honeysungra@gmail.com",
								url = ""
							)
//					license = @License(
//								name = "Apache 2.0",
//								url = ""
//							)
				),
		externalDocs = @ExternalDocumentation(
					description = "Spring Boot Blogging Application REST APIs",
					url="https://github.com/honeysungra/blog-application"
				)
			
		)
public class BlogApplicationSpringBootApplication {
	
	@Bean 
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(BlogApplicationSpringBootApplication.class, args);
	}

}
