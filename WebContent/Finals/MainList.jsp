<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
	<link rel="stylesheet" href="/cs3220stu54/css/finals/customization.css">
	<title>Final:: Articles</title>
</head>

<body>
	
	<br/><div class="container">
	
		<div class="jumbotron">
			<h2>Add a Link to an article.</h2>
			<form action="/cs3220stu54/AddLink" method="post">
				<div class="form-group">
			    		<label for="title">Title</label>
			    		<input type="text" class="form-control" name="title" id="title" placeholder="Enter Title">
			  	</div>
			  	<div class="form-group">
			    		<label for="link">Link</label>
			    		<input type="text" class="form-control" name="link" id="link" placeholder="Enter Link">
			  	</div>
			 	<button type="submit" class="btn btn-default btn-primary">Add a Link</button>
			</form>
		</div>
		
		<br/><hr/><br/>
		
		<c:forEach var="a" items="${articles}">
		<div class="row">
			<div class="col-8">
				<div class="jumbotron jumbotron-fluid" >
					<p>${a.title}</p>
					<p>Link to article : <a href="${a.link }">${a.link }</a></p>
					<div class="row">
						<div class="col-4"><p>Votes : ${a.votes }</p></div>
						<div class="col-8">
							<button type="button" class="btn btn-sm btn-success" onclick="location.href = '/cs3220stu54/Upvote?id=${a.id }';">Upvote</button> <button type="button" class="btn btn-sm btn-danger" onclick="location.href = '/cs3220stu54/Downvote?id=${a.id }';">Downvote</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		</c:forEach>

	</div>
</body>

</html>