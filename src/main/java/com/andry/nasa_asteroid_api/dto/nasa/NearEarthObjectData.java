package com.andry.nasa_asteroid_api.dto.nasa;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class NearEarthObjectData {
    private String id;

    @JsonProperty("neo_reference_id")
    private String neoReferenceId;

    private String name;

    @JsonProperty("nasa_jpl_url")
    private String nasaJplUrl;

    @JsonProperty("absolute_magnitude_h")
    private Double absoluteMagnitudeH;

    @JsonProperty("estimated_diameter")
    private EstimatedDiameterData estimatedDiameter;

    @JsonProperty("is_potentially_hazardous_asteroid")
    private Boolean isPotentiallyHazardousAsteroid;

    @JsonProperty("is_sentry_object")
    private Boolean isSentryObject;

    @JsonProperty("close_approach_data")
    private List<CloseApproachData> closeApproachData;



}
