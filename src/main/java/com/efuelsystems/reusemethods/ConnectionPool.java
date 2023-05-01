package com.efuelsystems.reusemethods;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.mchange.v2.c3p0.ComboPooledDataSource;


public class ConnectionPool {
	private ComboPooledDataSource cpds;
	private static ConnectionPool datasource;

	/**
	 * Constructor Method
	 * 
	 * @throws IOException
	 * @throws Exception
	 */
	private ConnectionPool() throws IOException, Exception {

		// Load the Properties
		Properties properties = PropertiesLoader.getConfigPropertiesLoader();

		String driver = properties.getProperty("jdbc.driver");
		String hostname = properties.getProperty("DB.hostname");
		String username = properties.getProperty("DB.user");
		String password = properties.getProperty("DB.password");
		String URL = "jdbc:sqlserver://" + hostname;

		// Configure DB properties
		cpds = new ComboPooledDataSource();
		cpds.setJdbcUrl(URL);
		cpds.setUser(username);
		cpds.setPassword(password);
		cpds.setDriverClass(driver);
		cpds.setInitialPoolSize(10);
		cpds.setAcquireIncrement(2);
		cpds.setMaxPoolSize(25);
		cpds.setMinPoolSize(6);
		cpds.setMaxStatements(10);

	}

	/**
	 * C3P0ConnectionPoolDataSource singleton.
	 * 
	 * @return
	 * @throws IOException
	 * @throws Exception
	 */
	public static ConnectionPool getInstance() throws IOException, Exception {
		if (datasource == null) {
			datasource = new ConnectionPool();
			return datasource;
		} else {
			return datasource;
		}
	}

	/**
	 * TO get the connection from Connection Pool
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return this.cpds.getConnection();
	}
	
	/**
	 * 
	 * @param conn
	 */
	public void closeConnection(Connection conn) {

		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	/**
	 * Test method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection = null;
		try {
			// Get Connection from the Connection Pool
			connection = ConnectionPool.getInstance().getConnection();
			System.out.println(">>> Connection " + connection);
			System.out.println(">>> Connection Opened ? : " + connection.isClosed());

			// Query Here
			Statement stmt = connection.createStatement();
			String Sql = "---------------";
			ResultSet rs = stmt.executeQuery(Sql);

			while (rs.next()) {
				System.out.println(rs.getString("column1") + " " + rs.getString("column2") + " " + rs.getString("column3") + " "
						+ rs.getString("column4"));
			}

			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				// Close the connection here - Makes connection free and pushes
				// to the Connection Pool.
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}
}
