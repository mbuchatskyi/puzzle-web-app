<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>

<style>
        <%@include file="../styles/main.css"%>
 </style>

<body>
	<div class="center">
		<form action = "/puzzle" method = "GET">
		<h1>Welcome to the Puzzle App!</h1>
		<img src="FileServlet?path=D:/images/baseimage.jpg" height="720" width="1280" />
		<br>
		<div class = "center"> 
		<button type="submit" class="center btn-primary btn-lg">Split the image and start</button>
        </div>
        </form>
	</div>
</body>
</html>