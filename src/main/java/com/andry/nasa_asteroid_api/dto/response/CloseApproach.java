package com.andry.nasa_asteroid_api.dto.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class CloseApproach implements Serializable {
    private String closeApproachDate;
    private String closeApproachDateFull;
    private Long epochDateCloseApproach;
    private RelativeVelocity relativeVelocity;
    private MissDistance missDistance;
    private String orbitingBody;
}
