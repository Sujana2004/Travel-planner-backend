package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.TripRequest;
import com.example.demo.dto.TripResponse;
import com.example.demo.service.GeoCodingService;
import com.example.demo.service.RouteService;
import com.example.demo.service.WeatherService;

@RestController
@RequestMapping("/api")
public class TripController {
	
	private final GeoCodingService geoService;
    private final WeatherService weatherService;
    private final RouteService routeService;

    public TripController(GeoCodingService geoService, WeatherService weatherService, RouteService routeService) {
        this.geoService = geoService;
        this.weatherService = weatherService;
        this.routeService = routeService;
    }

    @PostMapping("/plan")
    public TripResponse planTrip(@RequestBody TripRequest request) {
        TripResponse response = new TripResponse();

        double[] srcCoords = geoService.getCoordinates(request.getSource());
        double[] dstCoords = geoService.getCoordinates(request.getDestination());

        response.setSourceLat(srcCoords[0]);
        response.setSourceLon(srcCoords[1]);
        response.setDestLat(dstCoords[0]);
        response.setDestLon(dstCoords[1]);

        String srcWeather = weatherService.getWeather(srcCoords[0], srcCoords[1]);
        String dstWeather = weatherService.getWeather(dstCoords[0], dstCoords[1]);
        response.setWeatherSummary("Source: " + srcWeather + " | Destination: " + dstWeather);

        response.setRecommendation("Recommended Mode: Train 🚆 | Budget fits within " + request.getBudget() + " ₹");

        response.setRoutePoints(routeService.getRoute(srcCoords[0], srcCoords[1], dstCoords[0], dstCoords[1]));

        return response;
    }

    @GetMapping("/health")
    public String health() {
        return "Trip Planner Backend is running fine!";
    }
		
}
