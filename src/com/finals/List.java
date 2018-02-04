package com.finals;

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
import models.ArticlesList;

@WebServlet("/List")
public class List extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ArticlesList> articles = new ArrayList<>();
		
		try {
			Connection c = (new DatabaseConnection()).getServerSQLConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery( "SELECT * FROM FinalArticles ORDER BY votes DESC, title ASC" );	
			
			while( rs.next() ) {
				ArticlesList al = new ArticlesList(rs.getInt( "id" ), rs.getString( "title" ), rs.getString( "link" ), rs.getInt( "votes" ));
				articles.add(al);
			}
			stmt.close();
			c.close();
		} catch( SQLException e ) {
			throw new ServletException( e );
		}
		
		request.setAttribute("articles", articles);
		request.getRequestDispatcher("/Finals/MainList.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}