package services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import javabeans.Purchase;
import utils.PurchaseUtils;

@Path("purchaseservices")
public class PurchaseServices {
	
	// get all purchases
	@Path("getpurchases")
	@GET
	@Produces("application/json")
	public Response getPurchases() {
		
		// create JSONArray
		JSONArray purchasesJSONArray = new JSONArray();
		
		// declare ArrayList<Purchase> variable
		ArrayList<Purchase> purchasesArrayList;
		
		try {
			// get ArrayList result from utils and store it into purchasesArrayList
			purchasesArrayList = PurchaseUtils.getPurchases();
			
			// convert result in purchasesArrayList to JSONObject and store in purchasesJSONArray
			for (int x=0; x<purchasesArrayList.size(); x++) {
				JSONObject purchaseObject = new JSONObject();
				
				purchaseObject.put("userId", purchasesArrayList.get(x).getUserId());
				purchaseObject.put("productId", purchasesArrayList.get(x).getProductId());
				purchaseObject.put("quantity", purchasesArrayList.get(x).getQuantity());
				
				purchasesJSONArray.put(purchaseObject);
			}
			
			// return success data
			return Response.status(200).entity(purchasesJSONArray.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	@Path("getpurchasesbyuserid")
	@GET
	@Produces("application/json")
	public Response getPurchasesByUserId(@QueryParam("userId") int userId) {

		// create JSONArray
		JSONArray purchasesJSONArray = new JSONArray();
		
		// declare ArrayList<Purchase> variable
		ArrayList<Purchase> purchasesArrayList;
		
		try {
			// get ArrayList result from utils and store it into purchasesArrayList
			purchasesArrayList = PurchaseUtils.getPurchasesByUserId(userId);
			
			// convert result in purchasesArrayList to JSONObject and store in purchasesJSONArray
			for (int x=0; x<purchasesArrayList.size(); x++) {
				JSONObject purchaseObject = new JSONObject();
				
				purchaseObject.put("userId", purchasesArrayList.get(x).getUserId());
				purchaseObject.put("productId", purchasesArrayList.get(x).getProductId());
				purchaseObject.put("quantity", purchasesArrayList.get(x).getQuantity());
				
				purchasesJSONArray.put(purchaseObject);
			}
			
			// return success data
			return Response.status(200).entity(purchasesJSONArray.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}
