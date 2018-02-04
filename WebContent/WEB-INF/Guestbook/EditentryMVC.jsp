<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
		
	<head>
		<!-- meta tags -->
		<meta charset="utf-8">
		<meta name="description" content="Guest Book Application - Form to edit an existing entry in Guest Book.">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		
		<!-- Bootstrap CSS -->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
		
		<!-- Custom Stylesheet : customization.css -->
		<link rel="stylesheet" href="/cs3220stu54/css/customization3.css">
		
		<!-- Title of web page -->
    		<title>Edit Entry</title>
	</head>
		
    <body>
		<!-- Header Section -->
		<div class="jumbotron text-center headersection">
		    <div class="container">
		        <h1 class="headerh1">Guest Book<small class="headersmall"> Lab Assignment 3</small></h1>
		    </div>
		</div>
		
       	<!-- Page Content Section -->
       	<div class="container">
   			<h1>Edit Entry</h1><hr>
			<form action="/cs3220stu54/Guestbook/EditEntryMVC" method="post">
				<table>
   					<tr>
     					<td align="left">Name:</td>
     					<td align="left"><input type="text" name="name" value="${name }" /></td>
   					</tr>
   					<tr>
  						<td align="left">Message:</td>
     					<td align="left"><textarea name="message" rows="5" cols="20">${message }</textarea></td>
   					</tr>
				</table>
				<input type="hidden" name="id" value="${id }">
				<hr><input class="btn btn-primary" type="submit" name="addEntry" value="Confirm Edit"><hr>
			</form>
			<br><a class="btn btn-primary" href="/cs3220stu54/Guestbook/GuestBookMVC">Back to GuestBook</a>
       	</div>
	</body>

</html>