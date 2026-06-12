package com.andry.nasa_asteroid_api.dto.nasa;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class DiameterUnitData {

    @JsonProperty("estimated_diameter_min")
    private Double estimatedDiameterMin;

    @JsonProperty("estimated_diameter_max")
    private Double estimatedDiameterMax;

}
