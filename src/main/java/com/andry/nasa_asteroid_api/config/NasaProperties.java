package com.andry.nasa_asteroid_api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ConfigurationProperties(prefix = "nasa.api")
public class NasaProperties {
    private String baseUrl;

    private String apiKey;
}
