package com.assignments.assignment3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.utils.DatabaseConnection;
import models.GuestBookEntry;

@WebServlet(description = "GuestBook Application using SQL and MVC Architecture.", urlPatterns= {"/Guestbook/GuestBookMVC"})
public class GuestBookMVC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the query string, if search term is passed in the parameters.
		String query = request.getParameter("query");

		ArrayList<GuestBookEntry> guestbook = new ArrayList<>();

		try {
			Connection c = (new DatabaseConnection()).getServerSQLConnection();
			Statement stmt = c.createStatement();
			String queryString = "";

			if ( query == null || query.length() == 0 ) {
				queryString = "SELECT * FROM Guestbook";
			} else if ( query.length() > 0 ) {
				String[] queryTerms = query.trim().split(" ");
				String whereClause = "";
				for ( String queryTerm : queryTerms )
					whereClause += "name LIKE \'%" + queryTerm + "%\' OR message LIKE \'%" + queryTerm + "%\' OR ";
				queryString = "SELECT * FROM Guestbook WHERE " + whereClause.trim().substring(0, whereClause.length()-3);
			}

			ResultSet rs = stmt.executeQuery( queryString);	
			while( rs.next() ) {
				GuestBookEntry gbe = new GuestBookEntry(rs.getInt("id"), rs.getString("name"), rs.getString("message"), rs.getDate("date"));
				guestbook.add(gbe);
			}

			rs.close();
			stmt.close();
			c.close();
		} catch ( SQLException e ) {
			throw new ServletException( e );
		} 

		request.setAttribute("guestbook", guestbook);
		request.getRequestDispatcher("/WEB-INF/Guestbook/GuestbookMVC.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}