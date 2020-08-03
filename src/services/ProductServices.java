package services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import javabeans.Product;
import utils.ProductUtils;

@Path("productservices")
public class ProductServices {
	
	@Path("getproducts")
	@GET
	@Produces("application/json")
	public Response getProducts() {
		
		JSONArray productsJSONArray = new JSONArray();
		ArrayList<Product> productsArrayList;
		
		try {
			productsArrayList = ProductUtils.getProducts();
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
}
