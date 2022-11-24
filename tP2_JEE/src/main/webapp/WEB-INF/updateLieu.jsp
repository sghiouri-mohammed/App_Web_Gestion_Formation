<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
<title> Ajouter Un formation </title>
</head>

<body style="background-color:brown;">
	<br><br><br>
	
	<section style="background-color: beige; padding: 30px;border-radius:20px;" class="container align-self-center col-md-6">
		<div class="row" >
			<H1 style="text-align:center;font-family: fangsong; text-transform: capitalize;" > Modifier un Lieu <c:out value="${lio }" ></c:out> </H1>
			<form action="UpdateLieuServlet" method="POST">
			  <div class="form-group">
			    <label>ID Lieu</label>
			    <input type="text" name="idL" class="form-control"  placeholder="${lio}">
			  </div>
			  <br>
			  <div class="form-group">
			    <label>Adresse</label>
			    <input type="text" class="form-control" name="adresse" placeholder="Adresse ...">
			  </div>
			  <br>
			  <div class="form-group">
			    <label>Ville</label>
			    <input type="text" class="form-control" name="ville" placeholder="ville ...">
			  </div>
			  <br>
			  <button type="submit" class="btn btn-primary">Submit</button>
			</form>
			<br> <br> <br>
			
			<h4> <c:out value="${success}"> </c:out> </h4>
			<br>
			<a href="AdminServlet"> <button class="btn btn-success">   Back to Dashboard </button> </a>
		</div>			 
	</section>

</body>
</html>