package projekatWeb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionMenager {
	
	private static final String DATABASE = "localhost:3306/webproject";
	private static final String USER_NAME = "root";
	private static final String PASSWORD = "database";	

	private static Connection connection;

	public static void open() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://" + DATABASE + "?useSSL=false", USER_NAME, PASSWORD);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}

	public static void close() {
		try {
			connection.close();
		} catch (SQLException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}

}
