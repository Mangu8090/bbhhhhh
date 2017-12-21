package com.igate.vehicle.dao;

import java.util.ArrayList;

import com.igate.vehicle.dto.Vehicle;
import com.igate.vehicle.exception.VehicleException;

public interface IVehicleDAO {
	public int insertVehicle(Vehicle veh) throws VehicleException;
	public ArrayList<Vehicle> showVehicles() throws VehicleException;
	public int deleteVehicle(int VehicleID) throws VehicleException;
}
