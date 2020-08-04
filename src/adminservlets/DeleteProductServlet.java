package adminservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import utils.ProductUtils;

/**
 * Servlet implementation class DeleteProductServlet
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

@WebServlet("/DeleteProductServlet")
public class DeleteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/// get current session
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
					System.out.println("(DeleteProductServlet) There's no action to be taken for GET. Redirecting to categories.jsp to select a product of a category to delete.\n"); 
					response.sendRedirect("${pageContext.request.contextPath}/categories");
				}
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='${pageContext.request.contextPath}/index';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(DeleteProductServlet) Admin Validation Error: " + e + "\n");
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
		
		// pre-define variable
		int categoryId = 0;
		
		try {
			if(session.getAttribute("categoryId")!=null){
				if(request.getParameter("productId")!=null){
					categoryId = (int) session.getAttribute("categoryId");
					int productId = Integer.parseInt(request.getParameter("productId"));
					
					// delete product
					int count = ProductUtils.deleteProduct(productId); 
					
					if(count > 0){
						out.println("<script type='text/javascript'>");
						out.println("window.location.href='${pageContext.request.contextPath}/productlistings?categoryId=" + categoryId + "';");
						out.println("alert('Product has successfully been deleted.');");
						out.println("</script>");
					}else{
						out.println("<script type='text/javascript'>");
						out.println("window.location.href='${pageContext.request.contextPath}/productlistings?categoryId=" + categoryId + "';");
						out.println("alert('Failed to delete product.');");
						out.println("</script>");						
					}   
				}else{
					System.out.println("(DeleteProductServlet) Error: ProductId is null.\n");
					out.println("<script type='text/javascript'>");
					out.println("window.location.href='${pageContext.request.contextPath}/productlistings?categoryId=" + categoryId + "';");
					out.println("alert('Failed to delete product.');");
					out.println("</script>");		
				}
			}else{
				System.out.println("(DeleteProductServlet) Error: CategoryId is null.\n");
				response.sendRedirect("${pageContext.request.contextPath}/categories");
			}
		} catch (Exception e) {         
			System.out.println("(DeleteProductServlet) Error: " + e + "\n");
			response.sendRedirect("${pageContext.request.contextPath}/categories");
		} 
	}

}
