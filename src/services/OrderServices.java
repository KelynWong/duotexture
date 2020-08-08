package services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import javabeans.Order;
import utils.OrderUtils;

@Path("orderservices")
public class OrderServices {
	
	// get all orders
	@Path("getorders")
	@GET
	@Produces("application/json")
	public Response getOrders() {
		
		// create JSONArray
		JSONArray ordersJSONArray = new JSONArray();
		
		// declare ArrayList<Order> variable
		ArrayList<Order> ordersArrayList;
		
		try {
			// get ArrayList result from utils and store it into ordersArrayList
			ordersArrayList = OrderUtils.getOrders();
			
			// convert result in ordersArrayList to JSONObject and store in ordersJSONArray
			for (int x=0; x<ordersArrayList.size(); x++) {
				JSONObject orderObject = new JSONObject();
				
				orderObject.put("userId", ordersArrayList.get(x).getUserId());
				orderObject.put("productId", ordersArrayList.get(x).getProductId());
				orderObject.put("quantity", ordersArrayList.get(x).getQuantity());
				
				ordersJSONArray.put(orderObject);
			}
			
			// return success data
			return Response.status(200).entity(ordersJSONArray.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	// get orders by user id
	@Path("getordersbyuserid")
	@GET
	@Produces("application/json")
	public Response getOrdersByUserId(@QueryParam("userId") int userId) {
		
		// create JSONArray
		JSONArray ordersJSONArray = new JSONArray();
		// declare ArrayList<Order> variable
		ArrayList<Order> ordersArrayList;
		
		try {
			// get ArrayList result from utils and store it into ordersArrayList
			ordersArrayList = OrderUtils.getOrdersByUserId(userId);
			
			// convert result in ordersArrayList to JSONObject and store in ordersJSONArray
			for (int x=0; x<ordersArrayList.size(); x++) {
				JSONObject orderObject = new JSONObject();
				
				orderObject.put("userId", ordersArrayList.get(x).getUserId());
				orderObject.put("productId", ordersArrayList.get(x).getProductId());
				orderObject.put("quantity", ordersArrayList.get(x).getQuantity());
				
				ordersJSONArray.put(orderObject);
			}
			
			// return success data
			return Response.status(200).entity(ordersJSONArray.toString()).build();
			
		} catch (Exception e) {
			// return error data	
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}
