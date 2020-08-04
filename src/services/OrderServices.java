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
	
	@Path("getorderbyuserid")
	@GET
	@Produces("application/json")
	public Response getOrderByUserId(@QueryParam("userId") int userId) {
		
		try {
			Order orderBean = OrderUtils.getOrderByUserId(userId);
			
			JSONObject orderObject = new JSONObject();

			orderObject.put("userId", orderBean.getUserId());
			orderObject.put("productId", orderBean.getProductId());
			orderObject.put("quantity", orderBean.getQuantity());
			
			return Response.status(200).entity(orderObject.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}