package com.andry.nasa_asteroid_api.client;

import org.springframework.stereotype.Component;

import com.andry.nasa_asteroid_api.config.NasaProperties;
import com.andry.nasa_asteroid_api.dto.nasa.NeoLookupApiResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NasaNeoLookupClient {
    private final NasaProperties properties;
    private final NasaApiClient apiClient;
    private static final String NEO_LOOKUP_ENDPOINT = "/neo/rest/v1/neo/";

    public NeoLookupApiResponse getNeoById(String neoId) {
            String url = properties.getBaseUrl()
                + NEO_LOOKUP_ENDPOINT
                + "{neoId}"
                + "?api_key={apiKey}";

        return apiClient.get(url, NeoLookupApiResponse.class, neoId, properties.getApiKey());
    }

}
