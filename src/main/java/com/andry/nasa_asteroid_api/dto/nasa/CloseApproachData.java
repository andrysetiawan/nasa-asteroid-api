package com.andry.nasa_asteroid_api.dto.nasa;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CloseApproachData {
    @JsonProperty("close_approach_date")
    private String closeApproachDate;

    @JsonProperty("close_approach_date_full")
    private String closeApproachDateFull;

    @JsonProperty("epoch_date_close_approach")
    private Long epochDateCloseApproach;

    @JsonProperty("relative_velocity")
    private RelativeVelocityData relativeVelocity;

    @JsonProperty("miss_distance")
    private MissDistanceData missDistance;

    @JsonProperty("orbiting_body")
    private String orbitingBody;
}
