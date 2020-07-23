package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.*;

/**
 * Servlet implementation class AddProductServlet
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

		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String fah = request.getParameter("fah");
		
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target("http://localhost:8080/RestWSClient/rest/")
				.path("FtoCService").queryParam("fah", fah);
		Invocation.Builder invoBuilder = target.request(MediaType.APPLICATION_JSON);
		Response resp = invoBuilder.get();
		System.out.println("Status: " + resp.getStatus());
		
		if(resp.getStatus() == Response.Status.OK.getStatusCode()) {
			System.out.println("Success");
			String result = resp.readEntity(new GenericType<String>() {});
			
			System.out.println("result: " + result);
			
			request.setAttribute("result", result);
			
			RequestDispatcher rd = request.getRequestDispatcher("/FtoCdisplay.jsp");
			rd.forward(request, response);
		}
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
			if(request.getParameter("inputProductName")!=null){
				String inputProductName = request.getParameter("inputProductName");
				String inputProductDescription = request.getParameter("inputProductDescription");
				Double inputCostPrice = Double.parseDouble(request.getParameter("inputCostPrice"));
				Double inputRetailPrice = Double.parseDouble(request.getParameter("inputRetailPrice"));
				int inputQuantity = Integer.parseInt(request.getParameter("inputQuantity"));
				int inputCategoryId = Integer.parseInt(request.getParameter("inputCategoryId"));
				String inputImageUrl = request.getParameter("inputImageUrl");
				
				// connect to mysql database
				Class.forName("com.mysql.jdbc.Driver"); 
				String connURL = "jdbc:mysql://localhost/duotexture?user=root&password=password&serverTimezone=UTC";
				Connection conn = DriverManager.getConnection(connURL);
				
				// insert inputs into products
				String updateProductQuery = "INSERT INTO duotexture.products(`name`, `description`, `cost_price`, `retail_price`, `quantity`, `categoryId`, `image`) VALUES(?, ?, ?, ?, ?, ?, ?);"; 
				PreparedStatement pstmt = conn.prepareStatement(updateProductQuery);
			    pstmt.setString(1, inputProductName);
			    pstmt.setString(2, inputProductDescription);
			    pstmt.setDouble(3, inputCostPrice);
			    pstmt.setDouble(4, inputRetailPrice);
			    pstmt.setInt(5, inputQuantity);
			    pstmt.setInt(6, inputCategoryId);
			    pstmt.setString(7, inputImageUrl);
				int count = pstmt.executeUpdate(); 
			
				if(count > 0){
					response.sendRedirect("Assignment/website/add_product.jsp?productAddition=success"); 
				}else{
					response.sendRedirect("Assignment/website/add_product.jsp?productAddition=fail");
				}
			          
				conn.close();    
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

//	public void ServletRedirect() {
//		System.out.println("hihi");
//		HttpServletResponse response = null;
//		try {
//			response.sendRedirect("Assignment/website/add_product.jsp?");
//			PrintWriter out = response.getWriter();  
//			out.println("<script type='text/javascript'>");
//			out.println("window.location.href='index.jsp';");
//			out.println("alert('You do not have access rights.');");
//			out.println("</script>");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        // TODO Auto-generated constructor stub
//    }
}
