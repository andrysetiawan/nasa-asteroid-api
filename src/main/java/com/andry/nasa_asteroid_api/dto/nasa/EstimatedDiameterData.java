package com.andry.nasa_asteroid_api.dto.nasa;

import lombok.Data;

@Data
public class EstimatedDiameterData {
    private DiameterUnitData kilometers;
    private DiameterUnitData meters;
    private DiameterUnitData miles;
    private DiameterUnitData feet;

}
