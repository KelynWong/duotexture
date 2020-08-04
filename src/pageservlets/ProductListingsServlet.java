package pageservlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import javabeans.Category;
import javabeans.Product;

/**
 * Servlet implementation class ProductListingsServlet
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

@WebServlet("/productlistings")
public class ProductListingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductListingsServlet() {
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
			
			// target java and parse in data - get categories for navigation
			WebTarget target = client.target("http://localhost:8080/ST0510-JAD-Assignment/api/")
					.path("categoryservices/getcategories");
			
			// declare media is an application/json
			Invocation.Builder invoBuilder = target.request(MediaType.APPLICATION_JSON);
			
			// get response
			Response resp = invoBuilder.get();
			
			// if response status is ok
			if(resp.getStatus() == Response.Status.OK.getStatusCode()) {
				// get results from java class
				JSONArray categoriesJSONArray = new JSONArray(resp.readEntity(new GenericType<String>() {}));
				ArrayList<Category> categoriesArrayList = new ArrayList<Category>();
				
				for(int x=0; x<categoriesJSONArray.length(); x++) {
					JSONObject categoryObject = (JSONObject) categoriesJSONArray.get(x);
					
					int categoryId = categoryObject.getInt("categoryId");
					String name = categoryObject.getString("name");
					String description = categoryObject.getString("description");
					String image = categoryObject.getString("image");
					
					Category category = new Category(categoryId, name, description, image);
					categoriesArrayList.add(category);
				}
				
				// store in request
				request.setAttribute("categoriesArrayList", categoriesArrayList);
				
				// get category id	
				int categoryId = Integer.parseInt(request.getParameter("categoryId"));
				
				// declare client
				client = ClientBuilder.newClient();
				
				// target java and parse in data - get product by category id
				target = client.target("http://localhost:8080/ST0510-JAD-Assignment/api/")
						.path("productservices/getproductsbycategoryid").queryParam("categoryId", categoryId);
				
				// declare media is an application/json
				invoBuilder = target.request(MediaType.APPLICATION_JSON);
				
				// get response
				resp = invoBuilder.get();
				
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
						String image = productObject.getString("image");
						
						Product product = new Product(productId, name, description, cost_price, retail_price, categoryId, quantity,image);
						productsArrayList.add(product);
						
						// store in request
						request.setAttribute("productsArrayList", productsArrayList);
						
						// forward request to jsp for display
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("Assignment/website/product_listings.jsp");
						requestDispatcher.forward(request, response);
					}
				} else {
					System.out.println("(ProductListingsServlet) Error: Response not ok. \n");
					response.sendRedirect("Assignment/website/index.jsp");
				}
				
			} else {
				System.out.println("(ProductListingsServlet) Error: Response not ok. \n");
				response.sendRedirect("Assignment/website/index.jsp");
			}
			
		} catch (Exception e) {
			System.out.println("(ProductListingsServlet) Error: " + e + "\n");
			response.sendRedirect("Assignment/website/index.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// do nothing
	}

}
