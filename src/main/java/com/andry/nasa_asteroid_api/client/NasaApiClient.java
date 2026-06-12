package com.andry.nasa_asteroid_api.client;

import java.nio.charset.StandardCharsets;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.andry.nasa_asteroid_api.common.exception.ExternalApiException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class NasaApiClient {
    private final RestClient restClient;

    public <T> T get(String url, Class<T> responseType, Object... uriVariables) {
        log.info("Making GET request to URL: {}", url);
        try {
            return restClient.get().uri(url, uriVariables)
                    .retrieve()
                    .onStatus(
                            HttpStatusCode::isError,
                            (request, response) -> {
                                String body = new String(
                                        response.getBody()
                                                .readAllBytes(),
                                        StandardCharsets.UTF_8);

                                log.error(
                                        "External API Error status={} body={}",
                                        response.getStatusCode(),
                                        body);

                                throw new ExternalApiException(
                                        "Unable to retrieve data");

                            })
                    .body(responseType);
        } catch (ExternalApiException e) {
            throw e;
        } catch (Exception e) {
            log.error("Error while making GET request to URL: {}", url, e);
            throw new ExternalApiException("Unable to retrieve data", e);
        }
    }

}
