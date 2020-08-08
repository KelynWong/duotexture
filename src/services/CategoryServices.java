package services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import javabeans.Category;
import utils.CategoryUtils;

@Path("categoryservices")
public class CategoryServices {
	
	// get all categories
	@Path("getcategories")
	@GET
	@Produces("application/json")
	public Response getCategories() {
		
		// create JSONArray
		JSONArray categoriesJSONArray = new JSONArray();
		
		// declare ArrayList<Category>
		ArrayList<Category> categoriesArrayList;
		
		try {
			// get ArrayList result from utils and store it into categoriesArrayList
			categoriesArrayList = CategoryUtils.getCategories();
			
			// convert result in categoriesArrayList to JSONObject and store in categoriesJSONArray
			for (int x=0; x<categoriesArrayList.size(); x++) {
				JSONObject categoryObject = new JSONObject();
				
				categoryObject.put("categoryId", categoriesArrayList.get(x).getCategoryId());
				categoryObject.put("name", categoriesArrayList.get(x).getName());
				categoryObject.put("description", categoriesArrayList.get(x).getDescription());
				categoryObject.put("image", categoriesArrayList.get(x).getImage());
				
				categoriesJSONArray.put(categoryObject);
			}
			
			// return success data
			return Response.status(200).entity(categoriesJSONArray.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	// get category by id
	@Path("getcategorybyid")
	@GET
	@Produces("application/json")
	public Response getCategoryById(@QueryParam("categoryId") int categoryId) {
		
		try {
			// get Category result from utils and store it into categoryBean
			Category categoryBean = CategoryUtils.getCategoryById(categoryId);
			
			// store inside JSONObject
			JSONObject categoryObject = new JSONObject();
			
			categoryObject.put("categoryId", categoryBean.getCategoryId());
			categoryObject.put("name", categoryBean.getName());
			categoryObject.put("description", categoryBean.getDescription());
			categoryObject.put("image", categoryBean.getImage());
			
			// return success data
			return Response.status(200).entity(categoryObject.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}
