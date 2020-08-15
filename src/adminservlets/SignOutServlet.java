package adminservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SignOutServlet
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

@WebServlet("/SignOutServlet")
public class SignOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /* Get Method */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get current session
		HttpSession session=request.getSession();
		
		// get writer
		PrintWriter out = response.getWriter();
		
		// validate if user is logged in with an account type
		if(session.getAttribute("accountType")!=null) {		
			
			// mark session invalid and destroy
			session.invalidate();

			response.sendRedirect(request.getContextPath() + "/index");
			System.out.println("(adminservlets/SignOutServlet) Logged out!\n");
		} else {
			out.println("<script type='text/javascript'>");
			out.println("window.location.href='../ST0510-JAD-Assignment/login';");
			out.println("alert('You are not logged in.');");
			out.println("</script>");
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
				System.out.println("(adminservlets/SignOutServlet) There's no action to be taken for POST. Redirecting to index.jsp.\n"); 
				response.sendRedirect(request.getContextPath() + "/index");
			} else {
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='../ST0510-JAD-Assignment/login';");
				out.println("alert('You are not logged in.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(adminservlets/SignOutServlet) Validation Error: " + e + "\n");
		}
	}

}
