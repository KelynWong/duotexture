package services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import javabeans.Product;
import utils.ProductUtils;

@Path("productservices")
public class ProductServices {
	
	@Path("getproductsbycategoryid")
	@GET
	@Produces("application/json")
	public Response getProductsByCategoryId(@QueryParam("categoryId") int categoryId) {
		
		JSONArray productsJSONArray = new JSONArray();
		ArrayList<Product> productsArrayList;
		
		try {
			productsArrayList = ProductUtils.getProductsByCategoryId(categoryId);
			for (int x=0; x<productsArrayList.size(); x++) {
				JSONObject productObject = new JSONObject();
				
				productObject.put("productId", productsArrayList.get(x).getProductId());
				productObject.put("name", productsArrayList.get(x).getName());
				productObject.put("description", productsArrayList.get(x).getDescription());
				productObject.put("cost_price", productsArrayList.get(x).getCostPrice());
				productObject.put("retail_price", productsArrayList.get(x).getRetailPrice());
				productObject.put("quantity", productsArrayList.get(x).getQuantity());
				productObject.put("categoryId", productsArrayList.get(x).getCategoryId());
				productObject.put("image", productsArrayList.get(x).getImage());
				
				productsJSONArray.put(productObject);
			}
			
			return Response.status(200).entity(productsJSONArray.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	@Path("getproductbyid")
	@GET
	@Produces("application/json")
	public Response getProductById(@QueryParam("productId") int productId) {
		
		try {
			Product productBean = ProductUtils.getProductById(productId);
			
			JSONObject productObject = new JSONObject();
			
			productObject.put("productId", productBean.getProductId());
			productObject.put("name", productBean.getName());
			productObject.put("description", productBean.getDescription());
			productObject.put("cost_price", productBean.getCostPrice());
			productObject.put("retail_price", productBean.getRetailPrice());
			productObject.put("quantity", productBean.getQuantity());
			productObject.put("categoryId", productBean.getCategoryId());
			productObject.put("image", productBean.getImage());
			
			return Response.status(200).entity(productObject.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}
