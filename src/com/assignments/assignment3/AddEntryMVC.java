package com.assignments.assignment3;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.jdbc.PreparedStatement;
import database.utils.DatabaseConnection;

@WebServlet(description = "GuestBook Application using SQL and MVC Architecture.", urlPatterns= {"/Guestbook/AddEntryMVC"})
public class AddEntryMVC extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// To check :
		// 		if this servlet is called for the first time from GuestBookMVC servlet, or
		// 		if this servlet is redirected from doPost because of empty input form fields.
		boolean isFirstCall = true;
		Map<String, String[]> paramsAddEntry = request.getParameterMap();
		if ( paramsAddEntry.keySet().contains("errorCheck") ) 
			isFirstCall = false;
		// Sticky form.
		boolean isNameNull = false, isMessageNull = false;
		String name="", message="";
		try {
			isNameNull = (boolean) getServletContext().getAttribute("isNameNull");
			isMessageNull = (boolean) getServletContext().getAttribute("isMessageNull");
			name = (String) getServletContext().getAttribute("name");
			message = (String) getServletContext().getAttribute("message");
		} catch (NullPointerException npe) {}

		request.setAttribute("isFirstCall", isFirstCall);
		request.setAttribute("isNameNull", isNameNull);
		request.setAttribute("isMessageNull", isMessageNull);
		request.setAttribute("name", name);
		request.setAttribute("message", message);
		request.getRequestDispatcher("/WEB-INF/Guestbook/AddentryMVC.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get parameters passed from form of doGet method of AddEntry.
		String name = request.getParameter("name");
		String message = request.getParameter("message");
		// To store state of form fields - the input fields being left empty.
		boolean isNameNull = false;
		boolean isMessageNull = false;

		// Validate the name input for sticky forms.
		if ( name == null || name.equals("")) {
			isNameNull = true;
			getServletContext().setAttribute("isNameNull", isNameNull);
			getServletContext().setAttribute("name", "");
		} else {
			isNameNull = false;
			getServletContext().setAttribute("isNameNull", isNameNull);
			getServletContext().setAttribute("name", name);
		}

		// Validate the message input for sticky forms.
		if ( message == null || message.equals("")) {
			isMessageNull = true;
			getServletContext().setAttribute("isMessageNull", isMessageNull);
			getServletContext().setAttribute("message", "");
		} else {
			isMessageNull = false;
			getServletContext().setAttribute("isMessageNull", isMessageNull);
			getServletContext().setAttribute("message", message);
		}

		if ( name != null && name.trim().length() > 0 && message != null && message.trim().length() > 0 ) {	
			try {
				Connection c = (new DatabaseConnection()).getServerSQLConnection();
				PreparedStatement insertEntry = (PreparedStatement) c.prepareStatement("INSERT INTO Guestbook (name, message) VALUES (?, ?)");
				insertEntry.setString(1, name);
				insertEntry.setString(2, message);
				insertEntry.executeUpdate();
				insertEntry.close();
				c.close();
			} catch ( SQLException e ) {
				throw new ServletException( e );
			}

			// Reset the attributes that help in sticky forms.
			getServletContext().setAttribute("name", "");
			getServletContext().setAttribute("message", "");
			// Redirect to GuestBook.
			response.sendRedirect("GuestBookMVC");
		} else {
			// Reload AddEntry page with sticky form.
			response.sendRedirect("/cs3220stu54/Guestbook/AddEntryMVC?errorCheck=1");
		}
	}

}