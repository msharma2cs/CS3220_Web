<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- meta tags -->
		<meta charset="utf-8">
		<meta name="description" content="Student Profile Application using SQL and Sessions - Login page.">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	    	<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	
		<!-- Custom Stylesheet : customization.css -->
	   	<link rel="stylesheet" href="/cs3220stu54/css/customization3.css">
	
		<!-- Title of web page -->
	    <title>Student::LoginSQL(Sessions)</title>
	</head>

	<body>
		<div class="container">

			<div class="page-header">
  				<h1>Login <small>HttpSessions</small></h1>
			</div>

			<c:if test="${not empty error }">
				<p class="text-center text-danger">${error }</p>
			</c:if>
			
			<form action="LoginSQL" method="post">
				<div class="form-group">
					<label>Username (E-mail Address)</label>
					<input class="form-control"type="text" name="username" placeholder="Email">
				</div>
				<div class="form-group">
					<label>Password</label>
					<input class="form-control"type="password" name="password" placeholder="Password">
				</div>
				<button type="submit" class="btn btn-primary">Login</button>
			</form>
			<br><p>New User? <a href="SignupSQL" target="_parent"> Register Here</a>
		</div>
	</body>

</html>