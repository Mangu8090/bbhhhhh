package com.igate.vehicle.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.igate.vehicle.dto.Vehicle;
import com.igate.vehicle.exception.VehicleException;
import com.igate.vehicle.util.DBUtil;

/*
 * Database class - with all the database queries
 */
public class VehicleDAOImpl implements IVehicleDAO {

	Connection connection = null;
	Statement statement = null;
	ResultSet rsSet = null;
	PreparedStatement preparedStatement = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.igate.book.dao.IBookDAO#insertBook(com.igate.book.dto.Book) Code
	 * to insert book record in table, returning int- specifying number of
	 * records that are inserted successfully
	 */
	@Override
	public int insertVehicle(Vehicle veh) throws VehicleException {
		connection = DBUtil.obtainConnection();
		String sql = "INSERT INTO Vehicle VALUES(Vehicle_SEQ.nextval,?,?,?,?)";
		int recordInserted = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, veh.getVehicleName());
			preparedStatement.setDate(2,
					java.sql.Date.valueOf(veh.getPurchaseDate()));
			preparedStatement.setFloat(3, veh.getPrice());
			preparedStatement.setString(4, veh.getCity());

			recordInserted = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new VehicleException("Error while inserting values::"
					+ e.getMessage());
		}
		return recordInserted;
	}

	/*
	 * (non-Javadoc)
	 * @see com.igate.book.dao.IBookDAO#showBooks()
	 * Fetching book records from table. Storing in ArrayList and returning arraylist of type Book 
	 */
	@Override
	public ArrayList<Vehicle> showVehicles() throws VehicleException {
		ArrayList<Vehicle> vehList = new ArrayList<Vehicle>();
		connection = DBUtil.obtainConnection();
		String sql = "SELECT * FROM Vehicle";
		try {
			statement = connection.createStatement();
			rsSet = statement.executeQuery(sql);
			while (rsSet.next()) {
				Vehicle veh = new Vehicle();
				veh.setVehicleID(rsSet.getInt("VehicleID"));
				veh.setVehicleName(rsSet.getString("Vehicle_Name"));
				veh.setPurchaseDate(rsSet.getDate("purchase_date").toLocalDate());
	            veh.setPrice(rsSet.getFloat("price"));
	            veh.setCity(rsSet.getString("city"));

				vehList.add(veh);
			}
		} catch (SQLException e) {
			throw new VehicleException("Error while fetching values::"
					+ e.getMessage());
		}
		return vehList;

	}
	
	public int deleteVehicle(int VehicleID) throws VehicleException{
		connection = DBUtil.obtainConnection();
		String cmd="delete from Vehicle where VehicleID ="+ VehicleID;
			try 
			{
				
				statement =connection.createStatement();
				int n= statement.executeUpdate(cmd);
					if(n==0)
					{
						//logger.error("exception while deleting details..\n");
						throw new VehicleException("id not found in vehicle details");
					}
			}
			catch (VehicleException e) 
			{
				//logger.error("exception while deleting details..\n");
				throw new VehicleException("id not found in vehicle details");
			} 
			catch (SQLException e) 
			{
				throw new VehicleException("Database error");
			}
			return VehicleID;
		}
	}

