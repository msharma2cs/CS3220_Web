package database.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.ServletException;

public class DatabaseConnection {

	private String localURL = "jdbc:mysql://localhost/cs3220stu54";
	private String serverURL = "jdbc:mysql://cs3.calstatela.edu/cs3220stu54";
	private String username = "cs3220stu54";
	private String password = "i!AWULT5";
	private Connection databaseConnection = null;

	private static void checkForClass() throws ServletException {
		try {
			Class.forName( "com.mysql.jdbc.Driver" );
		} catch( ClassNotFoundException e ) {
			throw new ServletException( e );
		}
	}

	public Connection getLocalhostSQLConnection() throws ServletException {
		DatabaseConnection.checkForClass();
		try {
			databaseConnection = DriverManager.getConnection( this.localURL, this.username, this.password );
		} catch ( Exception e ) {
			System.out.println(e.getMessage());
		}
		return this.databaseConnection;
	}

	public Connection getServerSQLConnection() throws ServletException {
		DatabaseConnection.checkForClass();
		try {
			databaseConnection = DriverManager.getConnection( this.serverURL, this.username, this.password );
		} catch ( Exception e ) {
			System.out.println(e.getMessage());
		}
		return this.databaseConnection;
	}
	
	public void closeSQLConnection( Connection c ) {
		try {
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}