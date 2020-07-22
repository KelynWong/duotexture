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
 * Servlet implementation class SignUpServlet
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

@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
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
			if(request.getParameter("inputEmail")!=null){
				String inputEmail = request.getParameter("inputEmail");
				String inputUsername = request.getParameter("inputUsername");
				String inputPassword = request.getParameter("inputPassword");
				String inputFirstName = request.getParameter("inputFirstName");
				String inputLastName = request.getParameter("inputLastName");
				String inputAddress = request.getParameter("inputAddress");
				String inputCountry = request.getParameter("inputCountry");
				String inputPostalCode = request.getParameter("inputPostalCode");
				
				// get current session
        		HttpSession session=request.getSession();

				// connect to mysql database
				Class.forName("com.mysql.jdbc.Driver");  
				String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
				Connection conn = DriverManager.getConnection(connURL);    
				Statement stmt = conn.createStatement(); 
				
				// checks if email is equal to user's email
				String getAllUsersEmailQuery = "SELECT email FROM duotexture.users;";
				ResultSet getAllUsersEmailResult = stmt.executeQuery(getAllUsersEmailQuery); 
				
				while(getAllUsersEmailResult.next()){
					String userEmail = getAllUsersEmailResult.getString("email");
					if(userEmail.equals(inputEmail)){
						throw new java.sql.SQLIntegrityConstraintViolationException("Duplicate Entry");
					};
				}		
				
				// insert inputs into users table
				String insertUserQuery = "INSERT INTO duotexture.users(`email`, `username`, `password`, `userRole`) VALUES(?, ?, ?, ?);";
				PreparedStatement pstmt = conn.prepareStatement(insertUserQuery);
			    pstmt.setString(1, inputEmail);
			    pstmt.setString(2, inputUsername);
			    pstmt.setString(3, inputPassword);
			    pstmt.setString(4, "Member");
				int count = pstmt.executeUpdate(); 
				
				if (count > 0){
					// get last row of users
					String getLastUserQuery = "SELECT * FROM users ORDER BY userId DESC LIMIT 1;";
					ResultSet getLastUserResult = stmt.executeQuery(getLastUserQuery);
					
					getLastUserResult.next();
					int userId = getLastUserResult.getInt("userId");
					String memberUsername = getLastUserResult.getString("username");
					
					session.setAttribute("userId", userId);
					session.setAttribute("username", memberUsername);
					session.setAttribute("accountType", "member");
					
					// insert inputs into members table
					String insertMemberQuery = "INSERT INTO members(`first_name`, `last_name`, `country`, `address`, `postal_code`, `userId`) VALUES(?, ?, ?, ?, ?, ?);";
					PreparedStatement pstmt2 = conn.prepareStatement(insertMemberQuery);
				    pstmt2.setString(1, inputFirstName);
				    pstmt2.setString(2, inputLastName);
				    pstmt2.setString(3, inputCountry);
				    pstmt2.setString(4, inputAddress);
				    pstmt2.setString(5, inputPostalCode);
				    pstmt2.setObject(6, userId);
					int count2 = pstmt2.executeUpdate(); 
					
					if(count2 > 0){
						response.sendRedirect("Assignment/website/index.jsp");
					}else{
						response.sendRedirect("Assignment/website/sign_up.jsp?registration=fail"); 
					}
				}else{
					response.sendRedirect("Assignment/website/sign_up.jsp?registration=fail"); 
				}                  
			           
				conn.close();
			}else{
				response.sendRedirect("Assignment/website/sign_up.jsp");
				System.out.println("(SignUpServlet) Error: Wrong Flow\n");
			}
		} catch(java.sql.SQLIntegrityConstraintViolationException e){
			System.out.println("(SignUpServlet) Error: Duplicate Entry\n");
			response.sendRedirect("Assignment/website/sign_up.jsp?registration=fail"); 
		} catch(Exception e){
			System.out.println("(SignUpServlet) Error: " + e + "\n");
			response.sendRedirect("Assignment/website/sign_up.jsp?registration=fail"); 
		}
	}

}
