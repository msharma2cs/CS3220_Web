package com.assignments.assignment3;

import java.io.IOException;
import java.io.PrintWriter;
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

@WebServlet(description = "GuestBook Application using SQL.", urlPatterns= {"/Guestbook/DeleteEntrySQL"})
public class DeleteEntrySQL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deleteId = request.getParameter("id");
		String deleteName = "";
		String deleteMessage = "";
		
		if ( deleteId == null || deleteId.length() == 0 )
			response.sendRedirect("GuestBookSQL");
		
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
		out.println("        <meta name=\"description\" content=\"Guest Book Application - Delete an existing entry from Guest Book.\">");
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
		out.println("        <title>Delete Entry</title>");
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
		out.println("    		<h1>Delete Entry</h1><hr>");
		out.println("			<form action=\"/cs3220stu54/Guestbook/DeleteEntrySQL\" method=\"post\">");
		out.println("				<table>");
		out.println("    				<tr>");
		out.println("      					<td align=\"left\">Name</td>");
		out.println("      					<td align=\"left\">: " + deleteName + "</td>");
		out.println("    				</tr>");
		out.println("    				<tr>");
		out.println("   						<td align=\"left\">Message</td>\n");
		out.println("      					<td align=\"left\">: " + deleteMessage + "</td>\n");
		out.println("    				</tr>\n");
		out.println("				</table>");
		out.println("				<input type=\"hidden\" name=\"id\" value=\"" + deleteId + "\"");
		out.println("				<hr><input class=\"btn btn-primary\" type=\"submit\" name=\"addEntry\" value=\"Confirm Delete\"><hr>");
		out.println("			</form>");
		out.println("			<br><a class=\"btn btn-primary\" href=\"/cs3220stu54/Guestbook/GuestBookSQL\">Back to GuestBook</a>");
		out.println("        </div>");
		out.println("");
		out.println("    </body>");
		out.println("");
		out.println("</html>");
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
				c.close();
			} catch( SQLException e ) {
				throw new ServletException( e );
			}

			// Redirect to GuesBook with modified entry.
			response.sendRedirect("GuestBookSQL");
			return;
		}
		doGet(request, response);
	}

}