package pageservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import javabeans.Product;
import utils.CategoryUtils;

/**
 * Servlet implementation class CategoriesServlet
 * 
 * Class: DIT/FT/2B/21
 * Group: 1
 * 
 * Name: LEE ZONG XUN RENFRED
 * Admin Number: P1935392 
 * 
 * Name: WONG EN TING KELYN
 * Admin Number: P1935800
 * 
 */

@WebServlet("/Categories")
public class CategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// declare client
			Client client = ClientBuilder.newClient();
			
			// target java and parse in data
			WebTarget target = client.target("http://localhost:8080/ST0510-JAD-Assignment/api/")
					.path("categoryservices/getcategories");
			
			// declare media is an application/json
			Invocation.Builder invoBuilder = target.request(MediaType.APPLICATION_JSON);
			
			// get response
			Response resp = invoBuilder.get();
			
			// if response status is ok
			if(resp.getStatus() == Response.Status.OK.getStatusCode()) {
				// get results from java class
				JSONArray productsJSONArray = new JSONArray(resp.readEntity(new GenericType<String>() {}));
				ArrayList<Product> productsArrayList = new ArrayList<Product>();
				
				for(int x=0; x<productsJSONArray.length(); x++) {
					JSONObject productObject = (JSONObject) productsJSONArray.get(x);
					
					int productId = productObject.getInt("productId");
					String name = productObject.getString("name");
					String description = productObject.getString("description");
					double cost_price = productObject.getDouble("cost_price");
					double retail_price = productObject.getDouble("retail_price");
					int quantity = productObject.getInt("quantity");
					int categoryId = productObject.getInt("categoryId");
					String image = productObject.getString("image");
					
					Product product = new Product(productId, name, description, cost_price, retail_price, quantity, categoryId, image);
					productsArrayList.add(product);
				}
				
				// store in request
				request.setAttribute("productsArrayList", productsArrayList);
				
				// forward request to jsp for display
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("Assignment/website/categories.jsp");
				requestDispatcher.forward(request, response);
			} else {
				System.out.println("(CategoriesServlet.jsp) Error: Response not ok. \n");
				response.sendRedirect("IndexServlet");
			}
			
		} catch (Exception e) {
			System.out.println("(CategoriesServlet.jsp) Error: " + e + "\n");
			response.sendRedirect("IndexServlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// do nothing
	}

}
