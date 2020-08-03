package services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
}
