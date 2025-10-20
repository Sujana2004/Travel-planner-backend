package com.example.demo.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RouteService {
	
	@Value("${openrouteservice.api.key}")
    private String apiKey;

    private static final String ROUTE_URL = "https://api.openrouteservice.org/v2/directions/driving-car";

    public List<double[]> getRoute(double startLat, double startLon, double endLat, double endLon) {
        List<double[]> routePoints = new ArrayList<>();
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", apiKey);
            headers.set("Content-Type", "application/json");

            String body = String.format("{\"coordinates\":[[%f,%f],[%f,%f]]}", startLon, startLat, endLon, endLat);
            HttpEntity<String> entity = new HttpEntity<>(body, headers);

            String response = restTemplate.postForObject(ROUTE_URL, entity, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(response);
            JsonNode coords = json.at("/features/0/geometry/coordinates");

            for (JsonNode c : coords) {
                double lon = c.get(0).asDouble();
                double lat = c.get(1).asDouble();
                routePoints.add(new double[]{lat, lon});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return routePoints;
    }

	
}
