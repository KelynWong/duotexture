package javabeans;

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

public class Dropdown {
	private String orderType;
	private String orderDisplay;
	
	// default constructor
	public Dropdown() {
		
	}
	
	// constructor overload
	public Dropdown(String orderType, String orderDisplay) {
		this.orderType = orderType;
		this.orderDisplay = orderDisplay;
	}

	// get order type
	public String getOrderType() {
		return orderType;
	}

	// get order display
	public String getOrderDisplay() {
		return orderDisplay;
	}

	// set order type
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	// set order display
	public void setOrderDisplay(String orderDisplay) {
		this.orderDisplay = orderDisplay;
	}
}
