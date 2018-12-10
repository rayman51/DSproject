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

@WebServlet("/Customers")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CustomerServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//System.out.println("customer broke");

		// Create a client
		Client client = Client.create();
		//System.out.println("customer broke2");
		// Request a connection to the Jax rs service
		WebResource wr = client.resource("http://localhost:8080/WebService/webapi/customerlist/get");
		//System.out.println("customer broke3");
		// Get a response from the service
		String r = wr.accept(MediaType.APPLICATION_JSON).get(String.class);
		//System.out.println("customer broke4");
		Gson gson = new Gson();

		Type listType = new TypeToken<ArrayList<Customer>>() {
		}.getType();

		List<Customer> customers = gson.fromJson(r, listType);

		request.setAttribute("customers", customers);
		request.getRequestDispatcher("/WEB-INF/Customers.jsp").forward(request, response);

	}// doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (request.getParameter("delButton") != null) {
			del(request, response);

		} else if (request.getParameter("updatelButton") != null) {
			update(request, response);

		} // if

	}// doPost

	public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Create a client
		Client client = Client.create();

		// Request a connection to the Jax rs service
		WebResource wr = client.resource("http://localhost:8080/WebService/webapi/customerlist/delete");

		// Get a response from the service
		String r = wr.accept(MediaType.APPLICATION_JSON).get(String.class);

		Gson gson = new Gson();

		Type listType = new TypeToken<ArrayList<Customer>>() {
		}.getType();

		List<Customer> customers = gson.fromJson(r, listType);

		request.setAttribute("customers", customers);
		request.getRequestDispatcher("/WEB-INF/Customers.jsp").forward(request, response);

	}// del

	public static void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/Customers.jsp").forward(request, response);
	}// update

}// CustomerServlet
