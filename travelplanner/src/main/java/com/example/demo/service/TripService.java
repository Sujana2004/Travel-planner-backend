package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TripService {
	
	 @Autowired
	    private WeatherService weatherService;

	    @Autowired
	    private RouteService routeService;

	    @Autowired
	    private GeoCodingService geoCodingService;

	    public Map<String, Object> getTripRecommendation(String source, String destination) {
	        Map<String, Object> response = new HashMap<>();

	        // Dynamic lat/lon from GeoCoding API
	        double[] sourceCoords = geoCodingService.getCoordinates(source);
	        double[] destCoords = geoCodingService.getCoordinates(destination);

	        if (sourceCoords == null || destCoords == null) {
	            response.put("recommendation", "Invalid source or destination");
	            response.put("reason", "Could not fetch coordinates for the given cities");
	            response.put("confidence", 0.0);
	            response.put("routeCoordinates", null);
	            return response;
	        }

	        // Distance approximation
	        double distance = Math.sqrt(Math.pow(destCoords[0] - sourceCoords[0], 2)
	                + Math.pow(destCoords[1] - sourceCoords[1], 2)) * 111;

	        // Dynamic weather
	        String sourceWeather = weatherService.getWeather(sourceCoords[0], sourceCoords[1]);
	        String destWeather = weatherService.getWeather(destCoords[0], destCoords[1]);

	        // Recommendation
	        String[] vehicles = {"Car", "Bus", "Train", "Flight"};
	        Random random = new Random();
	        String recommendedVehicle = vehicles[random.nextInt(vehicles.length)];
	        double confidence = 0.75 + random.nextDouble() * 0.15;

	        // Dynamic route
	        List<double[]> routePoints = routeService.getRoute(sourceCoords[0], sourceCoords[1], destCoords[0], destCoords[1]);

	        response.put("recommendation", recommendedVehicle);
	        response.put("confidence", confidence);
	        response.put("reason", String.format("Distance: %.0f Km. Weather: %s -> %s", distance, sourceWeather, destWeather));
	        response.put("routeCoordinates", routePoints);
	        response.put("sourceWeather",sourceWeather);
	        response.put(destWeather, destWeather);
	        response.put("Recommendation Vehicle", recommendedVehicle);

	        return response;
	    }
	
}
