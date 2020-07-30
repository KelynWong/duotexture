package servlets;

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
 * Servlet implementation class AddProductServlet
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

@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
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
					System.out.println("(AddProductServlet) There's no action to be taken for GET. Redirecting to categories.jsp to select a product of a category to add.\n"); 
					response.sendRedirect("Assignment/website/add_category.jsp");
				}
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='Assignment/website/index.jsp';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(AddProductServlet) Admin Validation Error: " + e + "\n");
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
						if(request.getParameter("inputProductName")!=null){
							String inputProductName = request.getParameter("inputProductName");
							String inputProductDescription = request.getParameter("inputProductDescription");
							Double inputCostPrice = Double.parseDouble(request.getParameter("inputCostPrice"));
							Double inputRetailPrice = Double.parseDouble(request.getParameter("inputRetailPrice"));
							int inputQuantity = Integer.parseInt(request.getParameter("inputQuantity"));
							int inputCategoryId = Integer.parseInt(request.getParameter("inputCategoryId"));
							String inputImageUrl = request.getParameter("inputImageUrl");
							
							// add product
							int count = ProductUtils.insertProduct(inputProductName, inputProductDescription, inputCostPrice, inputRetailPrice, inputQuantity, inputCategoryId, inputImageUrl);
						
							if(count > 0){
								response.sendRedirect("Assignment/website/add_product.jsp?productAddition=success"); 
							}else{
								response.sendRedirect("Assignment/website/add_product.jsp?productAddition=fail");
							}
						            
						}else{
							System.out.println("(AddProductServlet) Error: Wrong Flow\n");
							response.sendRedirect("Assignment/website/add_product.jsp?productAddition=fail");
						}
					} catch(java.sql.SQLIntegrityConstraintViolationException e){
						System.out.println("(AddProductServlet) Error: Duplicate Entry\n");
						response.sendRedirect("Assignment/website/add_product.jsp?productAddition=fail");
					} catch (java.lang.NumberFormatException e) {         
						System.out.println(" (AddProductServlet) Error: Invalid Inputs\n"); 
						response.sendRedirect("Assignment/website/add_product.jsp?productAddition=fail");
					} catch (Exception e) {         
						System.out.println("(AddProductServlet) Error: " + e + "\n"); 
						response.sendRedirect("Assignment/website/add_product.jsp?productAddition=fail");
					}
				}
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='Assignment/website/index.jsp';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(AddProductServlet) Admin Validation Error: " + e + "\n");
		}		
	}
}
