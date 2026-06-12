package com.andry.nasa_asteroid_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.andry.nasa_asteroid_api.dto.request.AsteroidQueryRequest;
import com.andry.nasa_asteroid_api.dto.response.AsteroidDetailResponse;
import com.andry.nasa_asteroid_api.dto.response.AsteroidResponse;
import com.andry.nasa_asteroid_api.service.AsteroidService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/asteroids")
@RequiredArgsConstructor
public class AsteroidController {
    private final AsteroidService asteroidService;

    @GetMapping
    public ResponseEntity<List<AsteroidResponse>> getAsteroids(@Valid @ModelAttribute AsteroidQueryRequest request) {
        List<AsteroidResponse> asteroids = asteroidService.getAsteroids(
                request.getStartDate(),
                request.getEndDate(),
                request.getLimit()
        );
        return ResponseEntity.ok(asteroids);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsteroidDetailResponse> getAsteroidById(@PathVariable String id) {
        AsteroidDetailResponse asteroid = asteroidService.getAsteroidDetail(id);
        return ResponseEntity.ok(asteroid);
    }
    
    

}
