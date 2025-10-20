package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeoCodingService {
	
	private static final String NOMINATIM_URL =
            "https://nominatim.openstreetmap.org/search?q=%s&format=json&limit=1";

    public double[] getCoordinates(String city) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = String.format(NOMINATIM_URL, city.replace(" ", "+"));
            String response = restTemplate.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode arr = mapper.readTree(response);

            if (arr.isArray() && arr.size() > 0) {
                JsonNode first = arr.get(0);
                double lat = first.get("lat").asDouble();
                double lon = first.get("lon").asDouble();
                return new double[]{lat, lon};
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new double[]{0.0, 0.0}; // fallback if API fails
    }
	
}
