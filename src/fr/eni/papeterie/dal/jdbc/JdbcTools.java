package fr.eni.papeterie.dal.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import fr.eni.papeterie.dal.Settings;

public class JdbcTools {
	private static Connection con;
	public static Connection getConnection() throws SQLException {
		if(con==null) {
			con=DriverManager.getConnection(Settings.getProperty("urldb"),Settings.getProperty("userdb"),Settings.getProperty("passworddb"));
			
		}return con;
	}
	public static Connection closeConnection()throws SQLException {
		if(con!=null) {
			con.close();
			con=null;
		}
		return con;
	}

}
