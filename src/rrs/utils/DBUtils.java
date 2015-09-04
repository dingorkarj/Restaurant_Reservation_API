package rrs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

	private final static String DB_URL = "jdbc:mysql://localhost:3306/rrs";
	private final static String USERNAME = "root";
	private final static String PASSWORD = "root";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("MySQL JDBC has been loaded.");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERROR: Could not find MySQL JDBC driver!");
		}
	}

	public static Connection startConnection() {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("Database connection is succesful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ERROR: Could not connect to Database!");
		}
		return connection;
	}

	public static void closeConnection(Connection connection, PreparedStatement ps, ResultSet rs) {

		try {
			if (connection != null) {
				connection.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
