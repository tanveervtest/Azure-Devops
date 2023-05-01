package com.efuelsystems.reusemethods;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBConnection {

	private Connection conn = null;

	/**
	 * This method is singleton factory method
	 * 
	 * @return properties
	 */
	public Connection getConnection() {

		try {

			if (conn == null) {

				// Load the Properties
				Properties properties = PropertiesLoader.getConfigPropertiesLoader();

				String driver = properties.getProperty("jdbc.driver");
				String hostname = properties.getProperty("DB.hostname");
				String username = properties.getProperty("DB.user");
				String password = properties.getProperty("DB.password");
				// String DBservice = properties.getProperty("DB.servicename");
				String URL = "jdbc:sqlserver://" + hostname + ";user=" + username + ";password=" + password;

				Class.forName(driver);
				conn = DriverManager.getConnection(URL);
			}

		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}

		return conn;
	}

	public void closeConnection() {

		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	/**
	 * This method is used to test stand alone
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		Connection conn = null;
		Statement stmt = null;
		DBConnection DBConnObj = null;

		try {

			DBConnObj = new DBConnection();
			conn = DBConnObj.getConnection();
			stmt = conn.createStatement();
			String Sql = "";
			ResultSet rs = stmt.executeQuery(Sql);

			while (rs.next()) {
				System.out.println(rs.getString("co11") + " " + rs.getString("co12") + " " + rs.getString("co13") + " "
						+ rs.getString("co14"));
			}

			rs.close();
			stmt.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			DBConnObj.closeConnection();
		}// end

	}
}
