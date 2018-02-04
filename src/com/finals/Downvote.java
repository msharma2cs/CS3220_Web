package com.finals;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.jdbc.PreparedStatement;
import database.utils.DatabaseConnection;

@WebServlet("/Downvote")
public class Downvote extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		try {
			Connection c = (new DatabaseConnection()).getServerSQLConnection();
			PreparedStatement decreaseVote = (PreparedStatement) c.prepareStatement("UPDATE FinalArticles SET votes=votes-1 WHERE id=?");
			decreaseVote.setInt(1, Integer.parseInt(id));
			decreaseVote.executeUpdate();
			c.close();
		} catch( SQLException e ) {
			throw new ServletException( e );
		}
		response.sendRedirect("/cs3220stu54/List");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}