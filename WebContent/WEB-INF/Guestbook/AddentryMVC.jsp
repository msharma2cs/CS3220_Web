<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

	<head>
		<!-- meta tags -->
		<meta charset="utf-8">
		<meta name="description" content="Guest Book Application - Form to add a new entry in Guest Book.">
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

        <!-- Custom Stylesheet : customization.css -->
        <link rel="stylesheet" href="/cs3220stu54/css/customization3.css">

        <!-- Title of web page -->
        <title>Add Entry</title>
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
	    		<h1>Add Entry</h1><hr>
			<form action="/cs3220stu54/Guestbook/AddEntryMVC" method="post">
				<table>
				
					<c:if test="${!isFirstCall && isNameNull}">
	  					<tr><td colspan=2 align="left"><p class="text-danger">You must specify a name.</p></td></tr>
	  				</c:if>
	  					
	  					<tr>
			      			<td align="left">Name:</td>
			      	<c:choose>		
			      	<c:when test="${!isFirstCall && !isNameNull}">
			      			<td align="left"><input type="text" name="name" value="${name}" size="21"/></td>
			      	</c:when>
			      	<c:otherwise>
			      			<td align="left"><input type="text" name="name" size="21"/></td>
			      	</c:otherwise>
			      	</c:choose>			
	  					</tr>
	  					
	  				<c:if test="${!isFirstCall && isMessageNull}">
	  					<tr><td colspan=2 align="left"><p class="text-danger">You must enter a message.</p></td></tr>
	  				</c:if>
	
	  					<tr>
			      			<td align="left">Message:</td>
			      	<c:choose>		
			      	<c:when test="${!isFirstCall && !isMessageNull}">
			      			<td align="left"><textarea name="message" rows="5" cols="20">${message}</textarea></td>
			      	</c:when>
			      	<c:otherwise>
			      			<td align="left"><textarea name="message" rows="5" cols="20"></textarea></td>
			      	</c:otherwise>
			      	</c:choose>			
	  					</tr>
	  			</table>
				<input class="btn btn-primary" type="submit" name="addEntry" value="Add Entry"><hr>
			</form>
			<br><a class="btn btn-primary" href="/cs3220stu54/Guestbook/GuestBookSQL">Back to GuestBook</a>
	    </div>
		
	</body>
		
</html>