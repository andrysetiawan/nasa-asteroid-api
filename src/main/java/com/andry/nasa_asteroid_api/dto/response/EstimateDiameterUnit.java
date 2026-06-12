package com.andry.nasa_asteroid_api.dto.response;

import lombok.Data;

@Data
public class EstimateDiameterUnit {
    private double estimatedDiameterMin;
    private double estimatedDiameterMax;
}
