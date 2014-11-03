package com.ailife.uip.doc.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;

/**
 * Created by chenmm on 9/22/2014.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan(value = "com.ailife.uip.doc")
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication uipApplication = new SpringApplication(Application.class);
		uipApplication.run(args);
	}

	@Bean
	public CharacterEncodingFilter characterEncodingFilter() {
		final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

}
