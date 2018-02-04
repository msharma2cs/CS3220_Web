package com.assignments.assignment3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.GuestBookEntry;

@WebServlet(description = "GuestBook Application using servlets.", urlPatterns= {"/Guestbook/GuestBookServlet"}, loadOnStartup=2)
public class GuestBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ArrayList<GuestBookEntry> entries = new ArrayList<GuestBookEntry>();
		entries.add(new GuestBookEntry("John", "Hello!"));
		entries.add(new GuestBookEntry("Mary", "Hi!"));
		entries.add(new GuestBookEntry("Joe", "Howdy!"));
		
		ServletContext context = this.getServletContext();
		context.setAttribute("entries", entries);
	}

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
		out.println("			<form class=\"form-inline\" action=\"GuestBookServlet\" method=\"GET\">");
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
		ArrayList<GuestBookEntry> entries = (ArrayList<GuestBookEntry>) getServletContext().getAttribute("entries");
		
		if ( query == null || query.length() == 0 ) {
			for ( GuestBookEntry entry : entries ) {
				out.println("                <tr>");
				out.println("                	<td width=\"20%\">" + entry.getName() + "</td>");
				out.println("                    <td width=\"40%\">" + entry.getMessage() + "</td>");
				out.println("                    <td width=\"20%\">" + entry.getCreated() + "</td>");
				out.println("                    <td width=\"20%\"><a class=\"btn btn-primary\" href=\"/cs3220stu54/Guestbook/EditEntryServlet?id=" + entry.getId() + "\">Edit</a> | <a class=\"btn btn-primary\" href=\"/cs3220stu54/Guestbook/DeleteEntryServlet?id=" + entry.getId() + "\">Delete</a></td>");
				out.println("                </tr>");
			}
		} else if ( query.length() > 0 ) {
			String[] queryTerms = query.trim().split(" ");
			for ( GuestBookEntry entry : entries ) {
				for ( String queryTerm : queryTerms ) {
					if ( entry.getName().toLowerCase().contains(queryTerm.toLowerCase()) || entry.getMessage().toLowerCase().contains(queryTerm.toLowerCase()) ) {
						out.println("                <tr>");
						out.println("                	<td width=\"20%\">" + entry.getName() + "</td>");
						out.println("                    <td width=\"40%\">" + entry.getMessage() + "</td>");
						out.println("                    <td width=\"20%\">" + entry.getCreated() + "</td>");
						out.println("                    <td width=\"20%\"><a class=\"btn btn-primary\" href=\"/cs3220stu54/Guestbook/EditEntryServlet?id=" + entry.getId() + "\">Edit</a> | <a class=\"btn btn-primary\" href=\"/cs3220stu54/Guestbook/DeleteEntryServlet?id=" + entry.getId() + "\">Delete</a></td>");
						out.println("                </tr>");
						break;
					}
				}
			}
		}
		
		out.println("                </table>");
		out.println("            </div>");
		out.println("			<a class=\"btn btn-primary\" href=\"/cs3220stu54/Guestbook/AddEntryServlet\">Add New Entry</a>");
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