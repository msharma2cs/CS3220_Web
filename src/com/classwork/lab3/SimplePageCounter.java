package com.classwork.lab3;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Page Counter", urlPatterns = {"/Introduction/SimplePageCounter"} )
public class SimplePageCounter extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	int count = 0;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Increment the count by 1
		count++;
		
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
		out.println("    	<title>Simple Page Counter</title>");
		out.println("	</head>");
		
		out.println("	<body>");
		out.println("		<div class=\"container\">");
		
		/* Page Body goes here */
		out.println("			<div class=\"page-header\">");
		out.println("    			<h1>Simple Page Counter <small>CS 3220</small></h1>");
		out.println("			</div>");
		
		out.println("			<h3><small>This page has been viewed:</small> " + count + " time(s).</h3>");
		
		out.println("		</div>");
		out.println("</body>");
		
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}