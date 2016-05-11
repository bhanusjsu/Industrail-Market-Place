package im.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDao {
	static Connection con;
ConnectionDao(){
		
	}
	
	public static Connection connect() {
		try {
			DriverManager.registerDriver (new oracle.jdbc.driver.OracleDriver());
			con = DriverManager.getConnection("jdbc:oracle:thin:@apraveen.czwfqfibxu0r.us-west-2.rds.amazonaws.com:1521:apraveen","apraveen","apraveen");
			return con;
		} catch (SQLException e) {

		} catch (Exception e) {

		}
		return null;
	}

	public static void destroy(Connection con) {
		try {
			con.close();
		} catch (SQLException e) {

		}
	}
	
	public static void main(String[] args) {
		Connection con=ConnectionDao.connect();
	if(con!=null){
		System.out.println("connected succesfully");
	
	}
	else
		System.out.println("Not connected");
}
}