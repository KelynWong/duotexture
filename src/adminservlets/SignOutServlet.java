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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignOutServlet() {
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
		
		// validate if user is logged in with an account type
		if(session.getAttribute("accountType")!=null){			
			// mark session invalid and destroy
			session.invalidate();  
			response.sendRedirect("Assignment/website/index.jsp");
			System.out.println("(SignOutServlet) Logged out!\n");
		} else {
			out.println("<script type='text/javascript'>");
			out.println("window.location.href='Assignment/website/login.jsp';");
			out.println("alert('You are not logged in.');");
			out.println("</script>");
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
				System.out.println("(SignOutServlet) There's no action to be taken for POST. Redirecting to index.jsp.\n"); 
				response.sendRedirect("Assignment/website/index.jsp");
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='Assignment/website/login.jsp';");
				out.println("alert('You are not logged in.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(SignOutServlet) Validation Error: " + e + "\n");
		}
	}

}