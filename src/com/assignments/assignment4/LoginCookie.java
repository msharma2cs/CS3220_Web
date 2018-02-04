package com.assignments.assignment4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Student;

@WebServlet(urlPatterns = "/cookies/Login", loadOnStartup = 4)
public class LoginCookie extends HttpServlet {
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
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);

		// Create a few students
		List<Student> students = new ArrayList<Student>();
		students.add(new Student("John", "Doe", "john@doe.com", "abcd"));
		students.add(new Student("Mary", "Jane", "mary@jane.com", "efgh"));
		students.add(new Student("Joe", "Boxer", "joe@boxer.com", "ijkl"));

		// Add the students to the application scope (Servlet Context)
		this.getServletContext().setAttribute("students", students);
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
		out.println("        <meta name=\"description\" content=\"Student Profile Application - Login page, remembers user by using cookies.\">");
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
		out.println("        <title>Login-Student Profile</title>");
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

		// Display the error message if it exists
		String error = (String) request.getAttribute("error");	
		if (error != null)
			out.println("        	<p class=\"text-center text-danger\">" + error + "</p>");

		// Create the login form
		out.println("        	<form action=\"/cs3220stu54/cookies/Login\" method=\"post\">");
		out.println("				<div class=\"form-group\">");
		out.println("					<label>Username (E-mail Address)</label>");
		out.println("					<input class=\"form-control\" type=\"text\" name=\"username\" placeholder=\"Email\">");
		out.println("				</div>");
		out.println("				<div class=\"form-group\">");
		out.println("					<label>Password</label>");
		out.println("					<input class=\"form-control\" type=\"password\" name=\"password\" placeholder=\"Password\">");
		out.println("				</div>");
		out.println("				<div class=\"checkbox\">");
		out.println("					<label>");
		out.println("						<input type=\"checkbox\" name=\"rememberMe\"> Remember Username");
		out.println("					</label>");
		out.println("				</div>");
		out.println("				<button type=\"submit\" class=\"btn btn-primary\">Login</button>");
		out.println("				<br><p>New User? <a href=\"/cs3220stu54/cookies/Signup\" target=\"_parent\"> Register Here</a>");
		out.println("			</form>");
		out.println("        </div>");
		out.println("");
		out.println("    </body>");
		out.println("");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the credentials from the request object
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String rememberMe = request.getParameter("rememberMe");

		// If the user submitted bad input, just redisplay the form
		if (username == null || username.trim().length() == 0 || password == null || password.trim().length() == 0) {
			doGet(request, response);
			return;
		}

		List<Student> students = (ArrayList<Student>) getServletContext().getAttribute("students");
		
		for (Student student : students) {
			if (student.getEmail().toLowerCase().equals(username.trim().toLowerCase()) && student.getPassword().equals(password)) {
				// Save the currently logged in student details in session attribute.
				request.getSession().setAttribute("authenticatedStudentCookie", student);
				
				// If rememberMe checkbox is selected, create a cookie with hex string hash value of student id.
				if ( rememberMe != null ) {
					Cookie studentCookie = new Cookie("student", student.getHashcode());
					studentCookie.setMaxAge(60*30);
					response.addCookie(studentCookie);
				}
				
				// Redirect to profiles page.
				response.sendRedirect("/cs3220stu54/cookies/MyProfile");
				return;				
			}
		}

		request.setAttribute("error", "Invalid username and/or password");
		doGet(request, response);
	}

}