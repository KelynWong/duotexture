package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

	public static Connection connectToDatabase() throws SQLException, ClassNotFoundException {
		// connect to database
		Class.forName("com.mysql.jdbc.Driver");
		String connURL = "jdbc:mysql://localhost/jad?user=root&password=password&serverTimezone=UTC";
		Connection conn = DriverManager.getConnection(connURL);
		
		return conn;
	}
}
