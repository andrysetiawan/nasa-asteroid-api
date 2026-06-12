package com.andry.nasa_asteroid_api.dto.response;

import lombok.Data;

@Data
public class RelativeVelocity {
    private String kilometersPerSecond;
    private String kilometersPerHour;
    private String milesPerHour;
}
