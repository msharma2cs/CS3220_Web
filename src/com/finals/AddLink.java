package com.finals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.utils.DatabaseConnection;

@WebServlet("/AddLink")
public class AddLink extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String link = request.getParameter("link");

		// Validate the title or link input.
		if ( title == null || title.equals("") || link == null || link.equals("") ) {
			getServletContext().setAttribute("incompleteForm", "");
		} else {
			try {
				Connection c = (new DatabaseConnection()).getServerSQLConnection();
				Statement stmt = c.createStatement();
				String insertQuery = "INSERT INTO FinalArticles ( title, link) VALUES (\'" + title + "\', \'" + link + "\');";
				stmt.executeUpdate(insertQuery);
	
				stmt.close();
				c.close();
			} catch( SQLException e ) {
				throw new ServletException( e );
			}
		}
		response.sendRedirect("/cs3220stu54/List");
	}

}