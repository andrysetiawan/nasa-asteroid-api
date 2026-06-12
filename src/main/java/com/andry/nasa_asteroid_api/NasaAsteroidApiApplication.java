package com.andry.nasa_asteroid_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.andry.nasa_asteroid_api.config.NasaProperties;

@SpringBootApplication
@EnableConfigurationProperties(NasaProperties.class)
public class NasaAsteroidApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NasaAsteroidApiApplication.class, args);
	}

}
