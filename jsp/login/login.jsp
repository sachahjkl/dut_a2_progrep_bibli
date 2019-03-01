<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<%
	String error = (String) request.getAttribute("error");
%>
<html>
<%@ include file="../common/head.jsp"%>

<body class="bg-dark">
	<div class="container">
		<div class="card shadow-sm m-5">
			<h3 class="card-header">Service de connexion de la bibliotheque
				:</h3>
			<div class="card-body">
				<form action="?action=login" method="post">
					<div class="form-group">
						<label for="login">Login</label> <input type="text"
							class="form-control" name="login" id="login"
							placeholder="Saisissez votre login" required>
					</div>
					<div class="form-group">
						<label for="password">Mot de passe</label> <input type="password"
							class="form-control" name="password" id="password"
							placeholder="Saisissez votre mot de passe" required>
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
				<%
					if (error != null) {
				%>
				<div class="alert alert-danger my-3" role="alert">
					<%=error%>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>

</html>