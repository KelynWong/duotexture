package services;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import javabeans.Member;
import javabeans.Order;
import utils.MemberUtils;
import utils.OrderUtils;

@Path("memberservices")
public class MemberServices {
	
	// get all members
	@Path("getmembers")
	@GET
	@Produces("application/json")
	public Response getMembers() {
		
		// create JSONArray
		JSONArray membersJSONArray = new JSONArray();
		
		// declare ArrayList<Member> variable
		ArrayList<Member> membersArrayList;
		
		try {
			// get ArrayList result from utils and store it into membersArrayList
			membersArrayList = MemberUtils.getMembers();
			
			// convert result in membersArrayList to JSONObject and store in membersJSONArray
			for (int x=0; x<membersArrayList.size(); x++) {
				JSONObject memberObject = new JSONObject();
				
				memberObject.put("userId", membersArrayList.get(x).getUserId());
				memberObject.put("first_name", membersArrayList.get(x).getFirstName());
				memberObject.put("last_name", membersArrayList.get(x).getLastName());
				memberObject.put("country", membersArrayList.get(x).getCountry());
				memberObject.put("address", membersArrayList.get(x).getAddress());
				memberObject.put("postal_code", membersArrayList.get(x).getPostalCode());
				
				membersJSONArray.put(memberObject);
			}
			
			// return success data
			return Response.status(200).entity(membersJSONArray.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	// get member by id
	@Path("getmemberbyid")
	@GET
	@Produces("application/json")
	public Response getMemberById(@QueryParam("userId") int userId) {
		
		try {
			// get Member result from utils and store it in memberBean
			Member memberBean = MemberUtils.getMemberByUserId(userId);
			
			// store inside JSONObject
			JSONObject memberObject = new JSONObject();

			memberObject.put("userId", memberBean.getUserId());
			memberObject.put("first_name", memberBean.getFirstName());
			memberObject.put("last_name", memberBean.getLastName());
			memberObject.put("country", memberBean.getCountry());
			memberObject.put("address", memberBean.getAddress());
			memberObject.put("postal_code", memberBean.getPostalCode());
			
			// return success data
			return Response.status(200).entity(memberObject.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	// get member by user id
	@Path("getmemberbyuserid")
	@GET
	@Produces("application/json")
	public Response getMemberByUserId(@QueryParam("userId") int userId) {
		
		try {
			// get Member result from utils and store it into memberBean
			Member memberBean = MemberUtils.getMemberByUserId(userId);
			
			// store inside JSONObject
			JSONObject memberObject = new JSONObject();

			memberObject.put("userId", memberBean.getUserId());
			memberObject.put("first_name", memberBean.getFirstName());
			memberObject.put("last_name", memberBean.getLastName());
			memberObject.put("country", memberBean.getCountry());
			memberObject.put("address", memberBean.getAddress());
			memberObject.put("postal_code", memberBean.getPostalCode());
			
			// return success data
			return Response.status(200).entity(memberObject.toString()).build();
			
		} catch (Exception e) {
			// return error data
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}
