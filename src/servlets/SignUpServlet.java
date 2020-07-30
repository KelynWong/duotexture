package servlets;

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
import utils.MemberUtils;

/**
 * Servlet implementation class SignUpServletS
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
		System.out.println("(SignUpServlet) There's no action to be taken for GET. Redirecting to index.jsp."); 
		response.sendRedirect("Assignment/website/index.jsp");		
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
			// if user is logged in with an account type
			if(session.getAttribute("accountType")==null){
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
						
						// get all users
						ArrayList<User> UsersArray = UserUtils.getUsers();

						for (int x=0; x<UsersArray.size(); x++) {
							String userEmail = UsersArray.get(x).getEmail();							
							
							// checks if email is equal to user's email
							if(userEmail.equals(inputEmail)){
								throw new java.sql.SQLIntegrityConstraintViolationException("Duplicate Entry");
							};
						}
						
						// add user
						int userId = UserUtils.insertUser(inputEmail, inputUsername, inputPassword, "Member");
						
						if (userId != 0) {
							session.setAttribute("userId", userId);
							session.setAttribute("username", inputUsername);
							session.setAttribute("accountType", "member");
							
							// add member
							int count = MemberUtils.insertMember(inputFirstName, inputLastName, inputCountry, inputAddress, inputPostalCode, userId);
							
							if(count > 0){
								response.sendRedirect("Assignment/website/index.jsp");
							}else{
								response.sendRedirect("Assignment/website/sign_up.jsp?registration=fail"); 
							}
						}
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
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='Assignment/website/index.jsp';");
				out.println("alert('You are already logged in.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(SignUpServlet) Admin Validation Error: " + e + "\n");
		}
	}

}
