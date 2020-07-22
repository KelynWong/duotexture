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
 * Servlet implementation class EditProfileServlet
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

@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		try {          
			if(request.getParameter("inputUsername")!=null){
				String inputUsername = request.getParameter("inputUsername");
				String inputEmail = request.getParameter("inputEmail");
				String inputPassword = request.getParameter("inputPassword");
				
				// get current session
        		HttpSession session=request.getSession();
				
				// connect to mysql database
				Class.forName("com.mysql.jdbc.Driver");         
				String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";      
				Connection conn = DriverManager.getConnection(connURL);   
				
				// edit and update user with inputs by user id
				String updateUserQuery = "UPDATE duotexture.users SET email=?, username=?, password=? WHERE userId=?"; 
				PreparedStatement pstmt = conn.prepareStatement(updateUserQuery);
			    pstmt.setString(1, inputEmail);
			    pstmt.setString(2, inputUsername);
			    pstmt.setString(3, inputPassword);
			    pstmt.setObject(4, session.getAttribute("userId"));
				int count = pstmt.executeUpdate(); 
				
				// if user is admin
				if(session.getAttribute("accountType").equals("admin")){
					if(count > 0){
						session.setAttribute("username", inputUsername);
						response.sendRedirect("Assignment/website/edit_profile.jsp?profileEdit=success"); 
					}else{
						response.sendRedirect("Assignment/website/edit_profile.jsp?profileEdit=fail");
					}
				}
				
				// if user is an member
				else if(session.getAttribute("accountType").equals("member")){
					String inputFirstName = request.getParameter("inputFirstName");
					String inputLastName = request.getParameter("inputLastName");
					String inputAddress = request.getParameter("inputAddress");
					String inputCountry = request.getParameter("inputCountry");
					String inputPostalCode = request.getParameter("inputPostalCode");
					
					// edit and update members with inputs by user id
					String updateMembersQuery = "UPDATE members SET first_name=?, last_name=?, country=?, address=?, postal_code=? WHERE userId=?;"; 
					PreparedStatement pstmt2 = conn.prepareStatement(updateMembersQuery);
					pstmt2.setString(1, inputFirstName);
				    pstmt2.setString(2, inputLastName);
				    pstmt2.setString(3, inputCountry);
				    pstmt2.setString(4, inputAddress);
				    pstmt2.setString(5, inputPostalCode);
				    pstmt2.setObject(6, session.getAttribute("userId"));
				    int count2 = pstmt2.executeUpdate();
				    
				    if(count2 > 0){
				    	session.setAttribute("username", inputUsername);
						response.sendRedirect("Assignment/website/edit_profile.jsp?profileEdit=success"); 
					}else{
						response.sendRedirect("Assignment/website/edit_profile.jsp?profileEdit=fail");
					}
				}        	
				
				conn.close(); 			
			}else{
				System.out.println("(SignUpServlet) Error: Wrong Flow\n");
				response.sendRedirect("Assignment/website/edit_profile.jsp?profileEdit=fail");
			}
		} catch(java.sql.SQLIntegrityConstraintViolationException e){
			System.out.println("(SignUpServlet) Error: Duplicate Entry\n");
			response.sendRedirect("Assignment/website/edit_profile.jsp?profileEdit=fail");	
		} catch (Exception e) {         
			System.out.println("(SignUpServlet) Error: " + e + "\n");
			response.sendRedirect("Assignment/website/edit_profile.jsp?profileEdit=fail");	
		}
	}

}
