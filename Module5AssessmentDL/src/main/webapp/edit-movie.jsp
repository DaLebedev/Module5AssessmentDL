<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Movie Data</title>
</head>
<body>
	<h3>Enter New Movie Data Below:</h3>
	<form action = "EditMovieServlet" method="post">
		Title: <input type ="text" name = "title" value= "${movieToEdit.title}">
		<br>
		Director: <input type = "text" name = "director" value= "${movieToEdit.director}">
		<br>
		Genre: <input type = "text" name = "genre" value= "${movieToEdit.genre}">
		<input type = "hidden" name = "id" value="${movieToEdit.id}">
		<input type = "submit" value="Save Edited Item">
	</form>
</body>
</html>