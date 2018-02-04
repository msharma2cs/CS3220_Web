<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
	
	<head>
		<!-- meta tags -->
		<meta charset="utf-8">
		<meta name="description" content="Student Profile Application using SQL and Sessions - Signup page, create a new student user.">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	    	<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	
		<!-- Custom Stylesheet : customization.css -->
	   	<link rel="stylesheet" href="/cs3220stu54/css/customization3.css">
	
		<!-- Title of web page -->
	    <title>Student::SignupSQL(Sessions)</title>
	</head>
	
	<body>
		<!-- Header Section -->
		<div class="jumbotron text-center headersection">
		    <div class="container">
		        <h1 class="headerh1">Student Profile <small class="headersmall"> Lab Assignment 4 </small></h1>
		    </div>
		</div>
	
		<!-- Page Content Section -->
		<div class="container">

		<form action="SignupSQL" method="post">
		
			<div class="form-group">
				<label>First Name <c:if test="${not empty firstNameError }"><small class="text-danger">" + firstNameError + "</small></c:if></label>
				<input class="form-control" type="text" name="firstname" <c:if test="${not empty fname }">value="${fname }"</c:if> placeholder="First Name">
			</div>
		
		// Display the lastNameError message if it exists
						<div class="form-group">
		String lastNameError = (String) request.getAttribute("lastNameError	
		if (lastNameError != null) 					<label>Last Name <small class="text-danger">" + lastNameError + "</small></label>
		else 					<label>Last Name</label>
		if ((String) request.getAttribute("lname") != null) 					<input class="form-control" type="text" name="lastname" value="" + (String) request.getAttribute("lname") + "" placeholder="Last Name">
		else 					<input class="form-control" type="text" name="lastname" placeholder="Last Name">
						</div>
		
		// Display the usernameError message if it exists
						<div class="form-group">
		String usernameError = (String) request.getAttribute("usernameError	
		String existingUserError = (String) request.getAttribute("existingUserError	
		if (usernameError != null) 					<label>Username (E-mail Address) <small class="text-danger">" + usernameError + "</small></label>
		else if (existingUserError != null) 					<label>Username (E-mail Address) <small class="text-danger">" + existingUserError + "</small></label>
		else 					<label>Username (E-mail Address)</label>
		if ((String) request.getAttribute("uname") != null) 					<input class="form-control" type="text" name="username" value="" + (String) request.getAttribute("uname") + "" placeholder="Email">
		else 					<input class="form-control" type="text" name="username" placeholder="Email">
						</div>

		// Display the passwordError message if it exists
						<div class="form-group">
		String passwordError = (String) request.getAttribute("passwordError	
		if (passwordError != null) 					<label>Password <small class="text-danger">" + passwordError + "</small></label>
		else 					<label>Password</label>
							<input class="form-control" type="password" name="password1" placeholder="Password">
						</div>
		
		// Display the passwordsMismatchError message if it exists
						<div class="form-group">
		String passwordsMismatchError = (String) request.getAttribute("passwordsMismatchError	
		if (passwordsMismatchError != null) 					<label>Re-type Password <small class="text-danger">" + passwordsMismatchError + "</small></label>
		else 					<label>Re-type Password</label>
							<input class="form-control" type="password" name="password2" placeholder="Re-type Password">
						</div>
		
				<button type="submit" class="btn btn-primary">Sign Up</button>		
			</form>
			<br><p>Already a user? <a href="LoginSQL" target="_parent"> Login Here</a>
		</div>	
	</body>
		
</html>