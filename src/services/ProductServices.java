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
	
	// get products by category id
	@Path("getproductsbycategoryid")
	@GET
	@Produces("application/json")
	public Response getProductsByCategoryId(@QueryParam("categoryId") int categoryId) {
		
		// create JSONArray
		JSONArray productsJSONArray = new JSONArray();
		
		// declare ArrayList<Product> variable
		ArrayList<Product> productsArrayList;
		
		try {
			// get ArrayList result from utils and store it into productsArrayList
			productsArrayList = ProductUtils.getProductsByCategoryId(categoryId);
			
			// convert result in productsArrayList to JSONObject and store in productsJSONArray
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
			
			// return success data
			return Response.status(200).entity(productsJSONArray.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	// get products by category id and keyword
	@Path("getproductsbycategoryidandkeyword")
	@GET
	@Produces("application/json")
	public Response getProductsByCategoryIdAndKeyword(@QueryParam("categoryId") int categoryId, @QueryParam("keyword") String keyword) {
		
		// create JSONArray
		JSONArray searchProductsJSONArray = new JSONArray();
		
		// declare ArrayList<Product> variable
		ArrayList<Product> searchProductsArrayList;
		
		try {
			// get ArrayList result from utils and store it into searchProductsArrayList
			searchProductsArrayList = ProductUtils.getProductsByCategoryIdAndKeyword(categoryId, keyword);
			
			// convert result in searchProductsArrayList to JSONObject and store in searchProductsJSONArray
			for (int x=0; x<searchProductsArrayList.size(); x++) {
				JSONObject productObject = new JSONObject();
				
				productObject.put("productId", searchProductsArrayList.get(x).getProductId());
				productObject.put("name", searchProductsArrayList.get(x).getName());
				productObject.put("description", searchProductsArrayList.get(x).getDescription());
				productObject.put("cost_price", searchProductsArrayList.get(x).getCostPrice());
				productObject.put("retail_price", searchProductsArrayList.get(x).getRetailPrice());
				productObject.put("quantity", searchProductsArrayList.get(x).getQuantity());
				productObject.put("categoryId", searchProductsArrayList.get(x).getCategoryId());
				productObject.put("image", searchProductsArrayList.get(x).getImage());
				
				searchProductsJSONArray.put(productObject);
			}
			
			// return success data
			return Response.status(200).entity(searchProductsJSONArray.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	// get product by id
	@Path("getproductbyid")
	@GET
	@Produces("application/json")
	public Response getProductById(@QueryParam("productId") int productId) {
		
		try {
			// get Product result from utils and store it inside productBean
			Product productBean = ProductUtils.getProductById(productId);
			
			// store inside JSONObject
			JSONObject productObject = new JSONObject();
			
			productObject.put("productId", productBean.getProductId());
			productObject.put("name", productBean.getName());
			productObject.put("description", productBean.getDescription());
			productObject.put("cost_price", productBean.getCostPrice());
			productObject.put("retail_price", productBean.getRetailPrice());
			productObject.put("quantity", productBean.getQuantity());
			productObject.put("categoryId", productBean.getCategoryId());
			productObject.put("image", productBean.getImage());
			
			// return success data
			return Response.status(200).entity(productObject.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}
