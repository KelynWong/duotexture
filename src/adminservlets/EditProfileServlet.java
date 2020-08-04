package adminservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.UserUtils;
import utils.MemberUtils;

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
		// get current session
		HttpSession session=request.getSession();

		// get writer
		PrintWriter out = response.getWriter();  
		
		try{ 
			// validate if user is logged in with an account type
			if(session.getAttribute("accountType")!=null){
				System.out.println("(EditProfileServlet) There's no action to be taken for GET. Redirecting to edit_profile.jsp to edit profile.\n"); 
				response.sendRedirect(request.getContextPath() + "/editprofile");
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='${pageContext.request.contextPath}/login';");
				out.println("alert('You are not logged in.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(EditProfileServlet) Validation Error: " + e + "\n");
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
				try {          
					if(request.getParameter("inputUsername")!=null){
						String inputUsername = request.getParameter("inputUsername");
						String inputEmail = request.getParameter("inputEmail");
						String inputPassword = request.getParameter("inputPassword");
						
						// edit user
						int count = UserUtils.editUser((int)session.getAttribute("userId"), inputEmail, inputUsername, inputPassword);
						
						// if user is admin
						if(session.getAttribute("accountType").equals("admin")){
							if(count > 0){
								session.setAttribute("username", inputUsername);
								response.sendRedirect(request.getContextPath() + "/editprofile?profileEdit=success"); 
							}else{
								response.sendRedirect(request.getContextPath() + "/editprofile?profileEdit=fail");
							}
						}
						
						// if user is an member
						else if(session.getAttribute("accountType").equals("member")){
							String inputFirstName = request.getParameter("inputFirstName");
							String inputLastName = request.getParameter("inputLastName");
							String inputAddress = request.getParameter("inputAddress");
							String inputCountry = request.getParameter("inputCountry");
							String inputPostalCode = request.getParameter("inputPostalCode");
							
							// edit member
							int count2 = MemberUtils.editMember(inputFirstName, inputLastName, inputCountry, inputAddress, inputPostalCode, (int)session.getAttribute("userId"));
						    
						    if(count2 > 0){
						    	session.setAttribute("username", inputUsername);
								response.sendRedirect(request.getContextPath() + "/editprofile?userId="+(int)session.getAttribute("userId")+"&profileEdit=success"); 
							}else{
								response.sendRedirect(request.getContextPath() + "/editprofile?userId="+(int)session.getAttribute("userId")+"&profileEdit=fail");
							}
						}        	
						 			
					}else{
						System.out.println("(EditProfileServlet) Error: Wrong Flow\n");
						response.sendRedirect(request.getContextPath() + "/editprofile?userId="+(int)session.getAttribute("userId")+"&profileEdit=fail");
					}
				} catch(java.sql.SQLIntegrityConstraintViolationException e){
					System.out.println("(EditProfileServlet) Error: Duplicate Entry\n");
					response.sendRedirect(request.getContextPath() + "/editprofile?userId="+(int)session.getAttribute("userId")+"&profileEdit=fail");
				} catch (Exception e) {         
					System.out.println("(EditProfileServlet) Error: " + e + "\n");
					response.sendRedirect(request.getContextPath() + "/editprofile?userId="+(int)session.getAttribute("userId")+"&profileEdit=fail");
				}
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='${pageContext.request.contextPath}/index';");
				out.println("alert('You are not logged in.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(EditProfileServlet) Admin Validation Error: " + e + "\n");
		}		
	}

}
