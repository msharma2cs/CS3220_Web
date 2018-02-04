<%@ page contentType="text/html" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<%-- set data source --%>
<sql:setDataSource
	driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost/cs3220stu54"
	user="cs3220stu54"
	password="i!AWULT5" />

<%-- query --%>
<sql:query var="items" sql="SELECT * FROM items"/>

<!DOCTYPE HTML>
<HTML>

	<HEAD>
		<TITLE>Hello JDBC</TITLE>
	</HEAD>

	<body>
		<table border="1">
			<tr>
				<th>Key</th>
				<th>Value</th>
			</tr>
			<c:forEach items="${items.rows}" var="row">
				<c:forEach items="${row}" var="col">
		      		<tr>
		        			<td>${col.key}</td>
		        			<td>${col.value}</td>
		      		</tr>
		    		</c:forEach>
		  	</c:forEach>
		</table>
	</body>

</HTML>