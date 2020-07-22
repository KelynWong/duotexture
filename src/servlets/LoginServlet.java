package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;

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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//  response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		try{
			if(request.getParameter("emailInput")!=null){
				String emailInput = request.getParameter("emailInput");
				String passwordInput = request.getParameter("passwordInput");
				
				Boolean isAdmin = false;
				Boolean isMember = false;
				
				// get current session
        		HttpSession session=request.getSession();
				
				// connect to mysql database
				Class.forName("com.mysql.jdbc.Driver");
				String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
				Connection conn = DriverManager.getConnection(connURL);
				Statement stmt = conn.createStatement();
				
				// get all users
				String getAllAdminsQuery = "SELECT * FROM users;";
				ResultSet getAllUsersResult = stmt.executeQuery(getAllAdminsQuery);
				
				while (getAllUsersResult.next()){
					String userEmail = getAllUsersResult.getString("email");
					String userPassword = getAllUsersResult.getString("password");
					String userRole = getAllUsersResult.getString("userRole");
					
					// checks if inputs equals details in users table
					if(userEmail.equals(emailInput) && userPassword.equals(passwordInput)){
						int userId = getAllUsersResult.getInt("userId");
						String username = getAllUsersResult.getString("username");
						if(userRole.equals("Admin")){
							isAdmin = true;
							session.setAttribute("accountType", "admin");
						}else if(userRole.equals("Member")){
							isMember = true;
							session.setAttribute("accountType", "member");
						}
						
						session.setAttribute("userId", userId);
		        		session.setAttribute("username", username);
						response.sendRedirect("Assignment/website/index.jsp");
					}
				}
				
				if(isAdmin == false && isMember == false){
					response.sendRedirect("Assignment/website/login.jsp?accountType=none");
				}
				conn.close();
			}else{
				response.sendRedirect("login.jsp");
				System.out.println("(validate_login.jsp) Error: Wrong Flow\n");
			}
		}catch(Exception e){
			System.out.println("(validate_login.jsp) Error: " + e + "\n");
		}
	}

}
