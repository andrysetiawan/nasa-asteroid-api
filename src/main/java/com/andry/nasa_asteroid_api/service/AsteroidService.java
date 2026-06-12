package com.andry.nasa_asteroid_api.service;

import java.time.LocalDate;
import java.util.List;

import com.andry.nasa_asteroid_api.dto.response.AsteroidDetailResponse;
import com.andry.nasa_asteroid_api.dto.response.AsteroidResponse;

public interface AsteroidService {
    List<AsteroidResponse> getAsteroids(LocalDate startDate, LocalDate endDate, int limit);

    AsteroidDetailResponse getAsteroidDetail(String id);
}
