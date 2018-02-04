package com.classwork.lab3;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Current date and time page.", urlPatterns= {"/Introduction/CurrentDateAndTime"})
public class CurrentDateAndTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get a reference to the application scope
		ServletContext applicationScope = this.getServletContext();
		
		// Read the current counter value
		int counter = (int) applicationScope.getAttribute("dAndTCounter");
		
		// Increment the counter and store it back in the app scope
		counter++;
		applicationScope.setAttribute("dAndTCounter", counter);
		
		// Set the content type
		response.setContentType("text/html");
		
		// Get a reference to the PrintWriter that lets us talk to the client
		PrintWriter out = response.getWriter();
		
		// Generate the HTML
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("	<head>");
		out.println("   		<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");
		out.println("    	<meta charset=\"UTF-8\">");
		out.println("    	<title>Current Date and Time</title>");
		out.println("	</head>");
		
		out.println("	<body>");
		out.println("		<div class=\"container\">");
		
		/* Page Body goes here */
		out.println("			<div class=\"page-header\">");
		out.println("    			<h1>Current Date and Time <small>ServletContext Example</small></h1>");
		out.println("			</div>");
		
		out.println("			<h3><small>The current date and time is:</small> " + new java.util.Date() + "</h3>");
		
		out.println("			<a class=\"btn btn-primary\" href=\"CandDMetrics\">Metrics</a>");
		
		out.println("		</div>");
		out.println("	</body>");
		
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}