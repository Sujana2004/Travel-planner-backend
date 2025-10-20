package com.example.demo.dto;

public class TripResponse {
	
	private String recommendation;
    private String distance;
    private double sourceLat;
    private double sourceLon;
    private double destLat;
    private double destLon;
    private String sourceWeather;
    private String destinationWeather;
	public String getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(String recommendation) {
		this.recommendation = recommendation;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public double getSourceLat() {
		return sourceLat;
	}
	public void setSourceLat(double sourceLat) {
		this.sourceLat = sourceLat;
	}
	public double getSourceLon() {
		return sourceLon;
	}
	public void setSourceLon(double sourceLon) {
		this.sourceLon = sourceLon;
	}
	public double getDestLat() {
		return destLat;
	}
	public void setDestLat(double destLat) {
		this.destLat = destLat;
	}
	public double getDestLon() {
		return destLon;
	}
	public void setDestLon(double destLon) {
		this.destLon = destLon;
	}
	public String getSourceWeather() {
		return sourceWeather;
	}
	public void setSourceWeather(String sourceWeather) {
		this.sourceWeather = sourceWeather;
	}
	public String getDestinationWeather() {
		return destinationWeather;
	}
	public void setDestinationWeather(String destinationWeather) {
		this.destinationWeather = destinationWeather;
	}
    
    
    
	
}
