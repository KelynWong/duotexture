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

@WebServlet("/EditProductServlet")
public class EditProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProductServlet() {
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
					out.println("window.location.href='Assignment/website/index.jsp';");
					out.println("alert('You do not have access rights.');");
					out.println("</script>");
				} else {
					System.out.println("(EditProductServlet) There's no action to be taken for GET. Redirecting to categories.jsp to select a product of a category to edit.\n"); 
					response.sendRedirect("Assignment/website/categories.jsp");
				}
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='Assignment/website/index.jsp';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(EditProductServlet) Admin Validation Error: " + e + "\n");
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
					out.println("window.location.href='Assignment/website/index.jsp';");
					out.println("alert('You do not have access rights.');");
					out.println("</script>");
				} else {
					try {       
						if(request.getParameter("inputProductId")!=null){
							int inputProductId = Integer.parseInt(request.getParameter("inputProductId"));
							String inputProductName = request.getParameter("inputProductName");
							String inputProductDescription = request.getParameter("inputProductDescription");
							Double inputCostPrice = Double.parseDouble(request.getParameter("inputCostPrice"));
							Double inputRetailPrice = Double.parseDouble(request.getParameter("inputRetailPrice"));
							int inputQuantity = Integer.parseInt(request.getParameter("inputQuantity"));
							int inputCategoryId = Integer.parseInt(request.getParameter("inputCategoryId"));
							String inputImageUrl = request.getParameter("inputImageUrl");
							
							// edit product
							int count = ProductUtils.editProduct(inputProductId, inputProductName, inputProductDescription, inputCostPrice, inputRetailPrice, inputQuantity, inputCategoryId, inputImageUrl);
						
							if(count > 0){
								response.sendRedirect("Assignment/website/edit_product.jsp?productId=" + inputProductId + "&productEdit=success"); 
							}else{
								response.sendRedirect("Assignment/website/edit_product.jsp?productId=" + inputProductId + "&productEdit=fail");
							}
							  
						}else{
							System.out.println("(EditProductServlet) Error: Wrong Flow\n");
							response.sendRedirect("Assignment/website/edit_product.jsp?productEdit=fail");
						}
					} catch(java.sql.SQLIntegrityConstraintViolationException e){
						System.out.println("(EditProductServlet) Error: Duplicate Entry\n");
						response.sendRedirect("Assignment/website/edit_product.jsp?productId=" + request.getParameter("inputProductId") + "&productEdit=fail");
					} catch (java.lang.NumberFormatException e) {         
						System.out.println("(EditProductServlet) Error: Invalid Inputs\n"); 
						response.sendRedirect("Assignment/website/edit_product.jsp?productId=" + request.getParameter("inputProductId") + "&productEdit=fail");
					} catch (Exception e) {         
						System.out.println("(EditProductServlet) Error :" + e + "\n");    
						response.sendRedirect("Assignment/website/edit_product.jsp?productId=" + request.getParameter("inputProductId") + "&productEdit=fail");
					}
				}
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='Assignment/website/index.jsp';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(EditProductServlet) Admin Validation Error: " + e + "\n");
		}
	}

}
