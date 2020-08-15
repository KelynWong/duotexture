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

public class Card {
	private int userId;
	private String cardOwner;
	private String cardNumber;
	private int expiryMonth;
	private int expiryYear;
	private int cvv;
	
	// default constructor
	public Card() {
		
	}
	
	// constructor overload
	public Card(int userId, String cardOwner, String cardNumber, int expiryMonth, int expiryYear, int cvv) {
		this.userId = userId;
		this.cardOwner = cardOwner;
		this.cardNumber = cardNumber;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.cvv = cvv;
	}

	// get user id
	public int getUserId() {
		return userId;
	}

	// get card owner
	public String getCardOwner() {
		return cardOwner;
	}

	// get card number
	public String getCardNumber() {
		return cardNumber;
	}

	// get card expiry month
	public int getExpiryMonth() {
		return expiryMonth;
	}

	// get card expiry year
	public int getExpiryYear() {
		return expiryYear;
	}

	// get card cvv
	public int getCvv() {
		return cvv;
	}

	// set user id
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	// set card owner
	public void setCardOwner(String cardOwner) {
		this.cardOwner = cardOwner;
	}
	
	// set card number
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	// set card expiry month
	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	
	// set card expiry year
	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}
	
	// set card cvv
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	
}
