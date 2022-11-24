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

<style>
	th{
		text-align:center;
	}
	.right{
		margin-left:100px;
	}
</style>

<body style="background-color: brown;">
	<br><br><br>
	
	<section style="background-color: beige; padding: 30px;border-radius:20px;" class="container align-self-center col-md-11">
		<div class="row" >
			<div class="col-md-5 align-self-center">
				<H1 style="text-align:center;font-family: fangsong; text-transform: capitalize;" > Ajouter une formation </H1>
				<form action="InsererFormationServlet" method="POST">
				  <div class="form-group">
				    <label>ID</label>
				    <input type="text" name="idF" class="form-control"  placeholder="Code de la formation">
				  </div>
				  <br>
				  <div class="form-group">
				    <label>Theme</label>
				    <input type="text" class="form-control" name="theme" placeholder="Theme de la formation">
				  </div>
				  <br>
				  <div class="form-group">
				    <label>Lieu de Formation</label>
				    <input type="text" class="form-control" name="lieuForm" placeholder="Lieu de la formation">
				  </div>
				  <br>
				  <button type="submit" class="btn btn-primary">Submit</button>
				</form>
				<br> <br>
				<h4> <c:out value="${success}" > </c:out> </h4>
			</div>
			<div class="col-md-5 right">
				<H1 style="text-align:center;font-family: fangsong; text-transform: capitalize;" > Liste des Lieux dispo </H1>
				<table class="table">
				  <thead>
				    <tr>
				      <th align="center">ID</th>
				      <th align="center">Adresse</th>
				    </tr>
				  </thead>
				  <tbody>
				  	<c:forEach var="lieu" items="${lieux}" > 
					  	<tr>
					      <th align="center"> <c:out value="${ lieu.id }"> </c:out> </th>
					      <td align="center"><c:out value="${ lieu.adresse }"> </c:out></td>
					    </tr>
				  	</c:forEach>
				  </tbody>
				</table>
			</div>
			<br>
			<a href="AdminServlet"> <button class="btn btn-success">   Back to Dashboard </button> </a>
		</div>	 
	</section>

</body>
</html>