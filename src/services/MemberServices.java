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
	
	@Path("getmembers")
	@GET
	@Produces("application/json")
	public Response getMembers() {
		
		JSONArray membersJSONArray = new JSONArray();
		ArrayList<Member> membersArrayList;
		
		try {
			membersArrayList = MemberUtils.getMembers();
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
			
			return Response.status(200).entity(membersJSONArray.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	@Path("getmemberbyid")
	@GET
	@Produces("application/json")
	public Response getMemberById(@QueryParam("userId") int userId) {
		
		try {
			Member memberBean = MemberUtils.getMemberByUserId(userId);
			
			JSONObject memberObject = new JSONObject();

			memberObject.put("userId", memberBean.getUserId());
			memberObject.put("first_name", memberBean.getFirstName());
			memberObject.put("last_name", memberBean.getLastName());
			memberObject.put("country", memberBean.getCountry());
			memberObject.put("address", memberBean.getAddress());
			memberObject.put("postal_code", memberBean.getPostalCode());
			
			return Response.status(200).entity(memberObject.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
	
	@Path("getmemberbyuserid")
	@GET
	@Produces("application/json")
	public Response getMemberByUserId(@QueryParam("userId") int userId) {
		
		try {
			Member memberBean = MemberUtils.getMemberById(userId);
			
			JSONObject memberObject = new JSONObject();

			memberObject.put("userId", memberBean.getUserId());
			memberObject.put("first_name", memberBean.getFirstName());
			memberObject.put("last_name", memberBean.getLastName());
			memberObject.put("country", memberBean.getCountry());
			memberObject.put("address", memberBean.getAddress());
			memberObject.put("postal_code", memberBean.getPostalCode());
			
			return Response.status(200).entity(memberObject.toString()).build();
			
		} catch (Exception e) {
			return Response.status(200).entity("Error occurred.").build();
		}
	}
}
