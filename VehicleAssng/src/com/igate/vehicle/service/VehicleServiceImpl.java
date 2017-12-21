package com.igate.vehicle.service;

import java.util.ArrayList;

import com.igate.vehicle.dao.VehicleDAOImpl;
import com.igate.vehicle.dao.IVehicleDAO;
import com.igate.vehicle.dto.Vehicle;
import com.igate.vehicle.exception.VehicleException;
/*
 * Service layer - dispatching all calls to database layer
 */
public class VehicleServiceImpl implements IVehicleService {

    IVehicleDAO vehDAO;

	public VehicleServiceImpl() {
		vehDAO = new VehicleDAOImpl();
	}

	@Override
	public int insertVehicle(Vehicle veh) throws VehicleException {
		return vehDAO.insertVehicle(veh);
	}

	@Override
	public ArrayList<Vehicle> showVehicles() throws VehicleException {
		return vehDAO.showVehicles();
	}
	
	public int deleteVehicle(int VehicleID) throws VehicleException {
		int ID = vehDAO.deleteVehicle(VehicleID);
		return ID;
	}

}
