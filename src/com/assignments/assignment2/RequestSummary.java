package com.assignments.assignment2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(description = "Assignment 2 - Request Summary.", urlPatterns = {"/RequestSummary"} )
public class RequestSummary extends HttpServlet {
	private static final long serialVersionUID = 1L;
	  
    	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set the content type
		response.setContentType("text/html");
				
		// Get a reference to the PrintWriter that lets us talk to the client
		PrintWriter out = response.getWriter();
				
		// Generate the HTML
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		
		// Head Section of HTML - START.
		out.println("    <head>");
		out.println("        <!-- meta tags -->");
		out.println("        <meta charset=\"utf-8\">");
		out.println("        <meta name=\"description\" content=\"RequestSummary - Displays request parameters and header information of HHTP Header.\">");
		out.println("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\">");

		// Stylesheets.
		out.println("        <!-- Bootstrap Sandstone Stylesheet (minified version) : bootstrapSandstone.css -->");
		out.println("        <link rel=\"stylesheet\" href=\"/cs3220stu54/css/bootstrapSandstone.css\">");
		out.println("        <!-- Custom Stylesheet : customization.css -->");
		out.println("        <link rel=\"stylesheet\" href=\"/cs3220stu54/css/customization.css\">");
		
		// Title of the page.
		out.println("        <!-- Title of web page -->");
		out.println("        <title>Request Summary</title>");
		out.println("    </head>");
		// Head Section of HTML - END.
		
		// Body Section of HTML - START.
		out.println("    <body>");
		
		// Header Section.
		out.println("        <!-- Header Section -->");
		out.println("        <div class=\"jumbotron headersection\">");
		out.println("            <div class=\"container\">");
		out.println("                <h1 class=\"headerh1\">Request Summary<small class=\"headersmall\"> Lab Assignment 2</small></h1>");
		out.println("            </div>");
		out.println("        </div>");
		
		// Request Type Section.
		out.println("        <!-- Request Type Section -->");
		out.println("        <div class=\"jumbotron container request-type-section\">");
		out.println("            <p>The following <span class=\"request-type\">" + request.getMethod() + "</span> request was submitted on <span class=\"request-type\">" + (new Date()).toString() + "</span>.</p>");
		out.println("        </div>");
		
		// Request Parameters Section.
		out.println("        <!-- Request Parameters Section -->");
		out.println("        <div class=\"container\">");
		out.println("            <h1>Request Parameters</h1>");
		out.println("            <div class=\"table-responsive\">");
		out.println("                <table class=\"table table-bordered\">");
		out.println("                    <col width=\"40%\">");
		out.println("                    <thread>");
		out.println("                        <tr>");
		out.println("                            <th bgcolor=\"#e9ecef\">Parameter Name</th>");
		out.println("                            <th bgcolor=\"#e9ecef\">Parameter Value</th>");
		out.println("                        </tr>");
		out.println("                    </thread>");
		out.println("                    <tbody>");
		
		Enumeration<String> parameterNames = request.getParameterNames();
		if ( !parameterNames.hasMoreElements() ) {
			out.println("                        <tr><td colspan=2 style=\"text-align:center;\">No parameters were passed with this request.</td></tr>");
		} else {
			while ( parameterNames.hasMoreElements() ) {
				out.println("                        <tr>");
				
				String parameterName = parameterNames.nextElement();
				
				out.println("                            <td>" + parameterName + "</td>");
				out.print("                            <td>");
				
				String[] parameterValues = request.getParameterValues(parameterName);
				for ( String param : parameterValues ) {
					out.println("<span class=\"label label-info param-value\">" + param + "</span>");
				}
				
				out.println("                            </td>");
				out.println("                        </tr>");
			}
		}
		out.println("                    </tbody>");
		out.println("                </table>");
		out.println("            </div>");
		out.println("        </div>");
		
		// Header Information Section.
		out.println("        <!-- Header Information Section -->");
		out.println("        <div class=\"container\">");
		out.println("            <h1>Header Information</h1>");
		out.println("            <div class=\"table-responsive\">");
		out.println("                <table class=\"table table-bordered\">");
		out.println("                    <col width=\"40%\">");
		out.println("                    <thread>");
		out.println("                        <tr>");
		out.println("                            <th bgcolor=\"#e9ecef\">Header Field</th>");
		out.println("                            <th bgcolor=\"#e9ecef\">Header Value</th>");
		out.println("                        </tr>");
		out.println("                    </thread>");
		out.println("                    <tbody>");
		
		Enumeration<String> headerFields = request.getHeaderNames();
		while ( headerFields.hasMoreElements() ) {
			out.println("                        <tr>");
			
			String headerField = headerFields.nextElement();
			String headerValue = request.getHeader(headerField);
			
			out.println("                            <td>" + headerField + "</td>");
			out.println("                            <td><span class=\"label label-info param-value\">" + headerValue + "</span></td>");
			out.println("                        </tr>");
		}
		
		out.println("                    </tbody>");
		out.println("                </table>");
		out.println("            </div>");
		out.println("        </div>");
		
		// Footer Section.
		out.println("        <!-- Footer Section -->");
		out.println("        <footer class=\"footer footersection\">");
		out.println("            <div class=\"container\">");
		out.println("                <p>Created by: Mohit Sharma (CIN: 303211238).</p>");
		out.println("                <p>Write @ <a href=\"mailto:msharma2@calstatela.edu\">msharma2@calstatela.edu</a>.</p>");
		out.println("            </div>");
		out.println("        </footer>");
		
		out.println("    </body>");
		// Body Section of HTML - END.
		out.println("</html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}