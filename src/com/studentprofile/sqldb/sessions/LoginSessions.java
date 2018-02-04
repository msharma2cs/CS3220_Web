package com.studentprofile.sqldb.sessions;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import database.utils.DatabaseConnection;
import models.Student;

@WebServlet(description = "Student Profile Application using sessions and sql.", urlPatterns= {"/Student/Sessions/LoginSQL"}, loadOnStartup=3)
public class LoginSessions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// If there is a session for logged in student, redirect back to the Profile page.
		if ( request.getSession().getAttribute("authenticatedStudentSql") != null) {
			response.sendRedirect("MyProfileSQL");
			return;
		}

		String error = (String) request.getAttribute("error");
		request.setAttribute("error", error);
		request.getRequestDispatcher("/WEB-INF/StudentProfile/SessionsLogin.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Get the credentials from the request object
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// If the user submitted bad input, just redisplay the login form.
		if (username == null || username.trim().length() == 0 || password == null || password.trim().length() == 0) {
			request.setAttribute("error", "Either username and/or password field(s) was left blank.");
			request.getRequestDispatcher("/WEB-INF/StudentProfile/SessionsLogin.jsp").forward(request, response);
		}

		// If we get here then the Student submitted a username and password.
		Student student = new Student();
		try {
			Connection c = (new DatabaseConnection()).getServerSQLConnection();
			PreparedStatement preparedStatement =  c.prepareStatement("SELECT * FROM StudentProfile WHERE email = ?  AND password = md5(?)");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if ( !resultSet.next() ) {
				request.setAttribute("error", "Invalid Username and/or Password. Please try again!!!");
				request.getRequestDispatcher("/WEB-INF/StudentProfile/SessionsLogin.jsp").forward(request, response);
			} else {
				student.setId(resultSet.getInt("id"));
				student.setHashcode(resultSet.getString("hashcode"));
				student.setFirstName(resultSet.getString("fname"));
				student.setLastName(resultSet.getString("lname"));
				student.setEmail(resultSet.getString("email"));
				student.setPassword(resultSet.getString("password"));
				double[] scores = new double[5];
				scores[0] = resultSet.getFloat("score_1");
				scores[1] = resultSet.getFloat("score_2");
				scores[2] = resultSet.getFloat("score_3");
				scores[3] = resultSet.getFloat("score_4");
				scores[4] = resultSet.getFloat("score_5");
				student.setScores(scores);	
			}
			
			//Set Session
			request.getSession().setAttribute("authenticatedStudentSql", student);

			resultSet.close();
			preparedStatement.close();
			c.close();
			
			response.sendRedirect("MyProfileSQL");
			return;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}