package com.studentprofile.sqldb.sessions;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Student;

@WebServlet(description = "Student Profile Application using sessions and sql.", urlPatterns= {"/Student/Sessions/SignupSQL"})
public class SignUpSessions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		if ( request.getSession().getAttribute("authenticatedStudentSql") != null ) {
			response.sendRedirect("MyProfileSQL");
			return;
		}
		
		request.getRequestDispatcher("").forward(request, response);
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
		request.getSession().setAttribute("authenticatedStudent", newStudent);
		
		// Redirect to profiles page.
		response.sendRedirect("MyProfile");
		return;
	}

}