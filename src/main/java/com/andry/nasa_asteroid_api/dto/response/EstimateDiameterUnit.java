package com.andry.nasa_asteroid_api.dto.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class EstimateDiameterUnit implements Serializable {
    private double estimatedDiameterMin;
    private double estimatedDiameterMax;
}
