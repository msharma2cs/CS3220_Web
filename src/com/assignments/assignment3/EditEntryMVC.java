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

@WebServlet(description = "GuestBook Application using SQL and MVC Architecture.", urlPatterns= {"/Guestbook/EditEntryMVC"})
public class EditEntryMVC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String editId = request.getParameter("id");
		String editName = "";
		String editMessage = "";

		try {
			Connection c = (new DatabaseConnection()).getServerSQLConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM Guestbook WHERE id=" + editId);	
			rs.next();
			editName = rs.getString("name");
			editMessage = rs.getString("message");
			rs.close();
			stmt.close();
			c.close();
		} catch ( SQLException e ) {
			throw new ServletException( e );
		}

		request.setAttribute("id", editId);
		request.setAttribute("name", editName);
		request.setAttribute("message", editMessage);
		request.getRequestDispatcher("/WEB-INF/Guestbook/EditentryMVC.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get parameters passed from form of doGet method of EditEntry.
		String editId = request.getParameter("id");
		String editName = request.getParameter("name");
		String editMessage = request.getParameter("message");

		if ( editId != null && editId.trim().length() > 0 && editName != null && editName.trim().length() > 0 && editMessage != null && editMessage.trim().length() > 0 ) {
			try {
				Connection c = (new DatabaseConnection()).getServerSQLConnection();
				PreparedStatement editEntry = (PreparedStatement) c.prepareStatement("UPDATE Guestbook SET name=?, message=? WHERE id=?");
				editEntry.setString(1, editName);
				editEntry.setString(2, editMessage);
				editEntry.setInt(3, Integer.parseInt(editId));
				editEntry.executeUpdate();
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