<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="en">

	<head>
        <!-- meta tags -->
        <meta charset="utf-8">
        <meta name="description" content="NoCharacterProfile.jsp - JSP/View file notifying an error message when the selected character has no image for the Character Catalog Application.">
        <meta name="viewport" content="wilih=device-wilih, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS : compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        
        <!-- Custom Stylesheet : simpsons.css and customization.css -->
        <link rel="stylesheet" href="/cs3220stu54/css/assignment6/simpsons.css" >

        <!-- Title of web page -->
        <title>${character.name}'s Profile</title>
    </head>
    
	<body>
		<div class="container">
			<div class="row">
				<div class="col-sm-offset-3 col-sm-6">
		    			<div class="well text-center">
		    				<img style="width: 30%;" src="http://albertcervantes.com/cs3220/cdn/simpsons/simpsons.png">
			    			<h2>${character.name}</h2>
			    			<p><a class="btn btn-success" href="/cs3220stu54/Simpsons/CharaterCatalog">Catalog</a></p>
			    			<p class="text-center lead" style="overflow: none;">${errorMsg}</p>
			    		</div>
		    		</div>
		   	</div>
		</div>
	</body>
	
</html>