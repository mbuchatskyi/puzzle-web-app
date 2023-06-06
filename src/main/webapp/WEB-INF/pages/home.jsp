<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<title> Home </title>
<style>
        <%@include file="../styles/main.css"%>
 </style>

<body>
	<div class="center">
		<h1>Welcome to the Puzzle App!</h1>
		<h3> Please, choose the image you want to play with:</h3>
		<a href="home?image=1"><img src="FileServlet?path=D:/images/baseimage.jpg" height="360" width="640" /></a>
		<a href="home?image=2"><img src="FileServlet?path=D:/images/baseimage1.jpg" height="360" width="640" /></a>
		<a href="home?image=3"><img src="FileServlet?path=D:/images/baseimage2.jpg" height="360" width="640" /></a>
		<a href="home?image=4"><img src="FileServlet?path=D:/images/baseimage3.jpg" height="360" width="640" /></a>
		<br><br><br>
	</div>
</body>
</html>