package com.assignments.assignment6;

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
import models.SimpsonsCharacter;

@WebServlet(description = "Simpsons Character Catalog using SQL.", urlPatterns= {"/Simpsons/CharaterCatalogSQL"})
public class CharacterCatalogSQL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<SimpsonsCharacter> characters = new ArrayList<SimpsonsCharacter>();
		
		try {
			Connection c = (new DatabaseConnection()).getServerSQLConnection();
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM SimpsonsCharacters");
			
			while ( rs.next() ) {
				SimpsonsCharacter sc = new SimpsonsCharacter(rs.getInt("id"), rs.getString("name"), rs.getInt("number_of_images"));
				characters.add(sc);
			}
			
			rs.close();
			stmt.close();
			c.close();
		} catch ( SQLException e ) {
			throw new ServletException( e );
		}
		
		request.setAttribute("characters", characters);
		request.getRequestDispatcher("/WEB-INF/Simpsons/CharacterCatalog.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}