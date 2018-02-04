package com.midterm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Tutor;

@WebServlet(urlPatterns = "/midterm/TutorCatalog", loadOnStartup = 5)
public class SearchTutorCatalog extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// Create a few tutors.
		List<Tutor> tutors = new ArrayList<Tutor>();
		tutors.add(new Tutor("Chengyu", "Sun", "chengyu@sun.com", "CS 1220 - Introduction to Website Development"));
		tutors.add(new Tutor("Yuqing", "Zhu", "yuqing@zhu.com", "CS 2011 - Introduction to Programming I, CS 2012 - Introduction to Programming II"));
		tutors.add(new Tutor("Behzad", "Parviz", "behzad@parviz.com", "CS 3112 - Analysis of Algorithms"));
		tutors.add(new Tutor("Albert", "Cervantes", "albert@cervantes.com", "CS 3220 - Web and Internet Programming"));
		tutors.add(new Tutor("Huiping", "Guo", "huiping@guo.com", "CS 4222 - Principles of Database Systems"));

		// Add the students to the application scope (Servlet Context)
		this.getServletContext().setAttribute("tutors", tutors);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the query string, if search term is passed in the parameters.
		String query = request.getParameter("query");

		// Set the content type.
		response.setContentType("text/html");

		// Get a reference to the PrintWriter that lets us talk to the client.
		PrintWriter out = response.getWriter();

		// Generate the HTML.
		out.println("<!DOCTYPE html>");
		out.println("");
		out.println("<html lang=\"en\">");
		out.println("");
		/* Page Head Section goes here. */
		out.println("    <head>");
		out.println("        <!-- meta tags -->");
		out.println("        <meta charset=\"utf-8\">");
		out.println("        <meta name=\"description\" content=\"Tutor Catalog - Tutors catalog, and tutor search page for students, for CSULA Computer Science Tutor Directory Application created for midterm.\">");
		out.println("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
		out.println("");
		/* Stylesheet links. */
		out.println("        <!-- Bootstrap CSS -->");
		out.println("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css\" integrity=\"sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M\" crossorigin=\"anonymous\">");
		out.println("");
		out.println("        <!-- Custom Stylesheet : customization.css -->");
		out.println("        <link rel=\"stylesheet\" href=\"/cs3220stu54/css/midterm/customization.css\">");
		out.println("");
		/* JavaScript Link. */
		out.println("		<!-- JavaScript - to sort the table columns, function taken from w3schools.com -->");
		out.println("		<script src=\"/cs3220stu54/js/midterm/sorttable.js\" type=\"text/javascript\"></script>");
		out.println("");
		/* Page Title goes here */
		out.println("        <!-- Title of web page -->");
		out.println("        <title>Midterm::Tutors Catalog</title>");
		out.println("    </head>");
		out.println("");
		/* Page Body goes here */
		out.println("    <body>");
		/* Header Section of Page Body. */
		out.println("        <!-- Header Section -->");
		out.println("        <div class=\"jumbotron text-center headersection\">");
		out.println("            <div class=\"container\">");
		out.println("                <h1 class=\"headerh1\">CSULA Computer Science Tutor Directory <small class=\"headersmall\"> Midterm </small></h1>");
		out.println("            </div>");
		out.println("        </div>");
		out.println("");
		/* Main content Section of Page Body. */
		out.println("        <!-- Page Content Section -->");
		out.println("        <div class=\"container\">");
		out.println("        	<h2 class=\"text-center\">Welcome Student to Tutors Catalog...</h1><hr>");
		// Table for Search functionality form.
		out.println("			<form class=\"form-inline\" action=\"TutorCatalog\" method=\"GET\">");
		out.println("				<div class=\"form-group\">");
		out.println("                	<input class=\"form-control\" style=\"margin-right:1rem;\" type=\"text\" name=\"query\" value=\"\" placeholder=\"Enter your search term(s)\" size=\"30\">");
		out.println("                   	<input class=\"form-control btn btn-primary\" type=\"submit\" value=\"Search\">");
		out.println("            	</div>");
		out.println("			</form><hr>");
		// Table for list of entries.
		out.println("            <div class=\"table-responsive\">");
		out.println("                <table class=\"table table-bordered table-hover sortable\">");
		out.println("                	<tr>");
		out.println("                    	<th bgcolor=\"#e9ecef\">Full Name</th>");
		out.println("                        <th bgcolor=\"#e9ecef\">Email</th>");
		out.println("                        <th bgcolor=\"#e9ecef\">Courses</th>");
		out.println("                   	</tr>");

		/* Java code to generate entries of the Guest Book. */
		ArrayList<Tutor> tutors = (ArrayList<Tutor>) getServletContext().getAttribute("tutors");

		// Filtering data and displaying in a table based on query string.
		// If query string is null or length of query string is 0 :
		//		displaying the entire list.
		// Else :
		// 		only the entries that contains any of the passed query string displayed.
		
		if ( query == null || query.length() == 0 ) {
			for ( Tutor tutor : tutors ) {
				out.println("                <tr>");
				out.println("                	<td width=\"25%\">" + tutor.getFullName() + "</td>");
				out.println("                    <td width=\"25%\">" + tutor.getEmail() + "</td>");
				out.println("                    <td width=\"50%\"><ul>");
				String[] courses = tutor.getCourses().trim().split(",");
				for ( String course : courses )
					out.println("                    <li>" + course + "</li>");
				out.println("                    </td>");
				out.println("                </tr>");
			}
		} else if ( query.length() > 0 ) {
			String[] queryTerms = query.trim().split(" ");
			for ( Tutor tutor : tutors ) {
				for ( String queryTerm : queryTerms ) {
					if ( tutor.getFullName().toLowerCase().contains(queryTerm.toLowerCase()) || tutor.getEmail().toLowerCase().contains(queryTerm.toLowerCase()) || tutor.getCourses().toLowerCase().contains(queryTerm.toLowerCase()) ) {
						out.println("                <tr>");
						out.println("                	<td width=\"20%\">" + tutor.getFullName() + "</td>");
						out.println("                    <td width=\"40%\">" + tutor.getEmail() + "</td>");
						out.println("                    <td width=\"50%\"><ul>");
						String[] courses = tutor.getCourses().trim().split(",");
						for ( String course : courses )
							out.println("                    <li>" + course + "</li>");
						out.println("                    </td>");
						out.println("                </tr>");
						break;
					}
				}
			}
		}

		out.println("                </table>");
		out.println("            </div>");
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