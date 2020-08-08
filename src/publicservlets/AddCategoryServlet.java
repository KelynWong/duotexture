package publicservlets;

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

/**
 * Servlet implementation class AddCategoryServlet
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

@WebServlet("/addcategory")
public class AddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategoryServlet() {
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
				
				// forward request to jsp for display
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("Assignment/website/add_category.jsp");
				requestDispatcher.forward(request, response);
			} else {
				System.out.println("(publicservlets/AddCategoryServlet) Error: Response not ok. \n");
				response.sendRedirect(request.getContextPath() + "/index");
			}
			
		} catch (Exception e) {
			System.out.println("(publicservlets/AddCategoryServlet) Error: " + e + "\n");
			response.sendRedirect(request.getContextPath() + "/index");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// do nothing
	}

}
