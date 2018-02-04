<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">

	<head>
        <!-- meta tags -->
        <meta charset="utf-8">
        <meta name="description" content="el-expressions.jsp file to display the EL expressions and the results in a table.">
        <meta name="viewport" content="wilih=device-wilih, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

        <!-- Custom Stylesheet : customization.css -->
        <link rel="stylesheet" href="/cs3220stu54/css//customization3.css">

        <!-- Title of web page -->
        <title>JSP::EL Evaluation</title>
    </head>

	<body>
		<!-- Header Section -->
        <div class="jumbotron text-center headersection">
            <div class="container">
                <h1 class="headerh1">EL Expressions JSP <small class="headersmall"> Lab Assignment 5</small></h1>
            </div>
        </div>

        <!-- Page Content Section -->
        <div class="container">
        		<h1 class="text-center">EL Evaluations and Auto Type Conversions</h1><hr>
        		
        		<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<tr>
						<th bgcolor="#e9ecef">Expression</th>
						<th bgcolor="#e9ecef">Result</th>
					</tr>
					
					<tr>
						<td>\${2+4/2}</td>
						<td>${2+4/2}</td>
					</tr>
					
					<tr>
						<td>\${2+3/2}</td>
						<td>${2+3/2}</td>
					</tr>
					
					<tr>
						<td>\${"2"+3/2}</td>
						<td>${"2"+3/2}</td>
					</tr>
					
					<tr>
						<td>\${"2" + 3 div 2}</td>
						<td>${"2" + 3 div 2}</td>
					</tr>
					
					<tr>
						<td>\${"a" + 3 div 2}</td>
						<td>
						<% try { %>
						${"a" + 3 div 2}
						<% } catch( Exception e ) { %>
							Adding String and Integer Error : java.lang.NumberFormatException: For input string: "a"
						<% } %></td>
					</tr>
					
					<tr>
						<td>\${ null == 'test' }</td>
						<td>${ null == 'test' }</td>
					</tr>
					
					<tr>
						<td>\${ null eq 'test' }</td>
						<td>${ null eq 'test' }</td>
					</tr>
					
					<tr>
						<td>\${ empty "" }</td>
						<td>${ empty "" }</td>
					</tr>
					
					<tr>
						<td>\${ empty null }</td>
						<td>${ empty null }</td>
					</tr>
					
					<tr>
						<td>\${ empty "null" }</td>
						<td>${ empty "null" }</td>
					</tr>
					
					<tr>
						<td>\${ "abc" lt 'b' }</td>
						<td>${ "abc" lt 'b' }</td>
					</tr>
					
					<tr>
						<td>\${ "cs320" > "cs203" }</td>
						<td>${ "cs320" > "cs203" }</td>
					</tr>
				</table>
			</div>
		
        </div>
	</body>

</html>