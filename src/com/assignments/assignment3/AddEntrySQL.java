package com.assignments.assignment3;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(description = "GuestBook Application using SQL.", urlPatterns= {"/Guestbook/AddEntrySQL"})
public class AddEntrySQL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// To check :
		// 		if this servlet is called for the first time from GuestBook servlet, or
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


		// Set the content type
		response.setContentType("text/html");

		// Get a reference to the PrintWriter that lets us talk to the client
		PrintWriter out = response.getWriter();

		// Generate the HTML
		out.println("<!DOCTYPE html>");
		out.println("");
		out.println("<html lang=\"en\">");
		out.println("");
		/* Page Head Section goes here. */
		out.println("    <head>");
		out.println("        <!-- meta tags -->");
		out.println("        <meta charset=\"utf-8\">");
		out.println("        <meta name=\"description\" content=\"Guest Book Application - Form to add a new entry in Guest Book.\">");
		out.println("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
		out.println("");
		/* Stylesheet links. */
		out.println("        <!-- Bootstrap CSS -->");
		out.println("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css\" integrity=\"sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M\" crossorigin=\"anonymous\">");
		out.println("");
		out.println("        <!-- Custom Stylesheet : customization.css -->");
		out.println("        <link rel=\"stylesheet\" href=\"/cs3220stu54/css/customization3.css\">");
		out.println("");
		/* Page Title goes here */
		out.println("        <!-- Title of web page -->");
		out.println("        <title>Add Entry</title>");
		out.println("    </head>");
		out.println("");
		/* Page Body goes here */
		out.println("    <body>");
		/* Header Section of Page Body. */
		out.println("        <!-- Header Section -->");
		out.println("        <div class=\"jumbotron text-center headersection\">");
		out.println("            <div class=\"container\">");
		out.println("                <h1 class=\"headerh1\">Guest Book<small class=\"headersmall\"> Lab Assignment 3</small></h1>");
		out.println("            </div>");
		out.println("        </div>");
		out.println("");
		/* Main content Section of Page Body. */
		out.println("        <!-- Page Content Section -->");
		out.println("        <div class=\"container\">");
		out.println("    		<h1>Add Entry</h1><hr>");
		out.println("			<form action=\"/cs3220stu54/Guestbook/AddEntrySQL\" method=\"post\">");

		out.println("				<table>");
		// If Add Entry form was submitted without name being specified.
		if ( !isFirstCall && isNameNull) {
			out.println("					<tr><td colspan=2 align=\"left\"><p class=\"text-danger\">You must specify a name.</p></td></tr>");

		}

		out.println("    				<tr>");
		out.println("      					<td align=\"left\">Name:</td>");
		// If Add Entry form was submitted with name, but without message being specified.
		if ( !isFirstCall && !isNameNull) {
			out.println("					<td align=\"left\"><input type=\"text\" name=\"name\" value=\"" + name + "\" size=\"21\"/></td>");	
		} else {
			out.println("      				<td align=\"left\"><input type=\"text\" name=\"name\" size=\"21\"/></td>");
		}
		out.println("    				</tr>");

		// If Add Entry form was submitted without message being specified.
		if ( !isFirstCall && isMessageNull) {
			out.println("					<tr><td colspan=2 align=\"left\"><p class=\"text-danger\">You must enter a message.</p></td></tr>");	
		}

		out.println("    				<tr>");
		out.println("   						<td align=\"left\">Message:</td>\n");
		// If Add Entry form was submitted with message, but without name being specified.
		if ( !isFirstCall && !isMessageNull) {
			out.println("      					<td align=\"left\"><textarea name=\"message\" rows=\"5\" cols=\"20\">" + message + "</textarea></td>\n");
		} else {
			out.println("      					<td align=\"left\"><textarea name=\"message\" rows=\"5\" cols=\"20\"></textarea></td>\n");
		}

		out.println("    				</tr>\n");
		out.println("				</table>");
		out.println("				<input class=\"btn btn-primary\" type=\"submit\" name=\"addEntry\" value=\"Add Entry\"><hr>");
		out.println("			</form>");
		out.println("			<br><a class=\"btn btn-primary\" href=\"/cs3220stu54/Guestbook/GuestBookSQL\">Back to GuestBook</a>");
		out.println("        </div>");
		out.println("");
		out.println("    </body>");
		out.println("");
		out.println("</html>");
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
			response.sendRedirect("GuestBookSQL");
		} else {
			// Reload AddEntry page with sticky form.
			response.sendRedirect("/cs3220stu54/Guestbook/AddEntrySQL?errorCheck=1");
		}
	}

}