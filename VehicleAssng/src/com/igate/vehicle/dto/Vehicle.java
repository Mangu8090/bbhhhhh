package com.igate.vehicle.dto;

import java.time.LocalDate;

public class Vehicle {
	private int VehicleID;
	private String VehicleName;
	private LocalDate purchaseDate;
	private float price;
	private String city;
	
	
	public int getVehicleID() {
		return VehicleID;
	}
	public void setVehicleID(int vehicleID) {
		VehicleID = vehicleID;
	}
	public String getVehicleName() {
		return VehicleName;
	}
	public void setVehicleName(String vehicleName) {
		VehicleName = vehicleName;
	}
	public LocalDate getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(LocalDate purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	
}
