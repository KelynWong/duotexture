package adminservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabeans.Cart;
import javabeans.Order;
import utils.CardUtils;
import utils.CartUtils;
import utils.OrderUtils;
import utils.PurchaseUtils;

/**
 * Servlet implementation class EditOrdersServlet
 */
@WebServlet("/EditOrdersServlet")
public class EditOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditOrdersServlet() {
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
					out.println("alert('You do not have access rights.');");
					out.println("</script>");
				} else {
					System.out.println("(adminservlets/EditOrdersServlet) There's no action to be taken for GET. Redirecting to categories.jsp to select a product of a category to edit.\n"); 
					response.sendRedirect(request.getContextPath() + "/index");
				}
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='../ST0510-JAD-Assignment/index';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(adminservlets/EditOrdersServlet) Admin Validation Error: " + e + "\n");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
					out.println("alert('You have to log in as member.');");
					out.println("</script>");
				} else {	
					int userId = (int)session.getAttribute("userId");
					ArrayList<Order> orderArrayList = new ArrayList<Order>();
					orderArrayList = OrderUtils.getOrdersByUserId(userId);
									
					if(orderArrayList.size() != 0) {
						int count = 0;
						// transfer to purchase
						for (int x=0; x<orderArrayList.size(); x++) {
							// get current date
							DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		    				String dateTime = dateFormat.format(new Date());
		    				
		    				// add purchase
							count = PurchaseUtils.insertPurchase(orderArrayList.get(x).getUserId(), orderArrayList.get(x).getProductId(), orderArrayList.get(x).getQuantity(), dateTime);
						}
										
						if(count > 0) {
							// delete order
							int count2 = OrderUtils.deleteOrder(userId); 
											
							if(count2 > 0) {
								out.println("<script type='text/javascript'>");
								out.println("window.location.href='../ST0510-JAD-Assignment/orders';");
								out.println("alert('Items have be confirmed that it is delivered!');");
								out.println("</script>");
							}else {
								System.out.println("(adminservlets/EditOrdersServlet) Error: Failed to delete order\n");
								response.sendRedirect(request.getContextPath() + "/purchases");
							}
						}else {
							System.out.println("(adminservlets/EditOrdersServlet) Error: Failed to insert to purchases\n");
							response.sendRedirect(request.getContextPath() + "/purchases");
						}
					}else {
						out.println("<script type='text/javascript'>");
						out.println("window.location.href='../ST0510-JAD-Assignment/orders';");
						out.println("alert('You have no orders!');");
						out.println("</script>");
					}	
				}
			}else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='Assignment/website/index.jsp';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(adminservlets/EditOrdersServlet) Member Validation Error: " + e + "\n");
		}	
	}
}
