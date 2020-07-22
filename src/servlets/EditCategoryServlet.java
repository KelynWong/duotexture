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
 * Servlet implementation class EditCategoryServlet
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

@WebServlet("/EditCategoryServlet")
public class EditCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCategoryServlet() {
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
			if(request.getParameter("inputCategoryId")!=null){
				int inputCategoryId = Integer.parseInt(request.getParameter("inputCategoryId"));
				String inputCategoryName = request.getParameter("inputCategoryName");
				String inputCategoryDescription = request.getParameter("inputCategoryDescription");
				String inputCategoryImageUrl = request.getParameter("inputCategoryImageUrl");
				
				// connect to mysql database
				Class.forName("com.mysql.jdbc.Driver"); 
				String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
				Connection conn = DriverManager.getConnection(connURL);
				
				// edit and update products with inputs by product id
				String updateCategoryQuery = "UPDATE categories SET name=?, description=?, image=? WHERE categoryId=?;"; 
				PreparedStatement pstmt = conn.prepareStatement(updateCategoryQuery);
			    pstmt.setString(1, inputCategoryName);
			    pstmt.setString(2, inputCategoryDescription);
			    pstmt.setString(3, inputCategoryImageUrl);
			    pstmt.setInt(4, inputCategoryId);
				int count = pstmt.executeUpdate(); 
			
				if(count > 0){
					response.sendRedirect("Assignment/website/edit_category.jsp?categoryId=" + inputCategoryId + "&categoryEdit=success"); 
				}else{
					response.sendRedirect("Assignment/website/edit_category.jsp?categoryId=" + inputCategoryId + "&categoryEdit=fail");
				}
				
				conn.close();   
			}else{
				System.out.println("(EditCategoryServlet) Error: Wrong Flow\n");
				response.sendRedirect("Assignment/website/edit_category.jsp?categoryId=" + request.getParameter("inputCategoryId") + "categoryEdit=fail");
			}
		} catch(java.sql.SQLIntegrityConstraintViolationException e){
			System.out.println("(EditCategoryServlet) Error: Duplicate Entry\n");
			response.sendRedirect("Assignment/website/edit_category.jsp?categoryId=" + request.getParameter("inputCategoryId") + "&categoryEdit=fail");
		} catch (java.lang.NumberFormatException e) {         
			System.out.println("(EditCategoryServlet) Error: Invalid Inputs\n"); 
			response.sendRedirect("Assignment/website/edit_category.jsp?categoryId=" + request.getParameter("inputCategoryId") + "&categoryEdit=fail");
		} catch (Exception e) {         
			System.out.println("(EditCategoryServlet) Error :" + e + "\n");    
			response.sendRedirect("Assignment/website/edit_category.jsp?categoryId=" + request.getParameter("inputCategoryId") + "&categoryEdit=fail");
		}
	}

}
