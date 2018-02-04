package com.classwork.lab5;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Countdown", urlPatterns = {"/requests/Countdown"})
public class Countdown extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private int count = 5;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		/* Page Title goes here */
		out.println("    	<title>Countdown</title>");
		out.println("	</head>");
		
		/* Page Body goes here */
		out.println("	<body>");
		out.println("		<div class=\"container\">");
		
		out.println("			<div class=\"page-header\">");
		out.println("    			<h1>Countdown <small>HttpServletResponse</small></h1>");
		out.println("			</div>");
		
		if ( count > 0 ) {
			response.setIntHeader("Refresh", 1);
			out.println("			<h3>T-minus " + count + " seconds to launch.");
			count--;
		} else {
			out.println("			<h3 class=\"text-danger\">Blast Off!</h3>");
			count = 5; // Reset the count
		}
			 
		out.println("		</div>");
		out.println("	</body>");
		
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}