package com.andry.nasa_asteroid_api.dto.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class RelativeVelocity implements Serializable {
    private String kilometersPerSecond;
    private String kilometersPerHour;
    private String milesPerHour;
}
