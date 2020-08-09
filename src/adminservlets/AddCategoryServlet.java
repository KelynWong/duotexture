package adminservlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import java.util.Iterator;
import utils.CategoryUtils;

/**
 * Servlet implementation class AddCategoryServlet
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
	
    public AddCategoryServlet() {
        super();
    }

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
					System.out.println("(adminservlets/AddCategoryServlet) There's no action to be taken for GET. Redirecting to add_category.jsp to add category.\n"); 
					response.sendRedirect(request.getContextPath() + "/addcategory");
				}
				
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='../ST0510-JAD-Assignment/index';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(adminservlets/AddCategoryServlet) Admin Validation Error: " + e + "\n");
		}
	}

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
					
					 if (ServletFileUpload.isMultipartContent(request)){
						 
				        try {
				        	// Create a factory for disk-based file items
				        	DiskFileItemFactory factory = new DiskFileItemFactory();

				        	// Configure a repository (to ensure a secure temporary location is used)
				        	ServletContext servletContext = this.getServletConfig().getServletContext();
				        	File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				        	factory.setRepository(repository);

				        	// Create a new file upload handler
				        	ServletFileUpload upload = new ServletFileUpload(factory);

				        	// Parse the request
				        	List<FileItem> items = upload.parseRequest((RequestContext) request);

				        	// Process the uploaded items
				        	Iterator<FileItem> iter = items.iterator();
				        	
				        	System.out.println("iter");
				        	System.out.println(iter);
				        	while (iter.hasNext()) {
				        	    FileItem item = iter.next();
				        	    System.out.println("item");
				        	    System.out.println(item);
				        	    if (item.isFormField()) {
				        	    	String name = item.getFieldName();
				        	    	System.out.println("name");
				        	    	System.out.println(name);
				        	        String value = item.getString();
				        	    } else {
				        	    	String fieldName = item.getFieldName();
				        	        String fileName = item.getName();
				        	        String contentType = item.getContentType();
				        	        boolean isInMemory = item.isInMemory();
				        	        long sizeInBytes = item.getSize();
				        	    }
				        	}
				            
				            try {       
								if(request.getParameter("inputCategoryName")!=null){
									// get fields
									String inputCategoryName = request.getParameter("inputCategoryName");
									String inputCategoryDescription = request.getParameter("inputCategoryDescription");
									String inputCategoryImageUrl = request.getParameter("inputCategoryImageUrl");
									
									// add category
									int count = CategoryUtils.insertCategory(inputCategoryName, inputCategoryDescription, inputCategoryImageUrl);
									
									// check count
									if(count > 0){
										response.sendRedirect(request.getContextPath() + "/addcategory?categoryAddition=success"); 
									} else{
										response.sendRedirect(request.getContextPath() + "/addcategory?categoryAddition=fail");
									}
								           
								} else{
									System.out.println("(adminservlets/AddCategoryServlet) Error: Wrong Flow\n");
									response.sendRedirect(request.getContextPath() + "/addcategory?categoryAddition=fail");
								}
								
							} catch(java.sql.SQLIntegrityConstraintViolationException e){
								System.out.println("(adminservlets/AddCategoryServlet) Error: Duplicate Entry\n");
								response.sendRedirect(request.getContextPath() + "/addcategory?categoryAddition=fail");
							} catch (java.lang.NumberFormatException e) {         
								System.out.println(" (adminservlets/AddCategoryServlet) Error: Invalid Inputs\n"); 
								response.sendRedirect(request.getContextPath() + "/addcategory?categoryAddition=fail");
							} catch (Exception e) {         
								System.out.println(" (adminservlets/AddCategoryServlet) Error: " + e + "\n"); 
								response.sendRedirect(request.getContextPath() + "/addcategory?categoryAddition=fail");
							}
				            
				            
				        } catch (FileUploadException ex) {
				        	System.out.println("(adminservlets/AddCategoryServlet) Error: FileUploadException \n");
							response.sendRedirect(request.getContextPath() + "/addcategory?categoryAddition=fail");
				        } catch (Exception ex) {
				        	System.out.println("(adminservlets/AddCategoryServlet) Error: " + ex + "\n");
							response.sendRedirect(request.getContextPath() + "/addcategory?categoryAddition=fail");
				        }
				    }
				}
					 
			}else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='../ST0510-JAD-Assignment/index';");
				out.println("alert('You are not logged in.');");
				out.println("</script>");
			}
			
		} catch (Exception e){
			System.out.println("(adminservlets/AddCategoryServlet) Admin Validation Error: " + e + "\n");
		}	
	}

}
