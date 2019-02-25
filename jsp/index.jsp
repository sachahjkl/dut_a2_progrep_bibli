<!DOCTYPE html>
<%@ page import="mediatheque.Utilisateur" %>
<%@ page import="mediatheque.Mediatheque" %>
<%-- <%@ page import="persistantdata.MediathequeData"%> --%>
<%
session.setAttribute("mediatheque", Mediatheque.getInstance());
Utilisateur cUser = (Utilisateur) session.getAttribute("cUser");
String err_message = (String) session.getAttribute("errMessage");
Mediatheque m = Mediatheque.getInstance();
//Class.forName("persistantdata.MediathequeData");
System.out.println(m.tousLesDocuments());
session.setAttribute("errMessage", null);
if(cUser != null){
	if(cUser.isBibliothecaire())
		 response.sendRedirect("./bibliothecaire/dash.jsp");
	else
		response.sendRedirect("./abonne/dash.jsp");
}
%>

<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible">
	<title>Connexion</title>
	<link rel="stylesheet" href="">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
	<div class="container">
		<h1>Service de connexion de la bibliotheque :</h1>
		<form action="connect.jsp" method="post">
		  <div class="form-group">
		    <label for="login">Login</label>
		    <input type="text" class="form-control" name="login" id="login" aria-describedby="emailHelp" placeholder="Saisissez votre login" required>
		  </div>
		  <div class="form-group">
		    <label for="password">Mot de passe</label>
		    <input type="password" class="form-control" name="password"id="password" placeholder="Saisissez votre mot de passe" required>
		  </div>
		  <button type="submit" class="btn btn-primary">Submit</button>
		</form>
		<%= "Utilisateur : " + cUser%>
		<% if(err_message != null){%>
		<div class="alert alert-danger my-3" role="alert">
		  <%=err_message%>
		</div>
		<%}%>
	</div>
</body>
</html>