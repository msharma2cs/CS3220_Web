package com.assignments.assignment4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Student;

@WebServlet("/cookies/Signup")
public class SignUpStudentCookies extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Student getLoggedInStudent( HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if ( cookies != null ) {
			for ( Cookie cookie : cookies ) {
				if ( cookie.getName().equals("student") ) {
					List<Student> students = (ArrayList<Student>) getServletContext().getAttribute("students");
					for ( Student student : students ) {
						if ( student.getHashcode().equals(cookie.getValue()) ) {
							return student;
						}
					}
				}
			}
		}
		return null;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if ( request.getSession().getAttribute("authenticatedStudentCookie") != null ) {
			response.sendRedirect("/cs3220stu54/cookies/MyProfile");
			return;
		}
		
		Student student = getLoggedInStudent(request);
		if ( student != null ) {
			request.getSession().setAttribute("authenticatedStudentCookie", student);
			response.sendRedirect("/cs3220stu54/cookies/MyProfile");
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
		out.println("        <meta name=\"description\" content=\"Student Profile Application - Signup page, create a new student user.\">");
		out.println("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");
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
		out.println("        <title>SignUp-Student Profile</title>");
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

		// Create the login form
		out.println("        	<form action=\"/cs3220stu54/cookies/Signup\" method=\"post\">");
		
		// Display the firstNameError message if it exists
		out.println("				<div class=\"form-group\">");
		String firstNameError = (String) request.getAttribute("firstNameError");	
		if (firstNameError != null) out.println("					<label>First Name <small class=\"text-danger\">" + firstNameError + "</small></label>");
		else out.println("					<label>First Name</label>");
		if ((String) request.getAttribute("fname") != null) out.println("					<input class=\"form-control\" type=\"text\" name=\"firstname\" value=\"" + (String) request.getAttribute("fname") + "\" placeholder=\"First Name\">");
		else out.println("					<input class=\"form-control\" type=\"text\" name=\"firstname\" placeholder=\"First Name\">");
		out.println("				</div>");
		
		// Display the lastNameError message if it exists
		out.println("				<div class=\"form-group\">");
		String lastNameError = (String) request.getAttribute("lastNameError");	
		if (lastNameError != null) out.println("					<label>Last Name <small class=\"text-danger\">" + lastNameError + "</small></label>");
		else out.println("					<label>Last Name</label>");
		if ((String) request.getAttribute("lname") != null) out.println("					<input class=\"form-control\" type=\"text\" name=\"lastname\" value=\"" + (String) request.getAttribute("lname") + "\" placeholder=\"Last Name\">");
		else out.println("					<input class=\"form-control\" type=\"text\" name=\"lastname\" placeholder=\"Last Name\">");
		out.println("				</div>");
		
		// Display the usernameError message if it exists
		out.println("				<div class=\"form-group\">");
		String usernameError = (String) request.getAttribute("usernameError");	
		String existingUserError = (String) request.getAttribute("existingUserError");	
		if (usernameError != null) out.println("					<label>Username (E-mail Address) <small class=\"text-danger\">" + usernameError + "</small></label>");
		else if (existingUserError != null) out.println("					<label>Username (E-mail Address) <small class=\"text-danger\">" + existingUserError + "</small></label>");
		else out.println("					<label>Username (E-mail Address)</label>");
		if ((String) request.getAttribute("uname") != null) out.println("					<input class=\"form-control\" type=\"text\" name=\"username\" value=\"" + (String) request.getAttribute("uname") + "\" placeholder=\"Email\">");
		else out.println("					<input class=\"form-control\" type=\"text\" name=\"username\" placeholder=\"Email\">");
		out.println("				</div>");

		// Display the passwordError message if it exists
		out.println("				<div class=\"form-group\">");
		String passwordError = (String) request.getAttribute("passwordError");	
		if (passwordError != null) out.println("					<label>Password <small class=\"text-danger\">" + passwordError + "</small></label>");
		else out.println("					<label>Password</label>");
		out.println("					<input class=\"form-control\" type=\"password\" name=\"password1\" placeholder=\"Password\">");
		out.println("				</div>");
		
		// Display the passwordsMismatchError message if it exists
		out.println("				<div class=\"form-group\">");
		String passwordsMismatchError = (String) request.getAttribute("passwordsMismatchError");	
		if (passwordsMismatchError != null) out.println("					<label>Re-type Password <small class=\"text-danger\">" + passwordsMismatchError + "</small></label>");
		else out.println("					<label>Re-type Password</label>");
		out.println("					<input class=\"form-control\" type=\"password\" name=\"password2\" placeholder=\"Re-type Password\">");
		out.println("				</div>");
		
		out.println("				<button type=\"submit\" class=\"btn btn-primary\">Sign Up</button>");
		out.println("				<br><p>Already a user? <a href=\"/cs3220stu54/cookies/Login\" target=\"_parent\"> Login Here</a>");
		out.println("			</form>");
		
		out.println("        </div>");
		out.println("");
		out.println("    </body>");
		out.println("");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the credentials from the request object
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String username = request.getParameter("username");
		String password1 = request.getParameter("password1");
		String password2 = request.getParameter("password2");
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
				
		// Check if Email Username field is blank.
		if ( username == null || username.trim().length() == 0 ) {
			isFormComplete = false;
			request.setAttribute("usernameError", "You must enter your username (Email), can't be left blank.");
		} else request.setAttribute("uname", username);
		
		// Check if password fields are blank or mismatch.
		if ( password1 == null || password2 == null || password1.trim().length() == 0 || password2.trim().length() == 0 ) {
			isFormComplete = false;
			request.setAttribute("passwordError", "You must enter password, can't be left blank.");
		}
		
		if ( !password1.equals(password2) ) {
			isFormComplete = false;
			request.setAttribute("passwordsMismatchError", "Password and Confirm Password do not match.");
		}
		
		if ( !isFormComplete ) {
			doGet(request, response);
			return;
		}

		// Getting till here means that all fields of SignUp form has been correctly filled.
		// Get the application scope variable students.
		List<Student> students = (ArrayList<Student>) getServletContext().getAttribute("students");
		
		// Before adding new registered student to the list,
		// check if there is an existing user with same username.
		for ( Student student : students ) {
			if ( student.getEmail().trim().toLowerCase().equals(username) ) {
				request.setAttribute("existingUserError", "User with this email address already exists.");
				doGet(request, response);
				return;
			}
		}
		
		// Getting till here means that there is no user with this username, so
		// Add the new user to the list.
		Student newStudent = new Student(firstName, lastName, username, password1);
		students.add(newStudent);
		
		// Save the currently registered student details in session attribute.
		request.getSession().setAttribute("authenticatedStudentCookie", newStudent);
		
		// Redirect to profiles page.
		response.sendRedirect("/cs3220stu54/cookies/MyProfile");
		return;
	}

}