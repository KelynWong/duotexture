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
 * Servlet implementation class AddCategoryServlet
 * Servlet implementation class EditProductServlet
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

@WebServlet("/AddCategoryServlet")
public class AddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCategoryServlet() {
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

		// get current session
		HttpSession session=request.getSession();

//		try{ 
//			// validate if user executing request is admin
//			if(session.getAttribute("accountType")!=null){
//				if(!session.getAttribute("accountType").equals("admin")){
//					PrintWriter out = response.getWriter();  
//					out.println("<script type='text/javascript'>");
//					out.println("window.location.href='index.jsp';");
//					out.println("alert('You do not have access rights.');");
//					out.println("</script>");
//
//				}
//			}else{
//				PrintWriter out = response.getWriter();  
//				out.println("<script type='text/javascript'>");
//				out.println("window.location.href='index.jsp';");
//				out.println("alert('You do not have access rights.');");
//				out.println("</script>");
//			}
//		} catch (Exception e){
//			System.out.println("(EditProductServlet) Admin Validation Error: " + e + "\n");
//		}
		
		try {       
			if(request.getParameter("inputCategoryName")!=null){
				String inputCategoryName = request.getParameter("inputCategoryName");
				String inputCategoryDescription = request.getParameter("inputCategoryDescription");
				String inputCategoryImageUrl = request.getParameter("inputCategoryImageUrl");
				
				// connect to mysql database
				Class.forName("com.mysql.jdbc.Driver"); 
				String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
				Connection conn = DriverManager.getConnection(connURL);
				
				// insert inputs into categories
				String addCategoryQuery = "INSERT INTO duotexture.categories(`name`, `description`, `image`) VALUES(?, ?, ?);"; 
				PreparedStatement pstmt = conn.prepareStatement(addCategoryQuery);
			    pstmt.setString(1, inputCategoryName);
			    pstmt.setString(2, inputCategoryDescription);
			    pstmt.setString(3, inputCategoryImageUrl);
				int count = pstmt.executeUpdate(); 
			
				if(count > 0){
					response.sendRedirect("Assignment/website/add_category.jsp?categoryAddition=success"); 
				}else{
					response.sendRedirect("Assignment/website/add_category.jsp?categoryAddition=fail");
				}
			          
				conn.close();    
			}else{
				System.out.println("(AddCategoryServlet) Error: Wrong Flow\n");
				response.sendRedirect("Assignment/website/add_category.jsp?categoryAddition=fail");
			}
		} catch(java.sql.SQLIntegrityConstraintViolationException e){
			System.out.println("(AddCategoryServlet) Error: Duplicate Entry\n");
			response.sendRedirect("Assignment/website/add_category.jsp?categoryAddition=fail");
		} catch (java.lang.NumberFormatException e) {         
			System.out.println(" (AddCategoryServlet) Error: Invalid Inputs\n"); 
			response.sendRedirect("Assignment/website/add_category.jsp?categoryAddition=fail");
		} catch (Exception e) {         
			System.out.println(" (AddCategoryServlet) Error: " + e + "\n"); 
			response.sendRedirect("Assignment/website/add_category.jsp?categoryAddition=fail");
		}
	}

}
