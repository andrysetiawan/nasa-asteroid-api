package com.andry.nasa_asteroid_api.dto.response;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsteroidDetailResponse implements Serializable {
    private String id;
    private String name;
    private String nasaJplUrl;
    private Double absoluteMagnitude;
    private EstimateDIameter estimatedDiameter;
    private Boolean isHazardous;
    private List<CloseApproach> closeApproachData;
    private Boolean isSentryObject;
}
