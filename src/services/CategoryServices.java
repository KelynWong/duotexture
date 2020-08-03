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
	
	@Path("getcategories")
	@GET
	@Produces("application/json")
	public Response getCategories() {
		
		JSONArray categoriesJSONArray = new JSONArray();
		ArrayList<Category> categoriesArrayList;
		
		try {
			categoriesArrayList = CategoryUtils.getCategories();
			for (int x=0; x<categoriesArrayList.size(); x++) {
				JSONObject categoryObject = new JSONObject();
				
				categoryObject.put("categoryId", categoriesArrayList.get(x).getCategoryId());
				categoryObject.put("name", categoriesArrayList.get(x).getName());
				categoryObject.put("description", categoriesArrayList.get(x).getDescription());
				categoryObject.put("image", categoriesArrayList.get(x).getImage());
				
				categoriesJSONArray.put(categoryObject);
			}
			
			return Response.status(200).entity(categoriesJSONArray.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	@Path("getcategorybyid")
	@GET
	@Produces("application/json")
	public Response getCategoryById(@QueryParam("categoryId") int categoryId) {
		
		try {
			Category categoryBean = CategoryUtils.getCategoryById(categoryId);
			
			JSONObject categoryObject = new JSONObject();
			
			categoryObject.put("categoryId", categoryBean.getCategoryId());
			categoryObject.put("name", categoryBean.getName());
			categoryObject.put("description", categoryBean.getDescription());
			categoryObject.put("image", categoryBean.getImage());
			
			return Response.status(200).entity(categoryBean.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}
