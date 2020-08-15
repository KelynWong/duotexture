package adminservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import javabeans.Card;
import javabeans.Cart;
import javabeans.Category;

import java.util.ArrayList;
import java.util.Date;
import java.util.regex.*;

import utils.CardUtils;
import utils.CartUtils;
import utils.OrderUtils;
import utils.PaymentUtils;

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
							String cardNumber = request.getParameter("cardNumber");
							int expiryMonth = Integer.parseInt(request.getParameter("expMonth"));
							int expiryYear = Integer.parseInt(request.getParameter("expYear"));
							int cvv = Integer.parseInt(request.getParameter("cvv"));
							
							// get current date
							DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    						String dateTime = dateFormat.format(new Date());
							
    						Boolean luhnAlgorithmValidation = false;
    						
    						luhnAlgorithmValidation = PaymentUtils.validateCreditCardNumber(cardNumber);
    						
							if(luhnAlgorithmValidation) {
								
								Date date = new Date();  
								LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						        int currentYear = localDate.getYear();  
						        int currentMonth = localDate.getMonthValue();
						        
						        // check year
								if(expiryYear >= currentYear) {
									// check month
									if(expiryMonth < 12) {
										// check year and month
										if(expiryYear == currentYear) {
											if(expiryMonth >= currentMonth) {
												int userId = (int)session.getAttribute("userId");
												
												// update payment by deleting before adding
												CardUtils.deleteCard(userId);
													
												// add payment
												int count = CardUtils.insertCard(userId, cardOwner, cardNumber, expiryMonth, expiryYear, cvv);
												
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
													System.out.println("(adminservlets/AddPaymentServlet) Error: Failed to insert card\n");
													response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
												}
											} else{
												System.out.println("(adminservlets/AddPaymentServlet) Error: Card Expired (Month in Year) \n");
												response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
											}
										} else {
											int userId = (int)session.getAttribute("userId");
											
											// update payment by deleting before adding
											CardUtils.deleteCard(userId);
											
											// add payment
											int addCardReturnCount = CardUtils.insertCard(userId, cardOwner, cardNumber, expiryMonth, expiryYear, cvv);
												
											if(addCardReturnCount > 0){
												ArrayList<Cart> cartArrayList = new ArrayList<Cart>();
												cartArrayList = CartUtils.getCartsByUserId(userId);
												
												if(cartArrayList.size() != 0) {
													int addOrderReturnCount = 0;
													
													// transfer to order
													for (int x=0; x<cartArrayList.size(); x++) {
														
							    						// add order
														addOrderReturnCount = OrderUtils.insertOrder(cartArrayList.get(x).getUserId(), cartArrayList.get(x).getProductId(), cartArrayList.get(x).getQuantity(), dateTime);
													}
															
													if(addOrderReturnCount > 0) {
														// delete cart
														int deleteCartReturnCount = CartUtils.deleteAllCart(userId); 
														
														if(deleteCartReturnCount > 0) {
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
												System.out.println("(adminservlets/AddPaymentServlet) Error: Failed to insert card\n");
												response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
											}
										} 
										
										// card expiry validation below
										
							        } else {
										System.out.println("(adminservlets/AddPaymentServlet) Error: Card expired (Month) \n");
										response.sendRedirect(request.getContextPath() + "/payment?payment=expired");
									}
								}else {
									System.out.println("(adminservlets/AddPaymentServlet) Error: Card expired (Year) \n");
									response.sendRedirect(request.getContextPath() + "/payment?payment=expired");
								}
								
								// card number validation below
								
							}else {
								System.out.println("(adminservlets/AddPaymentServlet) Error: Failed formatting validation\n");
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
