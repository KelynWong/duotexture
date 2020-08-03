package services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import javabeans.Card;
import utils.CardUtils;

@Path("cardservices")
public class CardServices {
	
	@Path("getcards")
	@GET
	@Produces("application/json")
	public Response getCards() {
		
		JSONArray cardsJSONArray = new JSONArray();
		ArrayList<Card> cardsArrayList;
		
		try {
			cardsArrayList = CardUtils.getCards();
			for (int x=0; x<cardsArrayList.size(); x++) {
				JSONObject cardObject = new JSONObject();
				
				cardObject.put("userId", cardsArrayList.get(x).getUserId());
				cardObject.put("cardOwner", cardsArrayList.get(x).getCardOwner());
				cardObject.put("cardNumber", cardsArrayList.get(x).getCardNumber());
				cardObject.put("expiryMonth", cardsArrayList.get(x).getExpiryMonth());
				cardObject.put("expiryYear", cardsArrayList.get(x).getExpiryYear());
				cardObject.put("cvv", cardsArrayList.get(x).getCvv());
				
				cardsJSONArray.put(cardObject);
			}
			
			return Response.status(200).entity(cardsJSONArray.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	@Path("getcardbyuserid")
	@GET
	@Produces("application/json")
	public Response getCardByUserId(@QueryParam("userId") int userId) {
		
		try {
			Card cardBean = CardUtils.getCardByUserId(userId);
			
			JSONObject cardObject = new JSONObject();

			cardObject.put("userId", cardBean.getUserId());
			cardObject.put("cardOwner", cardBean.getCardOwner());
			cardObject.put("cardNumber", cardBean.getCardNumber());
			cardObject.put("expiryMonth", cardBean.getExpiryMonth());
			cardObject.put("expiryYear", cardBean.getExpiryYear());
			cardObject.put("cvv", cardBean.getCvv());
			
			return Response.status(200).entity(cardBean.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}
