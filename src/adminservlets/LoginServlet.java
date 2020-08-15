package adminservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabeans.User;
import utils.UserUtils;

/**
 * Servlet implementation class LoginServlet
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

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	/* Get Method */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("(adminservlets/LoginServlet) There's no action to be taken for GET. Redirecting to index.jsp.\n"); 
		response.sendRedirect(request.getContextPath() + "/index");
	}

	/* Post Method */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get current session
		HttpSession session=request.getSession();

		// get writer
		PrintWriter out = response.getWriter();  
		
		try{ 
			// validate if user is logged in with an account type
			if(session.getAttribute("accountType")==null) {
				try{
					if(request.getParameter("emailInput")!=null){
						String emailInput = request.getParameter("emailInput");
						String passwordInput = request.getParameter("passwordInput");
						
						// initialize variables
						Boolean isAdmin = false;
						Boolean isMember = false;
						
						// get all users
						ArrayList<User> UsersArray = UserUtils.getUsers();
						
						// loop user against all users
						for (int x=0; x<UsersArray.size(); x++) {
							String userEmail = UsersArray.get(x).getEmail();
							String userPassword = UsersArray.get(x).getPassword();
							String userRole = UsersArray.get(x).getUserRole();
							
							// checks if inputs equals details in users table
							if(userEmail.equals(emailInput) && userPassword.equals(passwordInput)){
								int userId = UsersArray.get(x).getUserId();
								String username = UsersArray.get(x).getUsername();
								
								if(userRole.equals("Admin")){
									isAdmin = true;
									session.setAttribute("accountType", "admin");
								}else if(userRole.equals("Member")){
									isMember = true;
									session.setAttribute("accountType", "member");
								}
								
								session.setAttribute("userId", userId);
				        		session.setAttribute("username", username);
								response.sendRedirect(request.getContextPath() + "/index");
							}
						}
						
						// if account does not exist
						if(isAdmin == false && isMember == false){
							response.sendRedirect(request.getContextPath() + "/login?accountType=none");
						}
						
					}else{
						response.sendRedirect("login.jsp");
						System.out.println("(adminservlets/LoginServlet) Error: Wrong Flow \n");
					}
				}catch(Exception e){
					System.out.println("(adminservlets/LoginServlet) Error: " + e + " \n");
				}
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='../ST0510-JAD-Assignment/index';");
				out.println("alert('You are already logged in.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(adminservlets/LoginServlet) Validation Error: " + e + " \n");
		}
	}
}
