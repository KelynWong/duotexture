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

/**
 * Servlet implementation class EditCategoryServlet
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
					out.println("window.location.href='${pageContext.request.contextPath}/index';");
					out.println("alert('You do not have access rights.');");
					out.println("</script>");
				} else {
					System.out.println("(EditCategoryServlet) There's no action to be taken for GET. Redirecting to categories.jsp to select a category to edit.\n"); 
					response.sendRedirect("Assignment/website/categories.jsp");
				}
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='${pageContext.request.contextPath}/index';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(EditCategoryServlet) Admin Validation Error: " + e + "\n");
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
					out.println("window.location.href='${pageContext.request.contextPath}/index';");
					out.println("alert('You do not have access rights.');");
					out.println("</script>");
				} else {
					try {       
						if(request.getParameter("inputCategoryId")!=null){
							int inputCategoryId = Integer.parseInt(request.getParameter("inputCategoryId"));
							String inputCategoryName = request.getParameter("inputCategoryName");
							String inputCategoryDescription = request.getParameter("inputCategoryDescription");
							String inputCategoryImageUrl = request.getParameter("inputCategoryImageUrl");

							// edit category
							int count = CategoryUtils.editCategory(inputCategoryName, inputCategoryDescription, inputCategoryImageUrl, inputCategoryId);
							
							if(count > 0){
								response.sendRedirect("Assignment/website/edit_category.jsp?categoryId=" + inputCategoryId + "&categoryEdit=success"); 
							}else{
								response.sendRedirect("Assignment/website/edit_category.jsp?categoryId=" + inputCategoryId + "&categoryEdit=fail");
							}
							
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
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='${pageContext.request.contextPath}/index';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(EditCategoryServlet) Admin Validation Error: " + e + "\n");
		}
	}

}
