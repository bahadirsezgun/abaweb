package com.abasus.wadoclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAutoConfiguration
@ServletComponentScan
@EntityScan(basePackages={"com.abasus"})
@EnableJpaRepositories("com.abasus")
@ComponentScan(basePackages={"com.abasus"})
@SpringBootApplication
public class WadoClientApplication {

	
	
	public static void main(String[] args) {
		SpringApplication.run(WadoClientApplication.class, args);
	}
}
