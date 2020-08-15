package adminservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import javabeans.Cart;
import javabeans.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.*;

import utils.CardUtils;
import utils.CartUtils;
import utils.OrderUtils;

/**
 * Servlet implementation class AddPaymentServlet
 */
@WebServlet("/AddPaymentServlet")
public class AddPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddPaymentServlet() {
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
					out.println("window.location.href='Assignment/website/index.jsp';");
					out.println("alert('You do not have access rights.');");
					out.println("</script>");
				} else {
					System.out.println("(adminservlets/AddPaymentServlet) There's no action to be taken for GET. Redirecting to checkout.jsp for payment.\n"); 
					response.sendRedirect("Assignment/website/index.jsp");
				}
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='Assignment/website/index.jsp';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(adminservlets/AddPaymentServlet) Member Validation Error: " + e + "\n");
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
					try {       
						if(request.getParameter("cardName")!=null){
							String cardOwner = request.getParameter("cardName");
							String checkCardNumber = request.getParameter("cardNumber");
							int expiryMonth = Integer.parseInt(request.getParameter("expMonth"));
							int expiryYear = Integer.parseInt(request.getParameter("expYear"));
							int cvv = Integer.parseInt(request.getParameter("cvv"));
							
							// get current date
							DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    						String dateTime = dateFormat.format(new Date());
									
							if(Pattern.matches("^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$", checkCardNumber) == true) {
								Date d=new Date();  
						        int year=d.getYear();  
						        int currentYear=year+1900;
								if(expiryYear >= currentYear) {
									int month=d.getMonth();  
							        int currentMonth=month+1;
							        
							        if(expiryMonth >= currentMonth) {
							        	int userId = (int)session.getAttribute("userId");

										// add payment
										int count = CardUtils.insertCard(userId, cardOwner, checkCardNumber, expiryMonth, expiryYear, cvv);
											
										if(count > 0){
											ArrayList<Cart> cartArrayList = new ArrayList<Cart>();
											cartArrayList = CartUtils.getCartsByUserId(userId);
													
											if(cartArrayList.size() != 0) {
												int count2 = 0;
												// transfer to order
												for (int x=0; x<cartArrayList.size(); x++) {
						    						// add order
													count2 = OrderUtils.insertOrder(cartArrayList.get(x).getUserId(), cartArrayList.get(x).getProductId(), cartArrayList.get(x).getQuantity(), dateTime);
												}
														
												if(count2 > 0) {
													// delete cart
													int count3 = CartUtils.deleteAllCart(userId); 
															
													if(count3 > 0) {
														response.sendRedirect(request.getContextPath() + "/payment?payment=success"); 
													}else {
														System.out.println("(adminservlets/AddPaymentServlet) Error: Failed to delete cart\n");
														response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
													}
												}else {
													System.out.println("(adminservlets/AddPaymentServlet) Error: Failed to insert to order\n");
													response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
												}
											}else {
												System.out.println("(adminservlets/AddPaymentServlet) Error: CartArrayList is empty\n");
												response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
											}
										} else{
											response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
										}
							        }else {
							        	System.out.println("(adminservlets/AddPaymentServlet) Error: Card expired\n");
										response.sendRedirect(request.getContextPath() + "/payment?payment=expired");
							        }
								}else {
									System.out.println("(adminservlets/AddPaymentServlet) Error: Card expired\n");
									response.sendRedirect(request.getContextPath() + "/payment?payment=expired");
								}
							}else {
								System.out.println("(adminservlets/AddPaymentServlet) Error: Wrong card information\n");
								response.sendRedirect(request.getContextPath() + "/payment?payment=cardFail");
							}
						} else{
							System.out.println("(adminservlets/AddPaymentServlet) Error: Wrong Flow\n");
							response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
						}
					} catch(java.sql.SQLIntegrityConstraintViolationException e){
						System.out.println("(adminservlets/AddPaymentServlet) Error: Duplicate Entry\n");
						response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
					} catch (Exception e) {         
						System.out.println("(adminservlets/AddPaymentServlet) Error: " + e + "\n"); 
						response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
					}
							
				}
			}else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='Assignment/website/index.jsp';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(adminservlets/AddPaymentServlet) Member Validation Error: " + e + "\n");
		}	
	}
}