package ie.gmit.sw;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

@WebServlet("/Bookings")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BookingServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("booking broke");
		// Create a client
		Client client = Client.create();
		//System.out.println("booking broke2");

		// Request a connection to the Jax rs service
		WebResource wr = client.resource("http://localhost:8080/WebService/webapi/bookinglist/get");
		//System.out.println("booking broke3");

		// Get a response from the service
		String r = wr.accept(MediaType.APPLICATION_JSON).get(String.class);
		//System.out.println("booking broke4");

		Gson gson = new Gson();

		Type listType = new TypeToken<ArrayList<Booking>>() {
		}.getType();

		List<Booking> bookings = gson.fromJson(r, listType);

		request.setAttribute("bookings", bookings);
		request.getRequestDispatcher("/WEB-INF/Bookings.jsp").forward(request, response);

	}// doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("delButton") != null) {
			del(request, response);

		} else if (request.getParameter("updatelButton") != null) {
			update(request, response);

		} else if (request.getParameter("new booking") != null) {
			add(request, response);

		} // if
			doGet(request, response);
	}// doPost

	@SuppressWarnings("unused")
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		Client client = Client.create();
		Customer customer = new Customer(request.getParameter("name"), request.getParameter("address"));
		Vehicle vehicle = new Vehicle(request.getParameter("reg"), request.getParameter("make"), request.getParameter("model"), true);
		Booking booking = new Booking(vehicle,customer);
		Gson gson = new Gson();
		String json = gson.toJson(booking);
		client.resource("http://localhost:8080/WebService/webapi/bookinglist/add").type(MediaType.APPLICATION_JSON).post();
	}

	public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Create a client
		Client client = Client.create();

		// Request a connection to the Jax rs service
		client.resource("http://localhost:8080/WebService/webapi/bookinglist/delete").delete();
		

	}// del

	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client client = Client.create();
		
		client.resource("http://localhost:8080/WebService/webapi/bookinglist/update").put();
		}// update
	
	

}
