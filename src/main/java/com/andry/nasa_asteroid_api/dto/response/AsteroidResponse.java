package com.andry.nasa_asteroid_api.dto.response;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsteroidResponse implements Serializable {
    private String id;
    private String name;
    private String nasaJplUrl;
    private Double absoluteMagnitude;
    private Boolean isHazardous;
    private LocalDate date;
    private Double distanceKm;
    private String orbitingBody;

}
