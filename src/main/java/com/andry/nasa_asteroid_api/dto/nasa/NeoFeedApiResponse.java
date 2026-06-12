package com.andry.nasa_asteroid_api.dto.nasa;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NeoFeedApiResponse {

    @JsonProperty("element_count")
    private Integer elementCount;

    @JsonProperty("near_earth_objects")
    private Map<LocalDate, List<NearEarthObjectData>> nearEarthObjects;


}
