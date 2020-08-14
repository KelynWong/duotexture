package services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import javabeans.Cart;
import utils.CartUtils;

@Path("cartservices")
public class CartServices {
	
	// get all carts
	@Path("getcarts")
	@GET
	@Produces("application/json")
	public Response getCarts() {

		// create JSONArray
		JSONArray cartsJSONArray = new JSONArray();
		
		// declare ArrayList<Cart> variable
		ArrayList<Cart> cartsArrayList;
		
		try {
			// get ArrayList result from utils and store it into cartsArrayList
			cartsArrayList = CartUtils.getCarts();
			
			// convert result in cardsArrayList to JSONObject and store in cardsJSONArray
			for (int x=0; x<cartsArrayList.size(); x++) {
				JSONObject cartObject = new JSONObject();
				
				cartObject.put("userId", cartsArrayList.get(x).getUserId());
				cartObject.put("productId", cartsArrayList.get(x).getProductId());
				cartObject.put("quantity", cartsArrayList.get(x).getQuantity());
				cartObject.put("dateTime", cartsArrayList.get(x).getDateTime());
				
				cartObject.put("productName", cartsArrayList.get(x).getProductName());
				cartObject.put("productDescription", cartsArrayList.get(x).getProductDescription());
				cartObject.put("productCostPrice", cartsArrayList.get(x).getProductCostPrice());
				cartObject.put("productImage", cartsArrayList.get(x).getProductImage());
				
				cartsJSONArray.put(cartObject);
			}
			
			// return success data
			return Response.status(200).entity(cartsJSONArray.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	// get carts by user id
	@Path("getcartsbyuserid")
	@GET
	@Produces("application/json")
	public Response getCartsByUserId(@QueryParam("userId") int userId) {
		
		// create JSONArray
		JSONArray cartsJSONArray = new JSONArray();
		
		// declare ArrayList<Cart> variable
		ArrayList<Cart> cartsArrayList;
		
		try {
			// get ArrayList result from utils and store it into cartsArrayList
			cartsArrayList =  CartUtils.getCartsByUserId(userId);
			
			// convert result in cardsArrayList to JSONObject and store in cardsJSONArray
			for (int x=0; x<cartsArrayList.size(); x++) {
				JSONObject cartObject = new JSONObject();
				
				cartObject.put("userId", cartsArrayList.get(x).getUserId());
				cartObject.put("productId", cartsArrayList.get(x).getProductId());
				cartObject.put("quantity", cartsArrayList.get(x).getQuantity());
				cartObject.put("dateTime", cartsArrayList.get(x).getDateTime());
				
				cartObject.put("productName", cartsArrayList.get(x).getProductName());
				cartObject.put("productDescription", cartsArrayList.get(x).getProductDescription());
				cartObject.put("productCostPrice", cartsArrayList.get(x).getProductCostPrice());
				cartObject.put("productImage", cartsArrayList.get(x).getProductImage());
				
				cartsJSONArray.put(cartObject);
			}
			
			// return success data
			return Response.status(200).entity(cartsJSONArray.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}
