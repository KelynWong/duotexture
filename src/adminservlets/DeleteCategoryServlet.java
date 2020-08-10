package adminservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.CategoryUtils;
import utils.S3Utils;

/**
 * Servlet implementation class DeleteCategoryServlet
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

@WebServlet("/DeleteCategoryServlet")
public class DeleteCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCategoryServlet() {
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
				// validate if user executing request is admin
				if(!session.getAttribute("accountType").equals("admin")){
					out.println("<script type='text/javascript'>");
					out.println("window.location.href='../ST0510-JAD-Assignment/index';");
					out.println("alert('You do not have access rights.');");
					out.println("</script>");
				} else {
					System.out.println("(adminservlets/DeleteCategoryServlet) There's no action to be taken for GET. Redirecting to categories.jsp to select a category to delete.\n"); 
					response.sendRedirect(request.getContextPath() + "/categories");
				}
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='../ST0510-JAD-Assignment/index';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(adminservlets/DeleteCategoryServlet) Admin Validation Error: " + e + "\n");
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
				// validate if user executing request is admin
				if(!session.getAttribute("accountType").equals("admin")){
					
					out.println("<script type='text/javascript'>");
					out.println("window.location.href='../ST0510-JAD-Assignment/index';");
					out.println("alert('You do not have access rights.');");
					out.println("</script>");
				} else {
					try {
						if(request.getParameter("categoryId")!=null){
								int categoryId = Integer.parseInt(request.getParameter("categoryId"));
								
								// get image url from database
								String categoryImageUrl = CategoryUtils.getCategoryImageUrl(categoryId);
								
								// get object name from image url
								String object_name = categoryImageUrl.substring(categoryImageUrl.lastIndexOf('/') + 1);
								
								// delete file
								S3Utils.deleteFile(object_name);
								
								// delete category
								int count = CategoryUtils.deleteCategory(categoryId); 
								
								if(count > 0){
									out.println("<script type='text/javascript'>");
									out.println("window.location.href='../ST0510-JAD-Assignment/categories';");
									out.println("alert('Category has successfully been deleted.');");
									out.println("</script>");
								} else{
									out.println("<script type='text/javascript'>");
									out.println("window.location.href='../ST0510-JAD-Assignment/categories';");
									out.println("alert('Failed to delete category.');");
									out.println("</script>");
								}    
							} else{
								out.println("<script type='text/javascript'>");
								out.println("window.location.href='../ST0510-JAD-Assignment/categories';");
								out.println("alert('Failed to delete category.');");
								out.println("</script>");
							}
					} catch (Exception e) {         
						System.out.println("(adminservlets/DeleteCategoryServlet) Error: " + e + "\n");
						response.sendRedirect(request.getContextPath() + "/categories");
					} 
				}
			} else{ 
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='../ST0510-JAD-Assignment/index';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(adminservlets/DeleteCategoryServlet) Admin Validation Error: " + e + "\n");
		}
	}

}
