package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_Connection {

	private final static String URL = "jdbc:mysql://localhost:3306/vehicles";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "Jokers#123";
	private static Connection connection;
	private static DB_Connection instance;
	
	private DB_Connection(Connection connection) {
		DB_Connection.connection = connection;
	}
	
	public static Connection getConnection() {
		if(instance == null) {
			try {
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
				instance = new DB_Connection(connection);
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return DB_Connection.connection;
	}
}
