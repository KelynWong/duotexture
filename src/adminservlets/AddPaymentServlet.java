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

import javabeans.Cart;
import java.util.ArrayList;
import java.util.Date;
import utils.CardUtils;
import utils.CartUtils;
import utils.OrderUtils;
import utils.PaymentUtils;
import utils.ProductUtils;

/**
 * Servlet implementation class AddProductServlet
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

@WebServlet("/AddPaymentServlet")
public class AddPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/* Get Method */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get current session
		HttpSession session=request.getSession();

		// get writer
		PrintWriter out = response.getWriter();  
				
		try{ 
			// validate if user is logged in with an account type
			if(session.getAttribute("accountType")!=null) {
				// validate if user executing request is member
				if(!session.getAttribute("accountType").equals("member")) {
					out.println("<script type='text/javascript'>");
					out.println("window.location.href='Assignment/website/index.jsp';");
					out.println("alert('You do not have access rights.');");
					out.println("</script>");
				} else {
					System.out.println("(adminservlets/AddPaymentServlet) There's no action to be taken for GET. Redirecting to index.jsp.\n"); 
					response.sendRedirect("Assignment/website/index.jsp");
				}
			} else {
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='Assignment/website/index.jsp';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(adminservlets/AddPaymentServlet) Account Validation Error: " + e + "\n");
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
			if(session.getAttribute("accountType")!=null) {
				// validate if user executing request is member
				if(!session.getAttribute("accountType").equals("member")) {
					out.println("<script type='text/javascript'>");
					out.println("window.location.href='Assignment/website/index.jsp';");
					out.println("alert('You have to log in as member.');");
					out.println("</script>");
				} else {
					
					try {       
						if(request.getParameter("cardName")!=null) {
							
							// define variables
							String cardOwner = request.getParameter("cardName");
							String cardNumber = request.getParameter("cardNumber");
							int expiryMonth = Integer.parseInt(request.getParameter("expMonth"));
							int expiryYear = Integer.parseInt(request.getParameter("expYear"));
							int cvv = Integer.parseInt(request.getParameter("cvv"));
							Boolean luhnAlgorithmValidation = false;
							
							// get current date (for insertion)
							DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    						String dateTime = dateFormat.format(new Date());
    						
    						// carry out luhn algorithm validation
    						luhnAlgorithmValidation = PaymentUtils.validateCreditCardNumber(cardNumber);
    						
    						// if luhn algorithm is valid
							if(luhnAlgorithmValidation) {
								
								// get today's date (for validation)
								Date date = new Date();  
								LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
						        int currentYear = localDate.getYear();  
						        int currentMonth = localDate.getMonthValue();
						        
						        // check year
								if(expiryYear >= currentYear) {
									
									// check month
									if(expiryMonth <= 12) {
										
										// check year and month
										if(expiryYear == currentYear) {
											if(expiryMonth >= currentMonth) {
												int userId = (int)session.getAttribute("userId");
												
												// update card by always deleting before adding
												CardUtils.deleteCard(userId);
												
												// add card
												int addCardReturnCount = CardUtils.insertCard(userId, cardOwner, cardNumber, expiryMonth, expiryYear, cvv);
												
												// if card has been added
												if(addCardReturnCount > 0) {
													
													// get carts  by user id
													ArrayList<Cart> cartArrayList = CartUtils.getCartsByUserId(userId);
													
													// if there are Cart in cartArrayList
													if(cartArrayList.size() > 0) {
														
														// predefine variable
														int editProductQuantityReturnCount = 0;
														int addOrderReturnCount = 0;
														
														// transfer every cart to order database
														for (int x=0; x<cartArrayList.size(); x++) {
															int cartUserId = cartArrayList.get(x).getUserId();
															int cartProductId = cartArrayList.get(x).getProductId();
															int cartQuantity = cartArrayList.get(x).getQuantity();
															
															// update product quantity count
															editProductQuantityReturnCount = ProductUtils.editProductQuantity(cartProductId, cartQuantity);
															
															if (editProductQuantityReturnCount > 0) {
																// add order
																addOrderReturnCount = OrderUtils.insertOrder(cartUserId, cartProductId, cartQuantity, dateTime);
															}
														}
														
														// if order has been added
														if(addOrderReturnCount > 0) {
															
															// delete cart
															int deleteCartReturnCount = CartUtils.deleteAllCart(userId); 
															
															if(deleteCartReturnCount > 0) {
																// redirects
																response.sendRedirect(request.getContextPath() + "/payment?payment=success");
																
																// return count validation below
																
															} else {
																System.out.println("(adminservlets/AddPaymentServlet) Error: Failed to delete cart \n");
																response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
															}
														} else {
															System.out.println("(adminservlets/AddPaymentServlet) Error: Failed to insert to add to order \n");
															response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
														}
													} else {
														System.out.println("(adminservlets/AddPaymentServlet) Error: CartArrayList is empty \n");
														response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
													}
												} else {
													System.out.println("(adminservlets/AddPaymentServlet) Error: Failed to insert card \n");
													response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
												}
												
												// card expiry validation below
												
											} else {
												System.out.println("(adminservlets/AddPaymentServlet) Error: Card Expired (Month in Year) \n");
												response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
											}
											
										// if card expiry year does not match the current year
										} else {
											int userId = (int)session.getAttribute("userId");
											
											// update card by always deleting before adding
											CardUtils.deleteCard(userId);
											
											// add card
											int addCardReturnCount = CardUtils.insertCard(userId, cardOwner, cardNumber, expiryMonth, expiryYear, cvv);
											
											// if card has been added
											if(addCardReturnCount > 0) {
												
												// get carts  by user id
												ArrayList<Cart> cartArrayList = CartUtils.getCartsByUserId(userId);
												
												// if there are Cart in cartArrayList
												if(cartArrayList.size() > 0) {
													
													// predefine variable
													int editProductQuantityReturnCount = 0;
													int addOrderReturnCount = 0;
													
													// transfer every cart to order database
													for (int x=0; x<cartArrayList.size(); x++) {
														int cartUserId = cartArrayList.get(x).getUserId();
														int cartProductId = cartArrayList.get(x).getProductId();
														int cartQuantity = cartArrayList.get(x).getQuantity();
														
														// update product quantity count
														editProductQuantityReturnCount = ProductUtils.editProductQuantity(cartProductId, cartQuantity);
														
														if (editProductQuantityReturnCount > 0) {
															// add order
															addOrderReturnCount = OrderUtils.insertOrder(cartUserId, cartProductId, cartQuantity, dateTime);
														}
													}
													
													// if order has been added
													if(addOrderReturnCount > 0) {
														
														// delete cart
														int deleteCartReturnCount = CartUtils.deleteAllCart(userId); 
														
														if(deleteCartReturnCount > 0) {
															// redirects
															response.sendRedirect(request.getContextPath() + "/payment?payment=success");
															
															// return count validation below
															
														} else {
															System.out.println("(adminservlets/AddPaymentServlet) Error: Failed to delete cart \n");
															response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
														}
													} else {
														System.out.println("(adminservlets/AddPaymentServlet) Error: Failed to insert to add to order \n");
														response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
													}
												} else {
													System.out.println("(adminservlets/AddPaymentServlet) Error: CartArrayList is empty \n");
													response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
												}
											} else {
												System.out.println("(adminservlets/AddPaymentServlet) Error: Failed to insert card \n");
												response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
											}
										} 
										
										// card expiry validation below
										
							        } else {
										System.out.println("(adminservlets/AddPaymentServlet) Error: Card expired (Month) \n");
										response.sendRedirect(request.getContextPath() + "/payment?payment=expired");
									}
								} else {
									System.out.println("(adminservlets/AddPaymentServlet) Error: Card expired (Year) \n");
									response.sendRedirect(request.getContextPath() + "/payment?payment=expired");
								}
								
								// card number validation below
								
							} else {
								System.out.println("(adminservlets/AddPaymentServlet) Error: Failed formatting validation \n");
								response.sendRedirect(request.getContextPath() + "/payment?payment=cardFail");
							}
						} else {
							System.out.println("(adminservlets/AddPaymentServlet) Error: Wrong Flow \n");
							response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
						}
						
						// other validations below
						
					} catch(java.sql.SQLIntegrityConstraintViolationException e) {
						System.out.println("(adminservlets/AddPaymentServlet) Error: Duplicate entry \n");
						response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
					} catch (Exception e) {         
						System.out.println("(adminservlets/AddPaymentServlet) Error: " + e + " \n"); 
						response.sendRedirect(request.getContextPath() + "/payment?payment=fail");
					}
							
				}
			} else {
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
