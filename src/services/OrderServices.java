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
	
	@Path("getorders")
	@GET
	@Produces("application/json")
	public Response getOrders() {
		
		JSONArray ordersJSONArray = new JSONArray();
		ArrayList<Order> ordersArrayList;
		
		try {
			ordersArrayList = OrderUtils.getOrders();
			for (int x=0; x<ordersArrayList.size(); x++) {
				JSONObject orderObject = new JSONObject();
				
				orderObject.put("userId", ordersArrayList.get(x).getUserId());
				orderObject.put("productId", ordersArrayList.get(x).getProductId());
				orderObject.put("quantity", ordersArrayList.get(x).getQuantity());
				
				ordersJSONArray.put(orderObject);
			}
			
			return Response.status(200).entity(ordersJSONArray.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	@Path("getordersbyuserid")
	@GET
	@Produces("application/json")
	public Response getOrdersByUserId(@QueryParam("userId") int userId) {
		
		JSONArray ordersJSONArray = new JSONArray();
		ArrayList<Order> ordersArrayList;
		
		try {
			ordersArrayList = OrderUtils.getOrdersByUserId(userId);
			for (int x=0; x<ordersArrayList.size(); x++) {
				JSONObject orderObject = new JSONObject();
				
				orderObject.put("userId", ordersArrayList.get(x).getUserId());
				orderObject.put("productId", ordersArrayList.get(x).getProductId());
				orderObject.put("quantity", ordersArrayList.get(x).getQuantity());
				
				ordersJSONArray.put(orderObject);
			}
			
			return Response.status(200).entity(ordersJSONArray.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}
