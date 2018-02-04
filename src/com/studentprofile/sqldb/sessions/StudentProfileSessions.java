package com.studentprofile.sqldb.sessions;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Student;

@WebServlet(description = "Student Profile Application using sessions and java arraylist.", urlPatterns= {"/Student/Sessions/MyProfile"})
public class StudentProfileSessions extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student student = (Student) request.getSession().getAttribute("authenticatedStudent");
		// If there is no student logged in, redirect back to the login page
		if (student == null) {
			response.sendRedirect("Login");
			return;
		}
		
		// Set the content type
		response.setContentType("text/html");
		
		// Get a reference to the PrintWriter that lets us talk to the client
		PrintWriter out = response.getWriter();
		
		// Generate the HTML
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("	<head>");
		out.println("        <!-- meta tags -->");
		out.println("        <meta charset=\"utf-8\">");
		out.println("        <meta name=\"description\" content=\"Student Profile Application using java arraylist and sessions - Login page.\">");
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
		out.println("        <title>Student::Profile(Sessions)</title>");
		out.println("    </head>");
		
		/* Page Body goes here */
		out.println("	<body>");
		out.println("		<div class=\"container\">");
		
		out.println("			<div class=\"jumbotron\">");
		out.println("    			<h1>" + student.getFirstName() + "'s Profile</small></h1>");
		out.println("   			 	<p class=\"lead\">This is a Student's Only area.</p>");
		out.println("	 			<a class=\"btn btn-primary\" href=\"Logout\">Logout</a>");
		out.println("			</div>");
		
		out.println("			<h3>Grades <small>" + student.getFullName() + "</small></h3>");
		out.println("			<table class=\"table table-bordered\">");
		out.println("				<tr>");
		out.println("					<th bgcolor=\"#e9ecef\">Assignment</th>");
		out.println("					<th bgcolor=\"#e9ecef\">Score</th>");		
		out.println("				</tr>");
				
		// Print all of the student's scores.
		double[] scores = student.getScores();
		for (int i = 0; i < scores.length; i++) {
			out.println("				<tr>");
			out.println("					<td>Assignment " + (i+1) + "</td>");
			out.println("					<td>" + String.format("%.2f",scores[i]) + "</td>");
			out.println("				</tr>");
		}
		out.println("			</table>");
		out.println("		</div>");
		out.println("	</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}