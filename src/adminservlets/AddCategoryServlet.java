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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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

	// upload settings
    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    
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
				        	// create a factory for disk-based file items
				        	DiskFileItemFactory factory = new DiskFileItemFactory();
				        	
				        	// sets memory threshold - beyond which files are stored in disk
				            factory.setSizeThreshold(MEMORY_THRESHOLD);

				        	// configure a repository (to ensure a secure temporary location is used)
				        	ServletContext servletContext = this.getServletConfig().getServletContext();
				        	File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
				        	factory.setRepository(repository);

				        	// create a new file upload handler
				        	ServletFileUpload upload = new ServletFileUpload(factory);
				        	
				        	// sets maximum size of upload file
				            upload.setFileSizeMax(MAX_FILE_SIZE);
				             
				            // sets maximum size of request (include file + form data)
				            upload.setSizeMax(MAX_REQUEST_SIZE);

				        	// parse the request
				        	List<FileItem> items = upload.parseRequest(request);

				        	// process the uploaded items
				        	Iterator<FileItem> iter = items.iterator();
				        	
				        	String[] fieldNameArray;
				        	String[] valueArray;
				        	
				        	while (iter.hasNext()) {
				        	    FileItem item = iter.next();
				        	    if (item.isFormField()) {
				        	    	String name = item.getFieldName();
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
