package com.example.demo.dto;

public class TripRequest {
	
	private String source;
	private String destination;
	private int passengers;
	private double budget;
	private int comfort; //1-5
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getPassengers() {
		return passengers;
	}
	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double budget) {
		this.budget = budget;
	}
	public int getComfort() {
		return comfort;
	}
	public void setComfort(int comfort) {
		this.comfort = comfort;
	}
	public TripRequest(String source, String destination, int passengers, double budget, int comfort) {
		super();
		this.source = source;
		this.destination = destination;
		this.passengers = passengers;
		this.budget = budget;
		this.comfort = comfort;
	}
	public TripRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
