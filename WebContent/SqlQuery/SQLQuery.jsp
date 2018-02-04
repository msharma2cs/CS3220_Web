<%@ page contentType="text/html"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>

<%-- set data source --%>
<sql:setDataSource 
	driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://cs3.calstatela.edu/cs3220stu54" 
	user="cs3220stu54"
	password="i!AWULT5" />

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>

	<head>
		<title>JSTL SQL Query</title>

		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet"
			href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
			integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
			crossorigin="anonymous">
	</head>

	<body>
		<div class="container">

			<c:if test="${not empty param.sql}">
				<c:catch var ="catchException">
					<sql:query var="items" sql="${param.sql}" />
				</c:catch>
			</c:if>
				
			<div class="page-header">
				<h3>SQL Query</h3>
			</div>
	
			<div class="well">
				<c:if test = "${catchException != null}">
					<p class="lead text-danger">Invalid Query: <code>${param.sql}</code></p>
		      	</c:if>
				
				<form action="SQLQuery.jsp" method="post">
					<div class="form-group">
						<textarea class="form-control" rows="5" name="sql"></textarea>
					</div>
					<div class="text-center">
						<input class="btn btn-primary" type="submit" name="submit" value="Execute Query">
					</div>
				</form>
			</div>
			
			<c:if test="${not empty param.sql}">
				<c:set var="rowCount" value="0" scope="page"/>
				<c:forEach items="${items.rows}" var="row">
					<c:set var="rowCount" value="${rowCount + 1 }" scope="page"/>
				</c:forEach>
				<c:if test="${rowCount gt 1}">
					<p><strong><c:out value="${rowCount}"/> record(s) </strong> returned for : <code><c:out value="${param.sql}"/></code></p>
				</c:if>
				<div class="table-responsive">
					<table class="table table-bordered table-hover">
						<tr>
							<c:forEach items="${items.rows[0] }" var="col">
								<th bgcolor="#e9ecef">${col.key}</th>
							</c:forEach>
						</tr>
						
						<c:forEach items="${items.rows}" var="row">
							<tr>
							<c:forEach items="${row}" var="col">
								<td>${col.value}</td>
							</c:forEach>
							</tr>
						</c:forEach>
						
					</table>
				</div>
				
			</c:if>
	
		</div>
	</body>

</HTML>