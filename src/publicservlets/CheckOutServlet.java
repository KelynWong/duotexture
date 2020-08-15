package publicservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

import javabeans.Card;
import javabeans.Cart;
import javabeans.Category;

/**
 * Servlet implementation class CheckOutServlet
 */
@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckoutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get current session
    	HttpSession session=request.getSession();
    	
    	// get writer
    	PrintWriter out = response.getWriter();  
    	
    	try{ 
			// validate if user is logged in with an account type
			if(session.getAttribute("accountType")!=null){
				// validate if user executing request is member
				if(!session.getAttribute("accountType").equals("member")){
					out.println("<script type='text/javascript'>");
					out.println("window.location.href='../ST0510-JAD-Assignment/index';");
					out.println("alert('You have to login as member to checkout.');");
					out.println("</script>");
				}else {
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
	        				
	    				// declare client
	    				client = ClientBuilder.newClient();
	    				
	    				// target java and parse in data - get categories for navigation and page
	    				target = client.target("http://localhost:8080/ST0510-JAD-Assignment/api/")
	    						.path("cartservices/getcartsbyuserid").queryParam("userId", (int)session.getAttribute("userId"));
	    				
	    				// declare media is an application/json
	    				invoBuilder = target.request(MediaType.APPLICATION_JSON);
	    				
	    				// get response
	    				resp = invoBuilder.get();
	    				
	    				// if response status is ok
	    				if(resp.getStatus() == Response.Status.OK.getStatusCode()) {
	    					// get results from java class
	    					JSONArray cartJSONArray = new JSONArray(resp.readEntity(new GenericType<String>() {}));
	    					ArrayList<Cart> cartArrayList = new ArrayList<Cart>();
	    					
	    					if(cartJSONArray.length() == 0) {
	    						out.println("<script type='text/javascript'>");
	    						out.println("window.location.href='../ST0510-JAD-Assignment/cart';");
	    						out.println("alert('You have an empty cart.');");
	    						out.println("</script>");
	    					}else {
		    					for(int x=0; x<cartJSONArray.length(); x++) {
		    						JSONObject cartObject = (JSONObject) cartJSONArray.get(x);
		    						
		    						int userId = cartObject.getInt("userId");
		    						int productId = cartObject.getInt("productId");
		    						int quantity = cartObject.getInt("quantity");
		    						String dateTime = cartObject.getString("dateTime");
		    						
		    						String productName = cartObject.getString("productName");
		    						String productDescription = cartObject.getString("productDescription");
		    						double productCostPrice = cartObject.getDouble("productCostPrice");
		    						String productImage = cartObject.getString("productImage");
		    						
		    						Cart cart = new Cart(userId, productId, quantity, dateTime, productName, productDescription, productCostPrice, productImage);
		    						cartArrayList.add(cart);
		    					}
		    					
		    					// store in request
		    					request.setAttribute("cartArrayList", cartArrayList);
		    					
		    					// get user id	
		    					int userId = (int) session.getAttribute("userId");
		    					
		    					// declare client
		    					client = ClientBuilder.newClient();
		    					
		    					// target java and parse in data - get card by id
		    					target = client.target("http://localhost:8080/ST0510-JAD-Assignment/api/")
		    							.path("cardservices/getcardbyuserid").queryParam("userId", userId);
		    					
		    					// declare media is an application/json
		    					invoBuilder = target.request(MediaType.APPLICATION_JSON);
		    					
		    					// get response
		    					resp = invoBuilder.get();
		    					
		    					RequestDispatcher requestDispatcher = null;
		    					// if response status is ok
		    					if(resp.getStatus() == Response.Status.OK.getStatusCode()) {
		    						JSONObject cardObject = new JSONObject(resp.readEntity(new GenericType<String>() {}));
		    						
		    						if(cardObject.getInt("cvv") != 0) {
		    							String cardOwner = cardObject.getString("cardOwner");
		        						String cardNumber = cardObject.getString("cardNumber");
		        						int expiryMonth = cardObject.getInt("expiryMonth");
		        						int expiryYear = cardObject.getInt("expiryYear");
		        						int cvv = cardObject.getInt("cvv");
		        						
		        						Card card = new Card(userId, cardOwner, cardNumber, expiryMonth, expiryYear, cvv);
		        						
		        						// store in request
		        						request.setAttribute("card", card);
		        						
		        						// forward request to jsp for display
		            					requestDispatcher = request.getServletContext().getRequestDispatcher("/Assignment/website/checkout.jsp?cards=haveCard");
		            					requestDispatcher.forward(request, response);
		    						}else {
		    							requestDispatcher = request.getServletContext().getRequestDispatcher("/Assignment/website/checkout.jsp?cards=noCard");
		    							requestDispatcher.forward(request, response);
		    						}
		    					} else {
		    						System.out.println("(publicservlets/CheckOutServlet) Error: Response not ok. \n");
		    						response.sendRedirect(request.getContextPath() + "/index");
		    					}
		    				}
	    				} else {
	    					System.out.println("(publicservlets/CheckOutServlet) Error: Response not ok. \n");
	    					response.sendRedirect(request.getContextPath() + "/index");
	    				}
	        		} else {
	        			System.out.println("(publicservlets/CheckOutServlet) Error: Response not ok. \n");
	        			response.sendRedirect(request.getContextPath() + "/index");
	        		}
	    				
	    			} catch (Exception e) {
	    				System.out.println("(publicservlets/CheckOutServlet) Error: " + e + "\n");
	    				response.sendRedirect(request.getContextPath() + "/index");
	    			}
				}
			}else{ 
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='../ST0510-JAD-Assignment/login';");
				out.println("alert('You have to login as member to checkout.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(publicservlets/CheckOutServlet) Member Validation Error: " + e + "\n");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// do nothing
	}

}