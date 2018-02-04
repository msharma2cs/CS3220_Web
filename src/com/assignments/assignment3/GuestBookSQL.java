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
import database.utils.DatabaseConnection;

@WebServlet(description = "GuestBook Application using SQL.", urlPatterns= {"/Guestbook/GuestBookSQL"})
public class GuestBookSQL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the query string, if search term is passed in the parameters.
		String query = request.getParameter("query");

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
		out.println("        <meta name=\"description\" content=\"Guest Book Application - Displays a list of entries in Guest Book.\">");
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
		out.println("        <title>Guest Book</title>");
		out.println("    </head>");
		out.println("");
		/* Page Body goes here */
		out.println("    <body>");
		/* Header Section of Page Body. */
		out.println("        <!-- Header Section -->");
		out.println("        <div class=\"jumbotron text-center headersection\">");
		out.println("            <div class=\"container\">");
		out.println("                <h1 class=\"headerh1\">Guest Book <small class=\"headersmall\"> Lab Assignment 3</small></h1>");
		out.println("            </div>");
		out.println("        </div>");
		out.println("");
		/* Main content Section of Page Body. */
		out.println("        <!-- Page Content Section -->");
		out.println("        <div class=\"container\">");
		out.println("        	<h1>Guest Book</h1><hr>");
		// Table for Search functionality form.
		out.println("			<form class=\"form-inline\" action=\"GuestBookSQL\" method=\"GET\">");
		out.println("				<div class=\"form-group\">");
		out.println("                	<input class=\"form-control\" style=\"margin-right:1rem;\" type=\"text\" name=\"query\" value=\"\" placeholder=\"Enter your search term(s)\" size=\"30\">");
		out.println("                   	<input class=\"form-control btn btn-primary\" type=\"submit\" value=\"Search\">");
		out.println("            	</div>");
		out.println("			</form><hr>");
		// Table for list of entries.
		out.println("            <div class=\"table-responsive\">");
		out.println("                <table class=\"table table-bordered table-hover\">");
		out.println("                	<tr>");
		out.println("                    	<th bgcolor=\"#e9ecef\">Name</th>");
		out.println("                        <th bgcolor=\"#e9ecef\">Message</th>");
		out.println("                        <th bgcolor=\"#e9ecef\">Date</th>");
		out.println("                        <th bgcolor=\"#e9ecef\">Actions</th>");
		out.println("                   	</tr>");

		/* Java code to generate entries of the Guest Book. */
		if ( query == null || query.length() == 0 ) {
			try {
				Connection c = (new DatabaseConnection()).getServerSQLConnection();
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM Guestbook" );	

				while( rs.next() ) {
					out.println("                <tr>");
					out.println("                	<td width=\"20%\">" + rs.getString("name") + "</td>");
					out.println("                    <td width=\"40%\">" + rs.getString("message") + "</td>");
					out.println("                    <td width=\"20%\">" + rs.getDate("date") + "</td>");
					out.println("                    <td width=\"20%\"><a class=\"btn btn-primary\" href=\"/cs3220stu54/Guestbook/EditEntrySQL?id=" + rs.getInt("id") + "\">Edit</a> | <a class=\"btn btn-primary\" href=\"/cs3220stu54/Guestbook/DeleteEntrySQL?id=" + rs.getInt("id") + "\">Delete</a></td>");
					out.println("                </tr>");
				}
				stmt.close();
				c.close();
			} catch ( SQLException e ) {
				throw new ServletException( e );
			}
		} else if ( query.length() > 0 ) {
			String[] queryTerms = query.trim().split(" ");
			String whereClause = "";
			for ( String queryTerm : queryTerms )
				whereClause += "name LIKE \'%" + queryTerm + "%\' OR message LIKE \'%" + queryTerm + "%\' OR ";
			try {
				Connection c = (new DatabaseConnection()).getServerSQLConnection();
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery( "SELECT * FROM Guestbook WHERE " + whereClause.trim().substring(0, whereClause.length()-3));	

				while( rs.next() ) {
					out.println("                <tr>");
					out.println("                	<td width=\"20%\">" + rs.getString("name") + "</td>");
					out.println("                    <td width=\"40%\">" + rs.getString("message") + "</td>");
					out.println("                    <td width=\"20%\">" + rs.getDate("date") + "</td>");
					out.println("                    <td width=\"20%\"><a class=\"btn btn-primary\" href=\"/cs3220stu54/Guestbook/EditEntrySQL?id=" + rs.getInt("id") + "\">Edit</a> | <a class=\"btn btn-primary\" href=\"/cs3220stu54/Guestbook/DeleteEntrySQL?id=" + rs.getInt("id") + "\">Delete</a></td>");
					out.println("                </tr>");
				}
				stmt.close();
				c.close();
			} catch ( SQLException e ) {
				throw new ServletException( e );
			}
		}

		out.println("                </table>");
		out.println("            </div>");
		out.println("			<a class=\"btn btn-primary\" href=\"/cs3220stu54/Guestbook/AddEntrySQL\">Add New Entry</a>");
		out.println("        </div>");
		out.println("");
		out.println("    </body>");
		out.println("");
		out.println("</html>");	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
