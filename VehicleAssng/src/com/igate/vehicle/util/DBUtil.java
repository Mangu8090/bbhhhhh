package com.igate.vehicle.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.igate.vehicle.exception.VehicleException;

/*
 * DBUtil class to access a connection pool 
 */
public class DBUtil {
	static Connection connection;

	public static Connection obtainConnection() throws VehicleException {
		try {
			InitialContext context = new InitialContext();
			DataSource source = (DataSource) context
					.lookup("java:/oracleds");
			connection = source.getConnection();
		} catch (NamingException e) {
			throw new VehicleException("Error while creating datascource::"
					+ e.getMessage());
		} catch (SQLException e) {
			throw new VehicleException("Error while obtaining connection::"
					+ e.getMessage());
		}
		return connection;
	}
}
