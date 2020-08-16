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

/**
 * 
 * Class: DIT/FT/2B/21
 * Group: 1
 * 
 * Name: LEE ZONG XUN RENFRED
 * Admin Number: P1935392 
 * 
 * Name: WONG EN TING KELYN
 * Admin Number: P1935800
 * 
 */

@Path("cardservices")
public class CardServices {
	
	// get all cards
	@Path("getcards")
	@GET
	@Produces("application/json")
	public Response getCards() {
		
		// create JSONArray
		JSONArray cardsJSONArray = new JSONArray();
		
		// declare ArrayList<Card> variable
		ArrayList<Card> cardsArrayList;
		
		try {
			// get ArrayList result from utils and store it into cardsArrayList
			cardsArrayList = CardUtils.getCards();
			
			// convert result in cardsArrayList to JSONObject and store in cardsJSONArray
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
			
			// return success data
			return Response.status(200).entity(cardsJSONArray.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	// get card by user id
	@Path("getcardbyuserid")
	@GET
	@Produces("application/json")
	public Response getCardByUserId(@QueryParam("userId") int userId) {
		
		try {
			// get Card result from utils and store it into cardBean
			Card cardBean = CardUtils.getCardByUserId(userId);
			
			// store inside JSONObject
			JSONObject cardObject = new JSONObject();

			cardObject.put("userId", cardBean.getUserId());
			cardObject.put("cardOwner", cardBean.getCardOwner());
			cardObject.put("cardNumber", cardBean.getCardNumber());
			cardObject.put("expiryMonth", cardBean.getExpiryMonth());
			cardObject.put("expiryYear", cardBean.getExpiryYear());
			cardObject.put("cvv", cardBean.getCvv());
			
			// return success data
			return Response.status(200).entity(cardObject.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}
