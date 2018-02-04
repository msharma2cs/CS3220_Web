package com.assignments.assignment4;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Student;

@WebServlet("/cookies/MyProfile")
public class StudentProfileCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Let's get a reference to the student who is currently logged in.
		Student student = (Student) request.getSession().getAttribute("authenticatedStudentCookie");
		
		// If there is no student logged in, redirect back to the login page.
		if (student == null) {
			response.sendRedirect("/cs3220stu54/cookies/Login");
			return;
		}
		
		// Set the content type
		response.setContentType("text/html");
		
		// Get a reference to the PrintWriter that lets us talk to the client
		PrintWriter out = response.getWriter();
		
		// Generate the HTML.
		out.println("<!DOCTYPE html>");
		out.println("");
		out.println("<html lang=\"en\">");
		out.println("");
		/* Page Head Section goes here. */
		out.println("    <head>");
		out.println("       	<!-- meta tags -->");
		out.println("       	<meta charset=\"utf-8\">");
		out.println("       	<meta name=\"description\" content=\"Student Profile Application - Profile page: Shows details of logged in user.\">");
		out.println("       	<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
		out.println("		<meta http-equiv=\"cache-control\" content=\"max-age=0\" />");
		out.println("		<meta http-equiv=\"cache-control\" content=\"no-cache\" />");
		out.println("		<meta http-equiv=\"expires\" content=\"0\" />");
		out.println("		<meta http-equiv=\"expires\" content=\"Tue, 01 Jan 1980 1:00:00 GMT\" />");
		out.println("		<meta http-equiv=\"pragma\" content=\"no-cache\" />");
		out.println("");
		/* Stylesheet links. */
		out.println("        <!-- Bootstrap CSS -->");
		out.println("        <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css\" integrity=\"sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M\" crossorigin=\"anonymous\">");
		out.println("");
		out.println("        <!-- Custom Stylesheet : customization.css -->");
		out.println("        <link rel=\"stylesheet\" href=\"/cs3220stu54/css/assignment4/customization.css\">");
		out.println("");
		/* Page Title goes here */
		out.println("        <!-- Title of web page -->");
		out.println("        <title>Student Profile</title>");
		out.println("    </head>");
		out.println("");
		/* Page Body goes here */
		out.println("    <body>");
		/* Header Section of Page Body. */
		out.println("        <!-- Header Section -->");
		out.println("        <div class=\"jumbotron text-center headersection\">");
		out.println("            <div class=\"container\">");
		out.println("                <h1 class=\"headerh1\">Student Profile <small class=\"headersmall\"> Lab Assignment 4 </small></h1>");
		out.println("            </div>");
		out.println("        </div>");
		out.println("");
		/* Main content Section of Page Body. */
		out.println("        <!-- Page Content Section -->");
		out.println("        <div class=\"container\">");
		
		out.println("			<div class=\"jumbotron\">");
		out.println("    			<h1 class=\"text-center \">" + student.getFirstName() + "'s Profile :</h1>");
		out.println("    			<p class=\"lead text-center\">This is a Student's Only area.</p>");
		out.println("    			<p class=\"lead\">Full Name : <b><em>" + student.getFullName() + "</em></b></p>");
		out.println("    			<p class=\"lead\">User Name : <b><em>" + student.getEmail() + "</em></b></p>");
		out.println("	 			<a class=\"btn btn-primary\" href=\"/cs3220stu54/cookies/Logout\">Logout</a>");
		out.println("			</div>");
		
		out.println("			<h3>Grades</h3>");
		out.println("			<table class=\"table table-bordered table-hover\">");
		out.println("				<tr>");
		out.println("					<th>Assignment</th>");
		out.println("					<th>Score</th>");		
		out.println("				</tr>");
				
		// Print all of the student's scores.
		double[] scores = student.getScores();
		for ( int i = 0; i < scores.length; i++ ) {
			out.println("				<tr>");
			out.println("					<td>Assignment " + (i+1) + "</td>");
			out.println("					<td>" + String.format("%.2f",scores[i]) + "</td>");
			out.println("				</tr>");
		}
		
		out.println("			</table>");
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