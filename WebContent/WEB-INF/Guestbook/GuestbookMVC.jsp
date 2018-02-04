<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">

	<head>
    		<!-- meta tags -->
        <meta charset="utf-8">
        <meta name="description" content="Guest Book Application - Displays a list of entries in Guest Book.">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

        <!-- Custom Stylesheet : customization.css -->
        <link rel="stylesheet" href="/cs3220stu54/css/customization3.css">

        <!-- Title of web page -->
        <title>Guest Book</title>
    </head>

    <body>
        <!-- Header Section -->
        <div class="jumbotron text-center headersection">
            <div class="container">
                <h1 class="headerh1">Guest Book <small class="headersmall"> Lab Assignment 3</small></h1>
            </div>
        </div>

        <!-- Page Content Section -->
        <div class="container">
        	<h1>Guest Book</h1><hr>
		
		<form class="form-inline" action="GuestBookMVC" method="GET">
			<div class="form-group">
            		<input class="form-control" style="margin-right:1rem;" type="text" name="query" value="" placeholder="Enter your search term(s)" size="30">
                	<input class="form-control btn btn-primary" type="submit" value="Search">
            	</div>
			</form><hr>

            <div class="table-responsive">
            		<table class="table table-bordered table-hover">
                		<tr>
                    		<th bgcolor="#e9ecef">Name</th>
                        	<th bgcolor="#e9ecef">Message</th>
                        	<th bgcolor="#e9ecef">Date</th>
                        	<th bgcolor="#e9ecef">Actions</th>
                   	</tr>
					<c:forEach var="a" items="${guestbook}">
						<tr>
				        		<td width="20%">${ a.name }</td>
				            	<td width="40%">${ a.message }</td>
		                   	<td width="20%">${ a.created }</td>
		                    	<td width="20%"><a class="btn btn-primary" href="/cs3220stu54/Guestbook/EditEntryMVC?id=${a.id}">Edit</a> | <a class="btn btn-primary" href="/cs3220stu54/Guestbook/DeleteEntryMVC?id=${a.id}">Delete</a></td>
		                </tr>
		        		</c:forEach>
				</table>
            </div>
			<a class="btn btn-primary" href="/cs3220stu54/Guestbook/AddEntryMVC">Add New Entry</a>
        </div>
    </body>

</html>