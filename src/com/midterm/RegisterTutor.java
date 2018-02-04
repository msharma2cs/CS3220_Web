package com.midterm;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Tutor;

@WebServlet("/midterm/Register")
public class RegisterTutor extends HttpServlet {
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
		out.println("        <meta name=\"description\" content=\"Registration Page - Allows Tutor to register, for CSULA Computer Science Tutor Directory Application created for midterm.\">");
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
		out.println("        <title>Midterm::Tutor Registration</title>");
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
		out.println("        	<h2 class=\"text-center\">Register as a Tutor</h2>");
		out.println("        	<form action=\"/cs3220stu54/midterm/Register\" method=\"post\">");

		// Display the firstNameError message if it exists.
		out.println("				<div class=\"form-group\">");
		String firstNameError = (String) request.getAttribute("firstNameError");	
		if (firstNameError != null) out.println("					<label>First Name <small class=\"text-danger\">" + firstNameError + "</small></label>");
		else out.println("					<label>First Name</label>");
		if ((String) request.getAttribute("fname") != null) out.println("					<input class=\"form-control\" type=\"text\" name=\"firstname\" value=\"" + (String) request.getAttribute("fname") + "\" placeholder=\"First Name\">");
		else out.println("					<input class=\"form-control\" type=\"text\" name=\"firstname\" placeholder=\"First Name\">");
		out.println("				</div>");

		// Display the lastNameError message if it exists.
		out.println("				<div class=\"form-group\">");
		String lastNameError = (String) request.getAttribute("lastNameError");	
		if (lastNameError != null) out.println("					<label>Last Name <small class=\"text-danger\">" + lastNameError + "</small></label>");
		else out.println("					<label>Last Name</label>");
		if ((String) request.getAttribute("lname") != null) out.println("					<input class=\"form-control\" type=\"text\" name=\"lastname\" value=\"" + (String) request.getAttribute("lname") + "\" placeholder=\"Last Name\">");
		else out.println("					<input class=\"form-control\" type=\"text\" name=\"lastname\" placeholder=\"Last Name\">");
		out.println("				</div>");

		// Display the emailError message if it exists.
		out.println("				<div class=\"form-group\">");
		String emailError = (String) request.getAttribute("emailError");
		if (emailError != null) out.println("					<label>E-mail <small class=\"text-danger\">" + emailError + "</small></label>");
		else out.println("					<label>E-mail</label>");
		if ((String) request.getAttribute("email") != null)	out.println("					<input class=\"form-control\" type=\"text\" name=\"email\" value=\"" + (String) request.getAttribute("email") + "\" placeholder=\"Email\">");
		else		out.println("					<input class=\"form-control\" type=\"text\" name=\"email\" placeholder=\"Email\">");
		out.println("				</div>");

		// Display the coursesError message if it exists.
		out.println("				<div class=\"form-group\">");
		String coursesError = (String) request.getAttribute("coursesError");	
		if (coursesError != null) out.println("					<label>Courses <small class=\"text-danger\">" + coursesError + "</small></label>");
		else out.println("					<label>Courses</label>");
		out.println("					<p>(Use , <small>(comma)</small> to separate multiple courses.)</p>");
		if ((String) request.getAttribute("coursesName") != null)		out.println("					<input class=\"form-control\" type=\"text\" name=\"courses\" value=\"" + (String) request.getAttribute("coursesName") + "\" placeholder=\"Courses\">");
		else		out.println("					<input class=\"form-control\" type=\"text\" name=\"courses\" placeholder=\"Courses\">");

		out.println("				</div>");
		out.println("				<button type=\"submit\" class=\"btn btn-primary\">Register</button>");
		out.println("			</form>");
		out.println("        </div>");
		out.println("");
		out.println("    </body>");
		out.println("");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the parameters from the request object.
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String courses = request.getParameter("courses");
		boolean isFormComplete = true;

		// Check if First Name field is blank.
		if ( firstName == null || firstName.trim().length() == 0 ) {
			isFormComplete = false;
			request.setAttribute("firstNameError", "You must enter your First Name, can't be left blank.");
		} else request.setAttribute("fname", firstName);

		// Check if Last Name field is blank.
		if ( lastName == null || lastName.trim().length() == 0 ) {
			isFormComplete = false;
			request.setAttribute("lastNameError", "You must enter your Last Name, can't be left blank.");
		} else request.setAttribute("lname", lastName);

		// Check if Email field is blank.
		if ( email == null || email.trim().length() == 0 ) {
			isFormComplete = false;
			request.setAttribute("emailError", "You must enter your E-mail, can't be left blank.");
		} else request.setAttribute("email", email);

		// Check if password fields are blank or mismatch.
		if ( courses == null || courses.trim().length() == 0 ) {
			isFormComplete = false;
			request.setAttribute("coursesError", "You must enter atleast 1 course, can't be left blank.");
		} else request.setAttribute("coursesName", courses);

		if ( !isFormComplete ) {
			doGet(request, response);
			return;
		}

		// Getting till here means that all fields of Register form has been correctly filled.
		// Get the application scope variable tutors.
		List<Tutor> tutors = (ArrayList<Tutor>) getServletContext().getAttribute("tutors");

		// Add the new user to the list.
		Tutor newTutor = new Tutor(firstName, lastName, email, courses);
		tutors.add(newTutor);

		// Save the currently registered tutor details in session attribute.
		request.getSession().setAttribute("registeredTutor", newTutor);

		// Redirect to Registration Success page.
		response.sendRedirect("/cs3220stu54/midterm/TutorRegistered");
		return;
	}

}