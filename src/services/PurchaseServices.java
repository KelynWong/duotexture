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
	
	@Path("getpurchases")
	@GET
	@Produces("application/json")
	public Response getPurchases() {
		
		JSONArray purchasesJSONArray = new JSONArray();
		ArrayList<Purchase> purchaseArrayList;
		
		try {
			purchaseArrayList = PurchaseUtils.getPurchases();
			for (int x=0; x<purchaseArrayList.size(); x++) {
				JSONObject purchaseObject = new JSONObject();
				
				purchaseObject.put("userId", purchaseArrayList.get(x).getUserId());
				purchaseObject.put("productId", purchaseArrayList.get(x).getProductId());
				purchaseObject.put("quantity", purchaseArrayList.get(x).getQuantity());
				
				purchasesJSONArray.put(purchaseObject);
			}
			
			return Response.status(200).entity(purchasesJSONArray.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	@Path("getpurchasesbyuserid")
	@GET
	@Produces("application/json")
	public Response getPurchasesByUserId(@QueryParam("userId") int userId) {

		JSONArray purchasesJSONArray = new JSONArray();
		ArrayList<Purchase> purchaseArrayList;
		
		try {
			purchaseArrayList = PurchaseUtils.getPurchasesByUserId(userId);
			for (int x=0; x<purchaseArrayList.size(); x++) {
				JSONObject purchaseObject = new JSONObject();
				
				purchaseObject.put("userId", purchaseArrayList.get(x).getUserId());
				purchaseObject.put("productId", purchaseArrayList.get(x).getProductId());
				purchaseObject.put("quantity", purchaseArrayList.get(x).getQuantity());
				
				purchasesJSONArray.put(purchaseObject);
			}
			
			return Response.status(200).entity(purchasesJSONArray.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}
