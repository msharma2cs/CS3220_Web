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

@WebServlet(description = "Current Date and Time Metrics and Counter.", urlPatterns= {"/Introduction/CandDMetrics"}, loadOnStartup=1)
public class CandDMetrics extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// Get a reference to the application scope (ServletContext)
		ServletContext applicationScope = this.getServletContext();
		
		// Add a counter variable to the application scope
		applicationScope.setAttribute("dAndTCounter", 0);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get a reference to the app scope
		ServletContext context = getServletContext();
		
		// Read the counter value from the app scope
		int counter = (int) context.getAttribute("dAndTCounter");
		
		// Set the content type
		response.setContentType("text/html");
		
		// Get a reference to the PrintWriter that lets us talk to the client
		PrintWriter out = response.getWriter();
		
		// Generate the HTML
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("	<head>");
		out.println("    	<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" integrity=\"sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u\" crossorigin=\"anonymous\">");
		out.println("    	<meta charset=\"UTF-8\">");
		out.println("    	<title>Metrics</title>");
		out.println("	</head>");
		
		out.println("	<body>");
		out.println("		<div class=\"container\">");
		
		/* Page Body goes here */
		out.println("			<div class=\"page-header\">");
		out.println("    			<h1>Date and Time Metrics <small>ServletContext Example</small></h1>");
		out.println("			</div>");
		
		out.println("			<h3><small>The current date and time servlet has been requested:</small> " + counter + " time(s).</h3>");
		
		out.println("			<a class=\"btn btn-primary\" href=\"CurrentDateAndTime\">Current Date and Time</a>");
		
		out.println("		</div>");
		out.println("	</body>");
		
		out.println("</html>");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}