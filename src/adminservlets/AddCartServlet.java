package adminservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import utils.CartUtils;

/**
 * Servlet implementation class DeleteCategoryServlet
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

@WebServlet("/AddCartServlet")
public class AddCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /* Get Method */
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
					out.println("window.location.href='Assignment/website/index.jsp';");
					out.println("alert('You have to login as member to access.');");
					out.println("</script>");
				} else {
					System.out.println("(adminservlets/AddCartServlet) There's no action to be taken for GET. Redirecting to index.jsp.\n"); 
					response.sendRedirect("Assignment/website/index.jsp");
				}
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='Assignment/website/index.jsp';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(adminservlets/AddCartServlet) Member Validation Error: " + e + "\n");
		}
	}

	/* Post Method */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get current session
		HttpSession session=request.getSession();
		
		// get writer
		PrintWriter out = response.getWriter();  

		try{ 
			// validate if user is logged in with an account type
			if(session.getAttribute("accountType")!=null){
				try {
					if(request.getParameter("productId")!=null){
						int productId = Integer.parseInt(request.getParameter("productId"));
						
						// validate if user executing request is member
						if(!session.getAttribute("accountType").equals("member")){
							out.println("<script type='text/javascript'>");
							out.println(String.format("window.location.href='../ST0510-JAD-Assignment/productdetails?productId=%d';", productId));
							out.println("alert('You have to login as member to add to cart.');");
							out.println("</script>");
						} else{ 
							
							// get user id
							int userId = (int)session.getAttribute("userId");
									
							// declare client
							Client client = ClientBuilder.newClient();
				    				
				    		// target java and parse in data - get categories for navigation and page
							WebTarget target = client.target("http://localhost:8080/ST0510-JAD-Assignment/api/")
				    				.path("cartservices/getcartsbyuserid").queryParam("userId", (int)session.getAttribute("userId"));
				    				
				    		// declare media is an application/json
							Invocation.Builder invoBuilder = target.request(MediaType.APPLICATION_JSON);
				    				
				    		// get response
							Response resp = invoBuilder.get();
				    			
				    		// if response status is ok
				    		if(resp.getStatus() == Response.Status.OK.getStatusCode()) {
				    			
				    			// get results from java class
				    			JSONArray cartJSONArray = new JSONArray(resp.readEntity(new GenericType<String>() {}));
				    			
				    			boolean existInCart = false;
				    			
				    			// exist in cart validation
				    			for(int x=0; x<cartJSONArray.length(); x++) {
				    				JSONObject cartObject = (JSONObject) cartJSONArray.get(x);
				    						
				    				int productIdCompare = cartObject.getInt("productId");
				    						
				    				if(productId == productIdCompare) {
				    					existInCart = true;
				    				}
				    			}
				    			
				    			if(existInCart == false) {
				    				int quantity = 1;
									
				    				// get current date time
				    				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    						String dateTime = dateFormat.format(new Date());
		    						
					    			// insert cart
					    			int insertCartCount = CartUtils.insertCart(userId, productId, quantity, dateTime); 
										
					    			if(insertCartCount > 0) {
					    				out.println("<script type='text/javascript'>");
					    				out.println(String.format("window.location.href='../ST0510-JAD-Assignment/productdetails?productId=%d';", productId));
					    				out.println("alert('Item added to cart.');");
					    				out.println("</script>");
					    			} else {
					    				out.println("<script type='text/javascript'>");
					    				out.println(String.format("window.location.href='../ST0510-JAD-Assignment/productdetails?productId=%d';", productId));
					    				out.println("alert('Failed to insert cart.');");
					    				out.println("</script>");
					    			}   
				    			} else {
				    				// edit cart increase
									int editCartIncreaseCount = CartUtils.editCartIncrease(userId, productId); 
													
									if(editCartIncreaseCount > 0){
										out.println("<script type='text/javascript'>");
										out.println(String.format("window.location.href='../ST0510-JAD-Assignment/productdetails?productId=%d';", productId));
					    				out.println("alert('Added one item in cart.');");
					    				out.println("</script>");
									}else {
										out.println("<script type='text/javascript'>");
										out.println(String.format("window.location.href='../ST0510-JAD-Assignment/productdetails?productId=%d';", productId));
					    				out.println("alert('Failed to increase product quantity.');");
					    				out.println("</script>");
									}
				    			}
				    		}else {
				    			System.out.println("(adminservlets/AddCartServlet) Error: Response not ok. \n");
				    			response.sendRedirect(request.getContextPath() + "/index");
				    		}
						}
					} else {
						out.println("<script type='text/javascript'>");
						out.println("window.location.href='../ST0510-JAD-Assignment/index';");
						out.println("alert('Failed to insert cart.');");
						out.println("</script>");
					}
			} catch (Exception e) {         
				System.out.println("(adminservlets/AddCartServlet) Error: " + e + "\n");
				response.sendRedirect(request.getContextPath() + "/index");
			} 
		}else{ 
			out.println("<script type='text/javascript'>");
			out.println("window.location.href='../ST0510-JAD-Assignment/login';");
			out.println("alert('You have to login as member to add item to cart.');");
			out.println("</script>");
		}
	} catch (Exception e){
		System.out.println("(adminservlets/AddCartServlet) Member Validation Error: " + e + "\n");
	}
	}

}
