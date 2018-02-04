package com.studentprofile.javaarrays.cookies;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Student Profile Application using cookies and java arraylist.", urlPatterns = {"/Student/Cookies/Logout"})
public class LogoutCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Delete the cookie on logout.
		Cookie[] cookies = request.getCookies();
		for ( Cookie cookie : cookies ) {
			if ( cookie.getName().equals("student") ) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}
		}
		
		// Delete/Invalidate the session.
		request.getSession().invalidate();
		
		// Redirect to Login page.
		response.sendRedirect("Login");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}