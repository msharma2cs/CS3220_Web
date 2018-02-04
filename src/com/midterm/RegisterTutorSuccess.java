package com.midterm;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Tutor;

@WebServlet("/midterm/TutorRegistered")
public class RegisterTutorSuccess extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Let's get a reference to the tutor who just registered.
		Tutor tutor = (Tutor) request.getSession().getAttribute("registeredTutor");

		// If registration failed, redirect back to the tutor registration page.
		if (tutor == null) {
			response.sendRedirect("/cs3220stu54/midterm/Register");
			return;
		}

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
		out.println("        <meta name=\"description\" content=\"Registration Successful - Notifies tutor about successful registration, for CSULA Computer Science Tutor Directory Application created for midterm.\">");
		out.println("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
		out.println("");
		/* Stylesheet links. */
		out.println("        <!-- Bootstrap CSS -->");
		out.println("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css\" integrity=\"sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M\" crossorigin=\"anonymous\">");
		out.println("");
		out.println("        <!-- Custom Stylesheet : customization.css -->");
		out.println("        <link rel=\"stylesheet\" href=\"/cs3220stu54/css/midterm/customization.css\">");
		out.println("");
		/* Page Title goes here */
		out.println("        <!-- Title of web page -->");
		out.println("        <title>Midterm::Success Register</title>");
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
		out.println("        	<h2 class=\"text-center\">Tutor Registered Successfully...</h1><hr>");
		out.println("			<table>");
		out.println("    			<tr>");
		out.println("      				<td align=\"left\">Name</td>");
		out.println("      				<td align=\"left\">: " + tutor.getFullName() + "</td>");
		out.println("    			</tr>");
		out.println("    			<tr>");
		out.println("   					<td align=\"left\">Email</td>\n");
		out.println("      				<td align=\"left\">: " + tutor.getEmail() + "</td>\n");
		out.println("    			</tr>\n");
		out.println("    			<tr>");
		out.println("   					<td align=\"left\">Courses</td>\n");
		out.println("      				<td align=\"left\">: " + tutor.getCourses() + "</td>\n");
		out.println("    			</tr>\n");
		out.println("			</table><br>");
		out.println("			<br><a class=\"btn btn-primary\" href=\"/cs3220stu54/midterm/TutorCatalog\">See Tutors Catalog</a>");
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