package com.midterm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Tutor;

@WebServlet("/midterm/Administrator")
public class TutorAdministrator extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		out.println("        <meta name=\"description\" content=\"Administrator - Site Admin page for CSULA Computer Science Tutor Directory Application created for midterm.\">");
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
		out.println("        <title>Midterm::Tutor Administrator</title>");
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
		out.println("        	<h2 class=\"text-center\">Welcome Administrator to Tutors Catalog...</h1><hr>");
		// Table for list of entries.
		out.println("            <div class=\"table-responsive\">");
		out.println("                <table class=\"table table-bordered table-hover sortable\">");
		out.println("                	<tr>");
		out.println("                    	<th bgcolor=\"#e9ecef\">Full Name</th>");
		out.println("                        <th bgcolor=\"#e9ecef\">Email</th>");
		out.println("                        <th bgcolor=\"#e9ecef\">Courses</th>");
		out.println("                        <th bgcolor=\"#e9ecef\">Actions</th>");
		out.println("                   	</tr>");

		/* Java code to generate entries of the Guest Book. */
		ArrayList<Tutor> tutors = (ArrayList<Tutor>) getServletContext().getAttribute("tutors");

		for ( Tutor tutor : tutors ) {
			out.println("                <tr>");
			out.println("                	<td width=\"20%\">" + tutor.getFullName() + "</td>");
			out.println("                    <td width=\"40%\">" + tutor.getEmail() + "</td>");
			out.println("                    <td width=\"50%\"><ul>");
			String[] courses = tutor.getCourses().trim().split(",");
			for ( String course : courses )
				out.println("                    <li>" + course + "</li>");
			out.println("                    </td>");
			out.println("                    <td width=\"20%\"><a class=\"btn btn-primary\" href=\"/cs3220stu54/midterm/ConfirmDelete?id=" + tutor.getId() + "\">Delete</a></td>");
			out.println("                </tr>");
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