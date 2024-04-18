package com.openclassrooms.mddapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@ComponentScan("com.openclassrooms.*")
@OpenAPIDefinition(info = @Info(title = "Hello World", version = "1.0.0", description = "Test", termsOfService = "runcodenow", contact = @Contact(name = "Nicolas", email = "n@gmail.com"), license = @License(name = "licence", url = "runcodenow")))
public class MddApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MddApiApplication.class, args);
	}

}
