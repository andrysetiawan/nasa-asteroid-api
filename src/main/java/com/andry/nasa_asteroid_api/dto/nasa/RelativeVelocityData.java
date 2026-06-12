package com.andry.nasa_asteroid_api.dto.nasa;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class RelativeVelocityData {
    @JsonProperty("kilometers_per_second")
    private String kilometersPerSecond;

    @JsonProperty("kilometers_per_hour")
    private String kilometersPerHour;

    @JsonProperty("miles_per_hour")
    private String milesPerHour;

}
