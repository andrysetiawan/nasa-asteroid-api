package com.andry.nasa_asteroid_api.dto.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class MissDistance implements Serializable {
    private String astronomical;
    private String lunar;
    private String kilometers;
    private String miles;
}
