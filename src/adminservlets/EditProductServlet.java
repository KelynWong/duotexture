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
import utils.ProductUtils;
import utils.S3Utils;

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
					out.println("window.location.href='../ST0510-JAD-Assignment/index';");
					out.println("alert('You do not have access rights.');");
					out.println("</script>");
				} else {
					System.out.println("(adminservlets/EditProductServlet) There's no action to be taken for GET. Redirecting to categories.jsp to select a product of a category to edit.\n"); 
					response.sendRedirect(request.getContextPath() + "/categories");
				}
			} else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='../ST0510-JAD-Assignment/index';");
				out.println("alert('You do not have access rights.');");
				out.println("</script>");
			}
		} catch (Exception e){
			System.out.println("(adminservlets/EditProductServlet) Admin Validation Error: " + e + "\n");
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
				        	    	long inputFileSize = item.getSize();
				        	    	System.out.println(inputFileSize);
				        	    	if(inputFileSize>0) {
				        	    		// #1 delete existing image 

				        	    		// get image url from database
				    					String productImageUrl = ProductUtils.getProductImageUrl(Integer.parseInt(valueArrayList.get(0)));
										
										// get object name from image url
				    					String object_name = productImageUrl.substring(productImageUrl.lastIndexOf('/') + 1);
										
										// delete file
										S3Utils.deleteFile(object_name);
										
										// #2 upload new image
										InputStream fileInputStream = item.getInputStream();
										String fileName = new File(item.getName()).getName();
					        	    	imageUrl = S3Utils.uploadFile(fileName, fileInputStream);
				        	    	}
				        	    }
				        	}
							
							int inputProductId = (int) Integer.parseInt(valueArrayList.get(0));
							String inputProductName = (String) valueArrayList.get(1);
							String inputProductDescription = (String) valueArrayList.get(2);
							Double inputCostPrice = (Double) Double.parseDouble(valueArrayList.get(3));
							Double inputRetailPrice = (Double) Double.parseDouble(valueArrayList.get(4));
							int inputQuantity = (int) Integer.parseInt(valueArrayList.get(5));
							int inputCategoryId = (int) Integer.parseInt(valueArrayList.get(6));
							
							String inputImageUrl;
							if(imageUrl == null) {
								inputImageUrl = (String) valueArrayList.get(7);
							} else {
								inputImageUrl = imageUrl;
							}
							
							// edit product
							int count = ProductUtils.editProduct(inputProductId, inputProductName, inputProductDescription, inputCostPrice, inputRetailPrice, inputQuantity, inputCategoryId, inputImageUrl);
						
							if(count > 0){
								response.sendRedirect(request.getContextPath() + "/editproduct?productId=" + inputProductId + "&productEdit=success"); 
							}else{
								response.sendRedirect(request.getContextPath() + "/editproduct?productId=" + inputProductId + "&productEdit=fail");
							}
								
				        } catch(java.sql.SQLIntegrityConstraintViolationException e){
							System.out.println("(adminservlets/EditProductServlet) Error: Duplicate Entry\n");
							response.sendRedirect(request.getContextPath() + "/editproduct?productId=" + request.getParameter("inputProductId") + "&productEdit=fail");
						} catch (java.lang.NumberFormatException e) {         
							System.out.println("(adminservlets/EditProductServlet) Error: Invalid Inputs\n"); 
							response.sendRedirect(request.getContextPath() + "/editproduct?productId=" + request.getParameter("inputProductId") + "&productEdit=fail");
				        } catch (FileUploadException ex) {
				        	System.out.println("(adminservlets/EditProductServlet) Error: FileUploadException \n");
				        	response.sendRedirect(request.getContextPath() + "/editproduct?productId=" + request.getParameter("inputProductId") + "&productEdit=fail");
						} catch (Exception e) {         
							System.out.println("(adminservlets/EditProductServlet) Error: " + e + "\n"); 
							response.sendRedirect(request.getContextPath() + "/editproduct?productId=" + request.getParameter("inputProductId") + "&productEdit=fail");
						}
				        
				    } else{
						System.out.println("(adminservlets/EditProductServlet) Error: Wrong Flow\n");
						response.sendRedirect(request.getContextPath() + "/editproduct?productId=" + request.getParameter("inputProductId") + "&productEdit=fail");
					}
				}
					 
			}else{
				out.println("<script type='text/javascript'>");
				out.println("window.location.href='../ST0510-JAD-Assignment/index';");
				out.println("alert('You are not logged in.');");
				out.println("</script>");
			}
			
		} catch (Exception e){
			System.out.println("(adminservlets/EditProductServlet) Admin Validation Error: " + e + "\n");
		}	
	}
}
