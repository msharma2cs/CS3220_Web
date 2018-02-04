package com.assignments.assignment3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.jdbc.PreparedStatement;
import database.utils.DatabaseConnection;

@WebServlet(description = "GuestBook Application using SQL and MVC Architecture.", urlPatterns= {"/Guestbook/DeleteEntryMVC"})
public class DeleteEntryMVC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deleteId = request.getParameter("id");
		String deleteName = "";
		String deleteMessage = "";
		
		if ( deleteId == null || deleteId.length() == 0 )
			response.sendRedirect("GuestBookMVC");
		
		try {
			Connection c = (new DatabaseConnection()).getServerSQLConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Guestbook WHERE id=" + deleteId);	
			rs.next();
			deleteName = rs.getString("name");
			deleteMessage = rs.getString("message");
			stmt.close();
			c.close();
		} catch ( SQLException e ) {
			throw new ServletException( e );
		}
		
		request.setAttribute("deleteId", deleteId);
		request.setAttribute("deleteName", deleteName);
		request.setAttribute("deleteMessage", deleteMessage);
		request.getRequestDispatcher("/WEB-INF/Guestbook/DeleteentryMVC.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the ID of the entry to be deleted from parameters passed.
		String deleteId = request.getParameter("id");
		
		if ( deleteId != null && deleteId.trim().length() > 0 ) {
			try {
				Connection c = (new DatabaseConnection()).getServerSQLConnection();
				PreparedStatement editEntry = (PreparedStatement) c.prepareStatement("DELETE FROM Guestbook WHERE id=?");
				editEntry.setInt(1, Integer.parseInt(deleteId));
				editEntry.executeUpdate();
				editEntry.close();
				c.close();
			} catch( SQLException e ) {
				throw new ServletException( e );
			}

			// Redirect to GuesBook with modified entry.
			response.sendRedirect("GuestBookMVC");
			return;
		}
		doGet(request, response);
	}

}