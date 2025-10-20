package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {
	
	private static final String WEATHER_URL =
            "https://api.open-meteo.com/v1/forecast?latitude=%f&longitude=%f&current_weather=true";

    public String getWeather(double lat, double lon) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = String.format(WEATHER_URL, lat, lon);
            String response = restTemplate.getForObject(url, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode json = mapper.readTree(response);
            JsonNode current = json.get("current_weather");

            if (current != null) {
                double temp = current.get("temperature").asDouble();
                double wind = current.get("windspeed").asDouble();
                return String.format("%.1f°C, wind %.1f km/h", temp, wind);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Weather data unavailable";
    }
	
}
