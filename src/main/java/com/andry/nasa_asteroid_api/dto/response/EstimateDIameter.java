package com.andry.nasa_asteroid_api.dto.response;

import lombok.Data;

@Data
public class EstimateDIameter {
    private EstimateDiameterUnit kilometers;
    private EstimateDiameterUnit meters;
    private EstimateDiameterUnit miles;
    private EstimateDiameterUnit feet;
}
