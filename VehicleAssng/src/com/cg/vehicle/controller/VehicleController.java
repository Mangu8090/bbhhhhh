package com.cg.vehicle.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.igate.vehicle.dto.Vehicle;
import com.igate.vehicle.exception.VehicleException;
import com.igate.vehicle.service.VehicleServiceImpl;
import com.igate.vehicle.service.IVehicleService;

/**
 * Servlet implementation class BookController
 * Acting as front controller. Where all requests are dispatched from this controller
 */
@WebServlet("/VehicleController")
public class VehicleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String operation = request.getParameter("action");
		PrintWriter pw = response.getWriter();
		RequestDispatcher view = null;
		IVehicleService service = new VehicleServiceImpl();
		if (operation != null && "add".equals(operation)) {
			view = request.getRequestDispatcher("addVehicle.html");
			view.forward(request, response);
		}
		
		if(operation !=null && "delete".equals(operation)){
			
			//int ID=Integer.parseInt(request.getParameter("VehicleID"));
			view = request.getRequestDispatcher("deleteVehicle.html");
			view.forward(request, response);
		}
		if(operation !=null && "Delete Vehicle".equals(operation)){
			int ID=Integer.parseInt(request.getParameter("VehicleID"));
			try {
				int records = service.deleteVehicle(ID);
				if (records != 0) {
					view = request.getRequestDispatcher("success1.html");
					view.forward(request, response);
				} else {
					pw.println("Deleting Vehicle details failed");
					view = request.getRequestDispatcher("error.html");
					view.include(request, response);
				}
			} catch (VehicleException e) {
				pw.println("Error while deleting Vehicle details");
				view = request.getRequestDispatcher("error.html");
				view.include(request, response);
			}
		}
			
		
		
		if (operation != null && "Append Vehicle".equals(operation)) {
			String VehicleName = request.getParameter("VehicleName");
			LocalDate purchaseDate = LocalDate.parse(request
					.getParameter("purchaseDate"));
			float price = Float.parseFloat(request
					.getParameter("price"));
			String city = request.getParameter("city");
			Vehicle veh = new Vehicle();
			veh.setVehicleName(VehicleName);
			veh.setPrice(price);
			veh.setPurchaseDate(purchaseDate);
			veh.setCity(city);
			try {
				int records = service.insertVehicle(veh);
				if (records != 0) {
					view = request.getRequestDispatcher("success.html");
					view.forward(request, response);
				} else {
					pw.println("Inserting Vehicle details failed");
					view = request.getRequestDispatcher("error.html");
					view.include(request, response);
				}
			} catch (VehicleException e) {
				pw.println("Error while inserting Vehicle details");
				view = request.getRequestDispatcher("error.html");
				view.include(request, response);
			}
		} if(operation != null && "view".equals(operation)) {
			try {
				ArrayList<Vehicle> vehList = service.showVehicles();
				if (vehList != null) {
					Iterator<Vehicle> vehIterator = vehList.iterator();
					pw.println("<body>");
					pw.println("<table border='1'>");
					pw.println("<tr>");
					pw.println("<th>Vehicle ID</th>");
					pw.println("<th>Vehicle Name</th>");
					pw.println("<th>Vehicle Purchase Date</th>");
					pw.println("<th>Price</th>");
					pw.println("<th>City</th>");
					pw.println("</tr>");
					while(vehIterator.hasNext()) {
						Vehicle veh = vehIterator.next();
						pw.println("<tr>");
						pw.println("<td>"+veh.getVehicleID()+"</td>");
						pw.println("<td>"+veh.getVehicleName()+"</td>");
						pw.println("<td>"+veh.getPurchaseDate()+"</td>");
						pw.println("<td>"+veh.getPrice()+"</td>");
						pw.println("<td>"+veh.getCity()+"</td>");
						pw.println("</tr>");
					}
					pw.println("</table>");
					pw.println("<a href='index.html'>Go Back to Home</a>");
					pw.println("</body>");
				} else {
					pw.println("Fetching Book details failed");
					view = request.getRequestDispatcher("error.html");
					view.include(request, response);
				}
			} catch (VehicleException e) {
				pw.println("Error while fetching book details");
				view = request.getRequestDispatcher("error.html");
				view.include(request, response);
			}
		}
	}

}
