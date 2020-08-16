package publicservlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import javabeans.Member;
import javabeans.Product;
import javabeans.Purchase;
import javabeans.AnalyticsOrder;
import javabeans.Category;
import utils.AnalyticUtils;

/**
 * Servlet implementation class AnalyticsOrderServlet
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

@WebServlet("/analyticsorder")
public class AnalyticsOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /* Get Method */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// declare client
			Client client = ClientBuilder.newClient();
			
			// target java and parse in data - get categories for navigation
			WebTarget target = client.target("http://localhost:8080/ST0510-JAD-Assignment/api/")
					.path("categoryservices/getcategories");
			
			// declare media is an application/json
			Invocation.Builder invoBuilder = target.request(MediaType.APPLICATION_JSON);
			
			// get response
			Response resp = invoBuilder.get();
			
			// if response status is ok
			if(resp.getStatus() == Response.Status.OK.getStatusCode()) {
				// get results from java class
				JSONArray categoriesJSONArray = new JSONArray(resp.readEntity(new GenericType<String>() {}));
				ArrayList<Category> categoriesArrayList = new ArrayList<Category>();
				
				for(int x=0; x<categoriesJSONArray.length(); x++) {
					JSONObject categoryObject = (JSONObject) categoriesJSONArray.get(x);
					
					int categoryId = categoryObject.getInt("categoryId");
					String name = categoryObject.getString("name");
					String description = categoryObject.getString("description");
					String image = categoryObject.getString("image");
					
					Category category = new Category(categoryId, name, description, image);
					categoriesArrayList.add(category);
				}
				
				// store in request
				request.setAttribute("categoriesArrayList", categoriesArrayList);
				
				/* All Orders Analysis */
				
				// initialize variables
				Boolean orderMaxRecord = false;
				int orderPageNumber = Integer.parseInt(request.getParameter("orderPage"));
				String orderKeyword = request.getParameter("orderKeywordInput");
				String orderOrder = request.getParameter("orderOrderInput");
				
				// check keyword input
				if(orderKeyword == null) {
					orderKeyword = "";
				}
				
				// check order input
				if(orderOrder == null) {
					orderOrder = "ASCUserId";
				}
				
				// get orders (minus one as page started at 1)
				ArrayList<AnalyticsOrder> ordersArrayList = AnalyticUtils.getOrders(orderPageNumber-1, orderKeyword, orderOrder);
				
				// get next orders (last page validation)
				ArrayList<AnalyticsOrder> nextOrdersArrayList = AnalyticUtils.getOrders(orderPageNumber, orderKeyword, orderOrder);
				if(nextOrdersArrayList.size()==0) {
					orderMaxRecord = true;
				}
				
				// store in request
				request.setAttribute("ordersArrayList", ordersArrayList);
				request.setAttribute("orderKeywordInput", orderKeyword);
				request.setAttribute("orderOrderInput", orderOrder);
				
				/* Top 10 Analysis */
				
				// initialize variables
				Boolean top10MaxRecord = false;
				String top10Keyword = request.getParameter("top10KeywordInput");
				String top10Order = request.getParameter("top10OrderInput");
				
				// check keyword input
				if(top10Keyword == null) {
					top10Keyword = "";
				}
				
				// check order input
				if(top10Order == null) {
					top10Order = "DESCProfit";
				}
				
				// get orders (minus one as page started at 1)
				ArrayList<AnalyticsOrder> top10ArrayList = AnalyticUtils.getTop10Customers(top10Keyword, top10Order);
				
				// get next orders (last page validation)
				ArrayList<AnalyticsOrder> nextTop10ArrayList = AnalyticUtils.getTop10Customers(top10Keyword, top10Order);
				if(nextTop10ArrayList.size()==0) {
					top10MaxRecord = true;
				}
				
				// store in request
				request.setAttribute("top10ArrayList", top10ArrayList);
				request.setAttribute("top10KeywordInput", top10Keyword);
				request.setAttribute("top10OrderInput", top10Order);
				
				/* Purchases Log Analysis */
				
				// initialize variables
				Boolean purchaseLogMaxRecord = false;
				int purchaseLogPageNumber = Integer.parseInt(request.getParameter("purchaseLogPage"));
				String purchaseLogKeyword = request.getParameter("purchaseLogKeywordInput");
				String purchaseLogOrder = request.getParameter("purchaseLogOrderInput");
				
				// check keyword input
				if(purchaseLogKeyword == null) {
					purchaseLogKeyword = "";
				}
				
				// check order input
				if(purchaseLogOrder == null) {
					purchaseLogOrder = "ASCProdId";
				}
				
				// get orders (minus one as page started at 1)
				ArrayList<AnalyticsOrder> purchaseLogArrayList = AnalyticUtils.getPurchasesLog(purchaseLogPageNumber-1, orderKeyword, orderOrder);
				
				// get next orders (last page validation)
				ArrayList<AnalyticsOrder> nextPurchaseLogArrayList = AnalyticUtils.getPurchasesLog(purchaseLogPageNumber, orderKeyword, orderOrder);
				if(nextPurchaseLogArrayList.size()==0) {
					purchaseLogMaxRecord = true;
				}
				
				// store in request
				request.setAttribute("purchaseLogArrayList", purchaseLogArrayList);
				request.setAttribute("purchaseLogKeywordInput", purchaseLogKeyword);
				request.setAttribute("purchaseLogOrderInput", purchaseLogOrder);
				
				// forward request to jsp for display
				RequestDispatcher requestDispatcher;
				
				if(orderMaxRecord == false && top10MaxRecord == false && purchaseLogMaxRecord == false) { // none
					requestDispatcher = request.getServletContext().getRequestDispatcher("/Assignment/website/analytics_order.jsp?page="+orderPageNumber);
				} else if (orderMaxRecord == true && top10MaxRecord == false && purchaseLogMaxRecord == false){ // orderMaxRecord
					requestDispatcher = request.getServletContext().getRequestDispatcher("/Assignment/website/analytics_order.jsp?page="+orderPageNumber+"&orderMaxRecord=true");
				} else if (orderMaxRecord == false && top10MaxRecord == true && purchaseLogMaxRecord == false) { // top10MaxRecord
					requestDispatcher = request.getServletContext().getRequestDispatcher("/Assignment/website/analytics_order.jsp?page="+orderPageNumber+"&top10MaxRecord=true");
				} else if (orderMaxRecord == false && top10MaxRecord == false && purchaseLogMaxRecord == true){ // purchaseLogMaxRecord
					requestDispatcher = request.getServletContext().getRequestDispatcher("/Assignment/website/analytics_order.jsp?page="+orderPageNumber+"&purchaseLogMaxRecord=true");
				} else if (orderMaxRecord == true && top10MaxRecord == true && purchaseLogMaxRecord == false) { // order and top10
					requestDispatcher = request.getServletContext().getRequestDispatcher("/Assignment/website/analytics_order.jsp?page="+orderPageNumber+"&orderMaxRecord=true&top10MaxRecord=true");
				} else if (orderMaxRecord == false && top10MaxRecord == true && purchaseLogMaxRecord == true){ // top10 and purchase
					requestDispatcher = request.getServletContext().getRequestDispatcher("/Assignment/website/analytics_order.jsp?page="+orderPageNumber+"&top10MaxRecord=true&purchaseLogMaxRecord=true");
				} else { // order, top10 and purchase
					requestDispatcher = request.getServletContext().getRequestDispatcher("/Assignment/website/analytics_order.jsp?page="+orderPageNumber+"&orderMaxRecord=true&top10MaxRecord=true&purchaseLogMaxRecord=true");
				}
				
				requestDispatcher.forward(request, response);
			} else {
				System.out.println("(publicservlets/AnalyticsOrderServlet) Error: Response not ok. \n");
				response.sendRedirect(request.getContextPath() + "/index");
			}
			
		} catch (Exception e) {
			System.out.println("(publicservlets/AnalyticsOrderServlet) Error: " + e + "\n");
			response.sendRedirect(request.getContextPath() + "/index");
		}

	}
}
