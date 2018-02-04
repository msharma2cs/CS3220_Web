package com.classwork.lab10;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.utils.DatabaseConnection;

@WebServlet("/CS3HelloJDBC")
public class CS3SQL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print( "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.0" );
		out.print( "Transitional//EN\">\n" );
		out.print( "<html><head><title>CS3:JDBC</title></head>\n<body>" );

		try {
			Connection c = (new DatabaseConnection()).getServerSQLConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "select * from items" );

			while( rs.next() ) {
				out.println( rs.getString( "name" ) );
				out.println( rs.getString( "price" ) );
				out.println( rs.getFloat( "quantity" ) );
				out.println( "<br>" );
			}
			c.close();
		} catch( SQLException e ) {
			throw new ServletException( e );
		}
		out.print( "</body></html>" );
	}

}