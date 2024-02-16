<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Full Movie Catalog</title>
</head>
<body>
	<h3>Full Movie Catalog</h3>
	
	<form method="post" action="NavigationServlet">
	<table>
	<c:forEach items="${requestScope.allItems}" var="currentitem">
	<tr>
		<td><input type="radio" name="id" value="${currentitem.id}"></td>
		<td>${currentitem.title}</td>
		<td>${currentitem.director}</td>
		<td>${currentitem.genre}</td>
	</tr>
	</c:forEach>
	</table>
	<input type="submit" value="Edit Movie" name="doThisToMovie">
	<br>
	<input type="submit" value="Delete Movie" name="doThisToMovie">
	<br>
	<input type="submit" value="Add Movie" name="doThisToMovie">
	</form>
</body>
</html>