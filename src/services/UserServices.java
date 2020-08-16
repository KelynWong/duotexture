package services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import javabeans.User;
import utils.UserUtils;

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

@Path("userservices")
public class UserServices {
	
	@Path("getusers")
	@GET
	@Produces("application/json")
	public Response getUsers() {
		
		// create JSONArray
		JSONArray usersJSONArray = new JSONArray();
		
		// declare ArrayList<User> variable
		ArrayList<User> usersArrayList;
		
		try {
			// get ArrayList result from utils and store it into usersArrayList
			usersArrayList = UserUtils.getUsers();
			
			// convert result in usersArrayList to JSONObject and store in usersJSONArray
			for (int x=0; x<usersArrayList.size(); x++) {
				JSONObject userObject = new JSONObject();
				
				userObject.put("userId", usersArrayList.get(x).getUserId());
				userObject.put("email", usersArrayList.get(x).getEmail());
				userObject.put("username", usersArrayList.get(x).getUsername());
				userObject.put("password", usersArrayList.get(x).getPassword());
				userObject.put("userRole", usersArrayList.get(x).getUserRole());
				
				usersJSONArray.put(userObject);
			}
			
			// return success data
			return Response.status(200).entity(usersJSONArray.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	// get user by id
	@Path("getuserbyid")
	@GET
	@Produces("application/json")
	public Response getUserById(@QueryParam("userId") int userId) {
		
		try {
			// get User result from utils and store it into userBean
			User userBean = UserUtils.getUserById(userId);
			
			// store inside JSONObject
			JSONObject userObject = new JSONObject();
			
			userObject.put("userId", userBean.getUserId());
			userObject.put("email", userBean.getEmail());
			userObject.put("username", userBean.getUsername());
			userObject.put("password", userBean.getPassword());
			userObject.put("userRole", userBean.getUserRole());
			
			// return success data
			return Response.status(200).entity(userObject.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}
