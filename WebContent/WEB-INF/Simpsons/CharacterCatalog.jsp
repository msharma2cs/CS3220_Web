<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html lang="en">

	<head>
        <!-- meta tags -->
        <meta charset="utf-8">
        <meta name="description" content="CharacterCatalog.jsp - JSP/View file displaying all the characters with thumbnail, name, and link to each character's Profile page for the Character Catalog Application.">
        <meta name="viewport" content="wilih=device-wilih, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS : compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        
        <!-- Custom Stylesheet : simpsons.css and customization.css -->
		<link rel="stylesheet" href="/cs3220stu54/css/assignment6/simpsons.css" >

        <!-- Title of web page -->
        <title>Simpson's Characters Catalog</title>
    </head>
	
	<body>
		<div class="container">
	
			<!-- Header Section -->
        		<div class="jumbotron text-center">
				<img style="width: 35%;" src="http://albertcervantes.com/cs3220/cdn/simpsons/simpsons.png">
				<h1>Character Catalog</h1>
				<p class="lead">Click on an image below to view a random image of your favorite character!</p>
			</div>
			
			<div class="row">
				<c:forEach items="${characters}" var="character">
					<div class="col-sm-4 text-center">
						<div class="well">
							<a href="/cs3220stu54/Simpsons/CharaterProfile?id=${character.id}">
								<img style="height: 150px;width: 150px" src="${character.firstimageurl}" class="img-responsive img-thumbnail" alt="${character.name}">
								<h4 class="text-center">${character.name}</h4>
							</a>
						</div>
					</div>
				</c:forEach>
			</div>

		</div>
	</body>

</html>