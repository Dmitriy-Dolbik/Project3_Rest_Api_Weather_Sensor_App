package com.dolbik.Project3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class Project3RestApiWeatherSensorApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project3RestApiWeatherSensorApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
