package base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBmanager {
	public static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/challenge_db","sh228104","g5xug49v");
			System.out.println("DBconnected");
			return con;
		} catch (ClassNotFoundException e) {
			throw new IllegalMonitorStateException();
		} catch (SQLException e) {
			throw new IllegalMonitorStateException();
		}
	}
}
