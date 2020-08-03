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

@Path("userservices")
public class UserServices {
	
	@Path("getusers")
	@GET
	@Produces("application/json")
	public Response getUsers() {
		
		JSONArray usersJSONArray = new JSONArray();
		ArrayList<User> usersArrayList;
		
		try {
			usersArrayList = UserUtils.getUsers();
			for (int x=0; x<usersArrayList.size(); x++) {
				JSONObject userObject = new JSONObject();
				
				userObject.put("userId", usersArrayList.get(x).getUserId());
				userObject.put("email", usersArrayList.get(x).getEmail());
				userObject.put("username", usersArrayList.get(x).getUsername());
				userObject.put("password", usersArrayList.get(x).getPassword());
				userObject.put("userRole", usersArrayList.get(x).getUserRole());
				
				usersJSONArray.put(userObject);
			}
			
			return Response.status(200).entity(usersJSONArray.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	@Path("getuserbyid")
	@GET
	@Produces("application/json")
	public Response getUserById(@QueryParam("userId") int userId) {
		
		try {
			User userBean = UserUtils.getUserById(userId);
			
			JSONObject userObject = new JSONObject();
			
			userObject.put("userId", userBean.getUserId());
			userObject.put("email", userBean.getEmail());
			userObject.put("username", userBean.getUsername());
			userObject.put("password", userBean.getPassword());
			userObject.put("userRole", userBean.getUserRole());
			
			return Response.status(200).entity(userBean.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}
