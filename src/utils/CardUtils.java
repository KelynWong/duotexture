package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connection.Database;
import javabeans.Card;

public class CardUtils {
	
	// get all cards
	public static ArrayList<Card> getCards () throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// statement, get all cards query and result
		Statement stmt = conn.createStatement();
		String getCardsQuery = "SELECT * FROM duotexture.cards;";
		ResultSet getCardsResult = stmt.executeQuery(getCardsQuery);
		
		// create new ArrayList of card
		ArrayList<Card> cardsArrayList = new ArrayList<Card>();
		
		// loop if there are new row
		while(getCardsResult.next()) {
			// create an instance of card
			Card cardBean = new Card();
			
			cardBean.setUserId(getCardsResult.getInt("userId"));
			cardBean.setCardOwner(getCardsResult.getString("cardOwner"));
			cardBean.setCardNumber(getCardsResult.getString("cardNumber"));
			cardBean.setExpiryMonth(getCardsResult.getInt("expiryMonth"));
			cardBean.setExpiryYear(getCardsResult.getInt("expiryYear"));
			cardBean.setCvv(getCardsResult.getInt("cvv"));
			
			// add cardBean to cardsArrayList
			cardsArrayList.add(cardBean);
		}
		
		// close connection
		conn.close();
		return cardsArrayList;
	}
	
	// get card by user id
	public static Card getCardByUserId (int userId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, get a card by user id query and result
		String getCardByUserIdQuery = "SELECT * FROM duotexture.cards WHERE userId=?;";
		PreparedStatement pstmt = conn.prepareStatement(getCardByUserIdQuery);
		pstmt.setInt(1,  userId);
		ResultSet getCardByUserIdResult = pstmt.executeQuery();
		
		// create an instance of card
		Card cardBean = new Card();
		
		// if there is a new row
		if(getCardByUserIdResult.next()) {
			cardBean.setUserId(getCardByUserIdResult.getInt("userId"));
			cardBean.setCardOwner(getCardByUserIdResult.getString("cardOwner"));
			cardBean.setCardNumber(getCardByUserIdResult.getString("cardNumber"));
			cardBean.setExpiryMonth(getCardByUserIdResult.getInt("expiryMonth"));
			cardBean.setExpiryYear(getCardByUserIdResult.getInt("expiryYear"));
			cardBean.setCvv(getCardByUserIdResult.getInt("cvv"));
		}
		
		// close connection
		conn.close();
		return cardBean;
	}
	
	// add card
	public static int insertCard (int userId, String cardOwner, String cardNumber, int expiryMonth, int expiryYear, int cvv) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, add card query and result
		String addCardQuery = "INSERT INTO duotexture.cards(`userId`, `cardOwner`, `cardNumber`, `expiryMonth`, `expiryYear`, `cvv`) VALUES(?, ?, ?, ?, ?, ?);";
		PreparedStatement pstmt = conn.prepareStatement(addCardQuery);
		pstmt.setInt(1, userId);
	    pstmt.setString(2, cardOwner);
	    pstmt.setString(3, cardNumber);
	    pstmt.setInt(4, expiryMonth);
	    pstmt.setInt(5, expiryYear);
	    pstmt.setInt(6, cvv);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// edit card
	public static int editCard (int userId, String cardOwner, int cardNumber, int expiryMonth, int expiryYear, int cvv) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();

		// prepared statement, edit card query and result
		String updateCardQuery = "UPDATE duotexture.cards SET cardOwner=?, cardNumber=?, expiryMonth=?, expiryYear=?, cvv=? WHERE userId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(updateCardQuery);
	    pstmt.setString(1, cardOwner);
	    pstmt.setInt(2, cardNumber);
	    pstmt.setInt(3, expiryMonth);
	    pstmt.setInt(4, expiryYear);
	    pstmt.setInt(5, cvv);
	    pstmt.setInt(6, userId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
	
	// delete card
	public static int deleteCard (int userId) throws SQLException, ClassNotFoundException {
		// connect to database
		Connection conn = Database.connectToDatabase();
		
		// prepared statement, delete card query and result
		String deleteCardQuery = "DELETE FROM duotexture.cards WHERE userId=?"; 
		PreparedStatement pstmt = conn.prepareStatement(deleteCardQuery);
	    pstmt.setInt(1, userId);
		int count = pstmt.executeUpdate(); 
		
		// close connection
		conn.close();
		return count;
	}
}
