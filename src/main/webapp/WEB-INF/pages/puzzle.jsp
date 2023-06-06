<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<html>
<style>
  <%@include file="../styles/main.css"%>
</style>
<body>

	<div class="center">
		<h2> Good choice! </h2>
		<h3>Here you can swap the puzzles and detect if they make up the
			base image</h3>
		<div class="form-section">
			<form action="/puzzle" method="GET">
				<a>Swap Puzzles:</a> <br> <select name="prio">
					<c:forEach items="${priorities}" var="entry">
						<option>${entry}</option>
					</c:forEach>
				</select> <a> with </a> <select name="prio2">
					<c:forEach items="${priorities}" var="entry">
						<option>${entry}</option>
					</c:forEach>
				</select><br>

				<button type="submit" class="center btn-primary margin-lform">Swap
					it</button>
			</form>
		</div>

		<div class="container">
			<div class="form-section-right">

				<c:choose>
					<c:when test="${ not empty truePuzzle}">
						<span style="color: #009900;">Puzzle is solved! ;)</span>
						<br>
					</c:when>
				</c:choose>
				
				<c:choose>
					<c:when test="${ not empty falsePuzzle}">
						<span style="color: #ff0000;">It's messed up :(</span>
						<br>
					</c:when>
				</c:choose>
				

			</div>

			<figure>
				<img src="FileServlet?path=D:/images/subimage_0.jpg" height="144"
					width="256">
				<figcaption>1</figcaption>
			</figure>


			<figure>
				<img src="FileServlet?path=D:/images/subimage_1.jpg" height="144"
					width="256">
				<figcaption>2</figcaption>
			</figure>


			<figure>
				<img src="FileServlet?path=D:/images/subimage_2.jpg" height="144"
					width="256">
				<figcaption>3</figcaption>
			</figure>


			<figure>
				<img src="FileServlet?path=D:/images/subimage_3.jpg" height="144"
					width="256">
				<figcaption>4</figcaption>
			</figure>

			<figure>
				<img src="FileServlet?path=D:/images/subimage_4.jpg" height="144"
					width="256">
				<figcaption>5</figcaption>
			</figure>
			<figure>
				<img src="FileServlet?path=D:/images/subimage_5.jpg" height="144"
					width="256">
				<figcaption>6</figcaption>
			</figure>
			<figure>
				<img src="FileServlet?path=D:/images/subimage_6.jpg" height="144"
					width="256">
				<figcaption>7</figcaption>
			</figure>
			<figure>
				<img src="FileServlet?path=D:/images/subimage_7.jpg" height="144"
					width="256">
				<figcaption>8</figcaption>
			</figure>

			<figure>
				<img src="FileServlet?path=D:/images/subimage_8.jpg" height="144"
					width="256">
				<figcaption>9</figcaption>
			</figure>
			<figure>
				<img src="FileServlet?path=D:/images/subimage_9.jpg" height="144"
					width="256">
				<figcaption>10</figcaption>
			</figure>
			<figure>
				<img src="FileServlet?path=D:/images/subimage_10.jpg" height="144"
					width="256">
				<figcaption>11</figcaption>
			</figure>
			<figure>
				<img src="FileServlet?path=D:/images/subimage_11.jpg" height="144"
					width="256">
				<figcaption>12</figcaption>
			</figure>

			<figure>
				<img src="FileServlet?path=D:/images/subimage_12.jpg" height="144"
					width="256">
				<figcaption>13</figcaption>
			</figure>
			<figure>
				<img src="FileServlet?path=D:/images/subimage_13.jpg" height="144"
					width="256">
				<figcaption>14</figcaption>
			</figure>
			<figure>
				<img src="FileServlet?path=D:/images/subimage_14.jpg" height="144"
					width="256">
				<figcaption>15</figcaption>
			</figure>
			<figure>
				<img src="FileServlet?path=D:/images/subimage_15.jpg" height="144"
					width="256">
				<figcaption>16</figcaption>
			</figure>
		</div>
		
		<form action="/solver">
		<button type="submit" class="center btn-primary btn-lg">Let the algorithm do it</button>
        </form>
	</div>

</body>
</html>

