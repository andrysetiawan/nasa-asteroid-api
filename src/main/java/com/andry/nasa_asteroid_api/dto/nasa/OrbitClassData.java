package com.andry.nasa_asteroid_api.dto.nasa;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class OrbitClassData {
    @JsonProperty("orbit_class_type")
    private String orbitClassType;

    @JsonProperty("orbit_class_description")
    private String orbitClassDescription;

    @JsonProperty("orbit_class_range")
    private String orbitClassRange;

}
