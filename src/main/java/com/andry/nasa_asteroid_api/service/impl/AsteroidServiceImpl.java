package com.andry.nasa_asteroid_api.service.impl;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.andry.nasa_asteroid_api.client.NasaNeoFeedClient;
import com.andry.nasa_asteroid_api.client.NasaNeoLookupClient;
import com.andry.nasa_asteroid_api.dto.nasa.CloseApproachData;
import com.andry.nasa_asteroid_api.dto.nasa.DiameterUnitData;
import com.andry.nasa_asteroid_api.dto.nasa.EstimatedDiameterData;
import com.andry.nasa_asteroid_api.dto.nasa.MissDistanceData;
import com.andry.nasa_asteroid_api.dto.nasa.NearEarthObjectData;
import com.andry.nasa_asteroid_api.dto.nasa.NeoFeedApiResponse;
import com.andry.nasa_asteroid_api.dto.nasa.NeoLookupApiResponse;
import com.andry.nasa_asteroid_api.dto.nasa.RelativeVelocityData;
import com.andry.nasa_asteroid_api.dto.response.AsteroidDetailResponse;
import com.andry.nasa_asteroid_api.dto.response.AsteroidResponse;
import com.andry.nasa_asteroid_api.dto.response.CloseApproach;
import com.andry.nasa_asteroid_api.dto.response.EstimateDIameter;
import com.andry.nasa_asteroid_api.dto.response.EstimateDiameterUnit;
import com.andry.nasa_asteroid_api.dto.response.MissDistance;
import com.andry.nasa_asteroid_api.dto.response.RelativeVelocity;
import com.andry.nasa_asteroid_api.service.AsteroidService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsteroidServiceImpl implements AsteroidService {
    private static final Integer DEFAULT_LIMIT = 10;
    private final NasaNeoFeedClient nasaNeoFeedClient;
    private final NasaNeoLookupClient nasaNeoLookupClient;

    @Override
    public List<AsteroidResponse> getAsteroids(LocalDate startDate, LocalDate endDate, int limit) {
        NeoFeedApiResponse response = nasaNeoFeedClient.getNeoFeed(startDate, endDate);

        int finalLimit = (limit > 0) ? limit : DEFAULT_LIMIT;

        return response.getNearEarthObjects().entrySet().stream()
                .flatMap(entry -> entry.getValue().stream().map(asteroid -> mapToResponse(entry.getKey(), asteroid)))
                .filter(asteroid -> asteroid != null && asteroid.getDistanceKm() != null)
                .sorted(Comparator.comparingDouble(AsteroidResponse::getDistanceKm))
                .limit(finalLimit)
                .toList();
    }

    @Override
    public AsteroidDetailResponse getAsteroidDetail(String id) {
        NeoLookupApiResponse response = nasaNeoLookupClient.getNeoById(id);

        return AsteroidDetailResponse.builder()
                .id(response.getId())
                .name(response.getName())
                .nasaJplUrl(response.getNasaJplUrl())
                .absoluteMagnitude(response.getAbsoluteMagnitudeH())
                .estimatedDiameter(mapEstimatedDiameter(response.getEstimatedDiameter()))
                .isHazardous(response.getIsPotentiallyHazardousAsteroid())
                .closeApproachData(mapCloseApproachData(response.getCloseApproachData()))
                .isSentryObject(response.getIsSentryObject())
                .build();
    }

    private EstimateDIameter mapEstimatedDiameter(EstimatedDiameterData source) {
        if (source == null) {
            return null;
        }

        EstimateDIameter diameter = new EstimateDIameter();
        diameter.setKilometers(mapDiameterUnit(source.getKilometers()));
        diameter.setMeters(mapDiameterUnit(source.getMeters()));
        diameter.setMiles(mapDiameterUnit(source.getMiles()));
        diameter.setFeet(mapDiameterUnit(source.getFeet()));
        return diameter;
    }

    private EstimateDiameterUnit mapDiameterUnit(DiameterUnitData source) {
        if (source == null) {
            return null;
        }

        EstimateDiameterUnit unit = new EstimateDiameterUnit();
        unit.setEstimatedDiameterMin(source.getEstimatedDiameterMin());
        unit.setEstimatedDiameterMax(source.getEstimatedDiameterMax());
        return unit;
    }

    private List<CloseApproach> mapCloseApproachData(List<CloseApproachData> source) {
        if (source == null) {
            return List.of();
        }

        return source.stream()
                .map(this::mapCloseApproach)
                .toList();
    }

    private CloseApproach mapCloseApproach(CloseApproachData source) {
        CloseApproach approach = new CloseApproach();
        approach.setCloseApproachDate(source.getCloseApproachDate());
        approach.setCloseApproachDateFull(source.getCloseApproachDateFull());
        approach.setEpochDateCloseApproach(source.getEpochDateCloseApproach());
        approach.setRelativeVelocity(mapRelativeVelocity(source.getRelativeVelocity()));
        approach.setMissDistance(mapMissDistance(source.getMissDistance()));
        approach.setOrbitingBody(source.getOrbitingBody());
        return approach;
    }

    private RelativeVelocity mapRelativeVelocity(RelativeVelocityData source) {
        if (source == null) {
            return null;
        }

        RelativeVelocity velocity = new RelativeVelocity();
        velocity.setKilometersPerSecond(source.getKilometersPerSecond());
        velocity.setKilometersPerHour(source.getKilometersPerHour());
        velocity.setMilesPerHour(source.getMilesPerHour());
        return velocity;
    }

    private MissDistance mapMissDistance(MissDistanceData source) {
        if (source == null) {
            return null;
        }

        MissDistance distance = new MissDistance();
        distance.setAstronomical(source.getAstronomical());
        distance.setLunar(source.getLunar());
        distance.setKilometers(source.getKilometers());
        distance.setMiles(source.getMiles());
        return distance;
    }

    private AsteroidResponse mapToResponse(LocalDate date, NearEarthObjectData asteroid) {

        List<CloseApproachData> approaches = asteroid.getCloseApproachData();
        if (approaches == null || approaches.isEmpty()) {
            return null;
        }

        CloseApproachData approach = approaches.getFirst();

        return AsteroidResponse.builder()
                .id(asteroid.getId())
                .name(asteroid.getName())
                .nasaJplUrl(asteroid.getNasaJplUrl())
                .absoluteMagnitude(asteroid.getAbsoluteMagnitudeH())
                .isHazardous(asteroid.getIsPotentiallyHazardousAsteroid())
                .date(date)
                .distanceKm(parseDistanceKm(approach.getMissDistance()))
                .orbitingBody(approach.getOrbitingBody())
                .build();
    }

    private Double parseDistanceKm(MissDistanceData missDistance) {
        if (missDistance == null || missDistance.getKilometers() == null) {
            return null;
        }

        try {
            return Double.parseDouble(missDistance.getKilometers());
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
