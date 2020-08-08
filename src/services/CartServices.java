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
	
	@Path("getcarts")
	@GET
	@Produces("application/json")
	public Response getCarts() {
		
		JSONArray cartsJSONArray = new JSONArray();
		ArrayList<Cart> cartsArrayList;
		
		try {
			cartsArrayList = CartUtils.getCarts();
			for (int x=0; x<cartsArrayList.size(); x++) {
				JSONObject cartObject = new JSONObject();
				
				cartObject.put("userId", cartsArrayList.get(x).getUserId());
				cartObject.put("productId", cartsArrayList.get(x).getProductId());
				cartObject.put("quantity", cartsArrayList.get(x).getQuantity());
				
				cartsJSONArray.put(cartObject);
			}
			
			return Response.status(200).entity(cartsJSONArray.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	@Path("getcartsbyuserid")
	@GET
	@Produces("application/json")
	public Response getCartsByUserId(@QueryParam("userId") int userId) {
		
		try {
			Cart cartBean = CartUtils.getCartsByUserId(userId);
			
			JSONObject cartObject = new JSONObject();

			cartObject.put("userId", cartBean.getUserId());
			cartObject.put("productId", cartBean.getProductId());
			cartObject.put("quantity", cartBean.getQuantity());
			
			return Response.status(200).entity(cartObject.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}
