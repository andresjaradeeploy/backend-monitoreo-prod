package com.monitor.bankendmonitoreoLinks.components.conector;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;


public class Conector {

	/*
	 * private static final String JDBC_URL
	 * ="jdbc:mysql://localhost:3306/monitor-links?use_SSL=false"; private static
	 * final String JDBC_USER = "root"; private static final String JDBC_PASSWORD =
	 * "";
	 */
	
	
	//Postgres produccion

  private static final String JDBC_URL = "jdbc:postgresql://ec2-52-200-138-5.compute-1.amazonaws.com:5432/d7538trnteas4q?use_SSL=false&serverTimezone=America/Bogota";
	private static final String JDBC_USER = "ue09i1d1nnub2a";
	private static final String JDBC_PASSWORD = "p99267a8db466a7b66b5912ebcd3f69648e65ce082bda9d8ab1691eae82b7873e";

	
	//Postgres test
		
	
	/*private static final String JDBC_URL ="jdbc:postgresql://localhost:5432/test-links?use_SSL=false&serverTimezone=America/Bogota";
	private static final String JDBC_USER="postgres";
	private static final String JDBC_PASSWORD="alissa1995*";*/
	private static BasicDataSource datasource;

	public static DataSource getDataSource() throws SQLException {
		if (datasource == null) {
			datasource = new BasicDataSource();
			datasource.setUrl(JDBC_URL);
			datasource.setUsername(JDBC_USER);
			datasource.setPassword(JDBC_PASSWORD);
			//Driver driver = new com.mysql.cj.jdbc.Driver();
			Driver driver= new org.postgresql.Driver();
			datasource.setDriver(driver);
			datasource.setInitialSize(50);
		}

		return datasource;

	}

	public static Connection getConnection() throws SQLException {
		return getDataSource().getConnection();
	}

	public static void close(ResultSet rs) {
		try {
			rs.close();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
			
		}
	}

	public static void close(PreparedStatement stmt) {
		try {
			stmt.close();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
	}

	public static void close(Connection conn) {
		try {
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace(System.out);
		}
	}
}
