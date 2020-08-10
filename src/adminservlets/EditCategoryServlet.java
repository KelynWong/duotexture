package adminservlets;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import utils.CategoryUtils;
import utils.S3Utils;

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
					out.println("window.location.href='../ST0510-JAD-Assignment/index';");
					out.println("alert('You do not have access rights.');");
					out.println("</script>");
				} else {
					System.out.println("(adminservlets/EditCategoryServlet) There's no action to be taken for GET. Redirecting to categories.jsp to select a category to edit.\n"); 
					response.sendRedirect(request.getContextPath() + "/categories");
				}
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='../ST0510-JAD-Assignment/index';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(adminservlets/EditCategoryServlet) Admin Validation Error: " + e + "\n");
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
						String imageUrl = null;
						 
				        try {
				        	// create a factory for disk-based file items
				        	DiskFileItemFactory factory = new DiskFileItemFactory();
				        	
				        	// sets memory threshold - beyond which files are stored in disk
				            factory.setSizeThreshold(MEMORY_THRESHOLD);

				        	// configure a repository (to ensure a secure temporary location is used)
				        	factory.setRepository(new File(System.getProperty("java.io.tmpdir")));
				        	 
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
				        	
				        	ArrayList<String> valueArrayList = new ArrayList<String>();
				        	
				        	while (iter.hasNext()) {
				        	    FileItem item = iter.next();
				        	    if (item.isFormField()) {
				        	        String value = item.getString();
				        	        valueArrayList.add(value);
				        	    } else {
				        	    	String content = "";
				        	    	if(!content.equals("")) {
				        	    		// #1 delete existing image 
				        	    		System.out.println("entered");
				        	    		// get image url from database
										String categoryImageUrl = CategoryUtils.getCategoryImageUrl(Integer.parseInt(valueArrayList.get(0)));
										
										// get object name from image url
										String object_name = categoryImageUrl.substring(categoryImageUrl.lastIndexOf('/') + 1);
										
										// delete file
										S3Utils.deleteFile(object_name);
										
										// #2 upload new image
										InputStream fileInputStream = item.getInputStream();
										String fileName = new File(item.getName()).getName();
					        	    	imageUrl = S3Utils.uploadFile(fileName, fileInputStream);
				        	    	}
				        	    }
				        	}
				            
							// get fields
							int inputCategoryId = (int) Integer.parseInt(valueArrayList.get(0));
							String inputCategoryName = (String) valueArrayList.get(1);
							String inputCategoryDescription = (String) valueArrayList.get(2);
							String inputCategoryImageUrl;
							
							if(imageUrl == null) {
								System.out.println("image == null");
								inputCategoryImageUrl = (String) valueArrayList.get(3);
							} else {
								System.out.println("image != null ");
								inputCategoryImageUrl = imageUrl;
							}
							
							
							// edit category
							int count = CategoryUtils.editCategory(inputCategoryName, inputCategoryDescription, inputCategoryImageUrl, inputCategoryId);
							
							if(count > 0){
								response.sendRedirect(request.getContextPath() + "/editcategory?categoryId=" + inputCategoryId + "&categoryEdit=success"); 
							}else{
								response.sendRedirect(request.getContextPath() + "/editcategory?categoryId=" + inputCategoryId + "&categoryEdit=fail");
							}
								           
								
				        } catch(java.sql.SQLIntegrityConstraintViolationException e){
							System.out.println("(adminservlets/EditCategoryServlet) Error: Duplicate Entry\n");
							response.sendRedirect(request.getContextPath() + "/editcategory?categoryId=" + request.getParameter("inputCategoryId") + "&categoryEdit=fail");
						} catch (java.lang.NumberFormatException e) {         
							System.out.println("(adminservlets/EditCategoryServlet) Error: Invalid Inputs\n"); 
							response.sendRedirect(request.getContextPath() + "/editcategory?categoryId=" + request.getParameter("inputCategoryId") + "&categoryEdit=fail");
				        } catch (FileUploadException ex) {
				        	System.out.println("(adminservlets/EditCategoryServlet) Error: FileUploadException \n");
				        	response.sendRedirect(request.getContextPath() + "/editcategory?categoryId=" + request.getParameter("inputCategoryId") + "&categoryEdit=fail");
						} catch (Exception e) {         
							System.out.println("(adminservlets/EditCategoryServlet) Error: " + e + "\n"); 
							response.sendRedirect(request.getContextPath() + "/editcategory?categoryId=" + request.getParameter("inputCategoryId") + "&categoryEdit=fail");
						}
				        
				    } else{
						System.out.println("(adminservlets/EditCategoryServlet) Error: Wrong Flow\n");
						response.sendRedirect(request.getContextPath() + "/editcategory?categoryId=" + request.getParameter("inputCategoryId") + "&categoryEdit=fail");
					}
				}
					 
			}else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='../ST0510-JAD-Assignment/index';");
				out.println("alert('You are not logged in.');");
				out.println("</script>");
			}
			
		} catch (Exception e){
			System.out.println("(adminservlets/EditCategoryServlet) Admin Validation Error: " + e + "\n");
		}	
	}
}