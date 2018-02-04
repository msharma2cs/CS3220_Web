package com.assignments.assignment3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.GuestBookEntry;

@WebServlet(description = "GuestBook Application using servlets.", urlPatterns="/Guestbook/EditEntryServlet")
public class EditEntry extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String editId = request.getParameter("id");
		String editName = "";
		String editMessage = "";
		ArrayList<GuestBookEntry> entries = (ArrayList<GuestBookEntry>) getServletContext().getAttribute("entries");
		for ( GuestBookEntry entry : entries ) {
			if ( entry.getId() == Integer.parseInt(editId) ) {
				editName = entry.getName();
				editMessage = entry.getMessage();
				break;
			}
		}
		
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
		out.println("        <meta name=\"description\" content=\"Guest Book Application - Form to edit an existing entry in Guest Book.\">");
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
		out.println("        <title>Edit Entry</title>");
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
		out.println("    		<h1>Edit Entry</h1><hr>");
		out.println("			<form action=\"/cs3220stu54/Guestbook/EditEntryServlet\" method=\"post\">");
		out.println("				<table>");
		out.println("    				<tr>");
		out.println("      					<td align=\"left\">Name:</td>");
		out.println("      					<td align=\"left\"><input type=\"text\" name=\"name\" value=\"" + editName + "\" /></td>");
		out.println("    				</tr>");
		out.println("    				<tr>");
		out.println("   						<td align=\"left\">Message:</td>\n");
		out.println("      					<td align=\"left\"><textarea name=\"message\" rows=\"5\" cols=\"20\">" + editMessage + "</textarea></td>\n");
		out.println("    				</tr>\n");
		out.println("				</table>");
		out.println("				<input type=\"hidden\" name=\"id\" value=\"" + editId + "\"");
		out.println("				<hr><input class=\"btn btn-primary\" type=\"submit\" name=\"addEntry\" value=\"Confirm Edit\"><hr>");
		out.println("			</form>");
		out.println("			<br><a class=\"btn btn-primary\" href=\"/cs3220stu54/Guestbook/GuestBookServlet\">Back to GuestBook</a>");
		out.println("        </div>");
		out.println("");
		out.println("    </body>");
		out.println("");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get parameters passed from form of doGet method of EditEntry.
		String editId = request.getParameter("id");
		String editName = request.getParameter("name");
		String editMessage = request.getParameter("message");
		
		if ( editId != null && editId.trim().length() > 0 && editName != null && editName.trim().length() > 0 && editMessage != null && editMessage.trim().length() > 0 ) {
			ArrayList<GuestBookEntry> entries = (ArrayList<GuestBookEntry>) getServletContext().getAttribute("entries");
			for ( GuestBookEntry entry : entries ) {
				if ( entry.getId() == Integer.parseInt(editId) ) {
					entry.setName(editName);
					entry.setMessage(editMessage);
					break;
				}
			}
			// Redirect to GuesBook with modified entry.
			response.sendRedirect("GuestBookServlet");
		}
		doGet(request, response);
	}

}