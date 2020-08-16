package adminservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

@WebServlet("/EditCartDecreaseServlet")
public class EditCartDecreaseServlet extends HttpServlet {
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
					System.out.println("(adminservlets/EditCartDecreaseServlet) There's no action to be taken for GET. Redirecting to index.jsp.\n"); 
					response.sendRedirect("Assignment/website/index.jsp");
				}
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='Assignment/website/index.jsp';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(adminservlets/EditCartDecreaseServlet) Member Validation Error: " + e + "\n");
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
				
				// validate if user executing request is member
				if(!session.getAttribute("accountType").equals("member")){
					out.println("<script type='text/javascript'>");
					out.println("window.location.href='../ST0510-JAD-Assignment/index';");
					out.println("alert('You do not have access rights.');");
					out.println("</script>");
				} else {
					try {       
						if(request.getParameter("productId")!=null){
							
							// initialize variables
							int userId = (int)session.getAttribute("userId");
							int productId = Integer.parseInt(request.getParameter("productId"));
							String currency = request.getParameter("currency");

							// edit cart decrease
							int editCartDecreaseCount = CartUtils.editCartDecrease(userId, productId); 
									
							if(editCartDecreaseCount > 0){
								response.sendRedirect(request.getContextPath() + "/cart?cartEdit=success&currency="+currency); 
							}else{
								response.sendRedirect(request.getContextPath() + "/cart?cartEdit=fail&currency="+currency);
							}
									
						}else{
							System.out.println("(adminservlets/EditCartDecreaseServlet) Error: Wrong Flow\n");
							response.sendRedirect(request.getContextPath() + "/cart?cartEdit=success"); 
						}
					} catch (Exception e) {         
						System.out.println("(adminservlets/EditCartDecreaseServlet) Error :" + e + "\n");    
						response.sendRedirect(request.getContextPath() + "/cart?cartEdit=success"); 
					}
				}
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='../ST0510-JAD-Assignment/index';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(adminservlets/EditCartDecreaseServlet) Member Validation Error: " + e + "\n");
		}
	}

}