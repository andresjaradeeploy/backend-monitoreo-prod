package com.monitor.bankendmonitoreoLinks.components.conector;
import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;


public class Conector {

	 private static final String JDBC_URL ="jdbc:mysql://localhost:3306/monitor-links?use_SSL=false";
	    private static final String JDBC_USER = "root";
	    private static final String JDBC_PASSWORD = "";
	    private static BasicDataSource datasource;

	    public static DataSource getDataSource() throws SQLException {
	        if (datasource == null) {
	            datasource = new BasicDataSource();
	            datasource.setUrl(JDBC_URL);
	            datasource.setUsername(JDBC_USER);
	            datasource.setPassword(JDBC_PASSWORD);
	            Driver driver = new com.mysql.cj.jdbc.Driver();
	            datasource.setDriver(driver);
	            datasource.setInitialSize(10);
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
