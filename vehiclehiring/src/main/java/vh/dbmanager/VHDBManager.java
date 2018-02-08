package vh.dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import vhexception.VHDBConnectionException;

public class VHDBManager {
	
	private static final String url = "jdbc:mysql://localhost:3306/vehiclehiring";
	private static final String userName = "root";
	private static final String password = "root";
	
	public static Connection getConnection(Connection con) throws VHDBConnectionException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, password);
		} catch(SQLException | ClassNotFoundException exception) {
			throw new VHDBConnectionException("Error while connecting to database", exception);
		}
		
		return con;
	}
	
	public static void closeConnection(Connection con) {
		try {
			if(!(con == null)) {
				con.close();
			}
		}
		catch(SQLException exception) {
			System.out.println("Could not close connection ");
			exception.printStackTrace();
		}
	}
}
