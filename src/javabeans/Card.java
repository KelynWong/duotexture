package javabeans;

public class Card {
	private int cardId;
	private int userId;
	private String cardOwner;
	private int cardNumber;
	private int expiryMonth;
	private int expiryYear;
	private int cvv;
	
	// default constructor
	public Card() {
		
	}
	
	// constructor overload
	public Card(int cardId, int userId, String cardOwner, int cardNumber, int expiryMonth, int expiryYear, int cvv) {
		this.cardId = cardId;
		this.userId = userId;
		this.cardOwner = cardOwner;
		this.cardNumber = cardNumber;
		this.expiryMonth = expiryMonth;
		this.expiryYear = expiryYear;
		this.cvv = cvv;
	}

	// get card id
	public int getCardId() {
		return cardId;
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
	public int getCardNumber() {
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

	// set card id
	public void setCardId(int cardId) {
		this.cardId = cardId;
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
	public void setCardNumber(int cardNumber) {
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
