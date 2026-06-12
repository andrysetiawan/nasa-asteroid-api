package com.andry.nasa_asteroid_api.dto.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class EstimateDIameter implements Serializable {
    private EstimateDiameterUnit kilometers;
    private EstimateDiameterUnit meters;
    private EstimateDiameterUnit miles;
    private EstimateDiameterUnit feet;
}
