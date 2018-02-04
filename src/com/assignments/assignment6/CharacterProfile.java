package com.assignments.assignment6;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.SimpsonsCharacter;

@WebServlet(description = "Simpsons Character Catalog", urlPatterns= {"/Simpsons/CharaterProfile"})
public class CharacterProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		if ( id == null || id.length() == 0 ) {
			response.sendRedirect("/WEB-INF/cs3220stu54/Simpsons/CharaterCatalog");
			return;
		}
		
		ArrayList<SimpsonsCharacter> sc = (ArrayList<SimpsonsCharacter>) getServletContext().getAttribute("characters");
		SimpsonsCharacter character = null;
		int characterId = Integer.parseInt(id);
		
		for ( SimpsonsCharacter c : sc ) {
			if ( c.getId() == characterId ) {
				character = c;
				break;
			}
		}
		
		if ( character == null ) {
			character = new SimpsonsCharacter("Invalid Character", 0);
			request.setAttribute("character", character);
			request.setAttribute("errorMsg", "No character found for the specified ID, please return to Catalog page and select a character.");
			request.getRequestDispatcher("/WEB-INF/Simpsons/NoCharacterProfile.jsp").forward(request, response);
		} else {
			request.setAttribute("character", character);
			if ( character.getNumberofimages() == 0 ) {
				request.setAttribute("errorMsg", "No images are available for " + character.getName() + ".");
				request.getRequestDispatcher("/WEB-INF/Simpsons/NoCharacterProfile.jsp").forward(request, response);
			} else if ( character.getNumberofimages() > 0 ){
				request.getRequestDispatcher("/WEB-INF/Simpsons/CharacterProfile.jsp").forward(request, response);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}