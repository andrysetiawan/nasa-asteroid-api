package com.andry.nasa_asteroid_api.client;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.andry.nasa_asteroid_api.config.NasaProperties;
import com.andry.nasa_asteroid_api.dto.nasa.NeoFeedApiResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NasaNeoFeedClient {
    private final NasaProperties properties;
    private final NasaApiClient apiClient;
    private static final String NEO_FEED_ENDPOINT = "/neo/rest/v1/feed";

    public NeoFeedApiResponse getNeoFeed(LocalDate startDate, LocalDate endDate) {
        String url = properties.getBaseUrl()
                + NEO_FEED_ENDPOINT
                + "?start_date={startDate}"
                + "&end_date={endDate}"
                + "&api_key={apiKey}";

        return apiClient.get(url, NeoFeedApiResponse.class, startDate, endDate, properties.getApiKey());
    }
    


}
