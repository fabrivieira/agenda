package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// TODO: Auto-generated Javadoc
/**
 * The Class DAO.
 */
public class DAO {

	/** The driver. */
	private static String driver = "com.mysql.cj.jdbc.Driver";
	
	/** The url. */
	private static String url = "jdbc:mysql://127.0.0.1:3306/dbmysql?useTimezone=true&serverTimezone=UTC";
	
	/** The user. */
	private static String user = "root";
	
	/** The password. */
	private static String password = "root";
	
	/** The conn. */
	private static Connection conn = null;

	/**
	 * Conn.
	 *
	 * @return the connection
	 */
	public static Connection conn() {
		if (conn == null) {
			try {
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, password);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		System.out.println(conn);
		return conn;
	}
}
