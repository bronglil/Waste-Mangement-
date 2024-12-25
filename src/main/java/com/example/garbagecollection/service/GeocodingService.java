package com.example.garbagecollection.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
public class GeocodingService {

    private final String NOMINATIM_URL = "https://nominatim.openstreetmap.org/search";

    public List<Map> getLocationDataByName(String location) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl(NOMINATIM_URL)
                .queryParam("q", location)
                .queryParam("format", "json")
                .queryParam("addressdetails", 1)
                .toUriString();

        // Get the response as a list of maps
        List<Map> response = restTemplate.getForObject(url, List.class);
        return response;
    }
}