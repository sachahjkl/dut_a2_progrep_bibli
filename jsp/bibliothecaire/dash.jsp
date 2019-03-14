<%@page import="java.math.BigInteger"%>
<%@page import="java.security.MessageDigest"%>
<%@page import="java.util.List"%>
<%@page import="mediatheque.Document"%>
<%@page import="mediatheque.Mediatheque"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="mediatheque.Utilisateur"%>
<%
	Utilisateur user = (Utilisateur) session.getAttribute("user");
	List<Document> docs = Mediatheque.getInstance().tousLesDocuments();
	MessageDigest md = MessageDigest.getInstance("MD5");
%>
<!DOCTYPE html>
<html>
<%@ include file="../common/head.jsp"%>

<body class="bg-dark">
	<div class="container">
		<div class="card mt-5">
			<h3 class="card-header">
				Fonctions de
				<%=user.toString()%>
				<button type="button"
					onclick="location.href='/bibliotheque?action=logoff'"
					class="btn btn-danger float-right">Logoff</button>
			</h3>
			<div class="card-body">

				<div class="card mt-4">
					<h3 class="card-header">Ajout d'un document</h3>
					<div class="card-body">
						<form action="?action=addDoc" method="post" accept-charset="UTF-8">
							<div class="form-group">
								<select class="form-control" name="type">
									<option selected="selected" value="0">Livre</option>
									<option value="1">CD</option>
									<option value="2">DVD</option>
								</select>
							</div>
							<div class="form-group">
								<label for="nomDoc">Nom du document</label> <input type="text"
									class="form-control" name="nomDoc"
									placeholder="Saisissez le nom du document" required>
							</div>
							<div class="form-group">
								<label for="auteurDoc">Auteur du document</label> <input
									type="text" class="form-control" name="auteurDoc"
									placeholder="Saisissez l'auteur du document" required>
							</div>
							<button type="submit" class="btn btn-primary">Créer
								document</button>
						</form>
					</div>
				</div>

				<%
					for (Document d : docs) {

						Object[] elements = d.affiche();
						String id = elements[1].toString().replace(" ", "_");
						byte[] messageDigest = md.digest(id.getBytes());
						BigInteger no = new BigInteger(1, messageDigest);
						id = no.toString(16);
						while (id.length() < 32) {
							id = "0" + id;
						}
						id = "ID" + id;
						String type = d.toString().split(" ")[0];
				%>
				<div class="card shadow-sm mt-2">
					<h5 class="card-header align-items-center">
						<a class="text-dark" data-toggle="collapse" href=<%="#" + id%>
							role="button" aria-expanded="false" aria-controls=<%=id%>> <i
							class="fas fa-plus"></i></a> (<%=elements[3].equals(0) ? "Dispo" : "Emprunté"%>)
						<%=type%>
						n°<%=elements[0]%>
						:
						<%=elements[1]%>
					</h5>
					<div class="collapse" id=<%=id%>>
						<div class="card-body">
							<ul>
								<li>N° : <%=elements[0]%></li>
								<li>Titre : <%=elements[1]%></li>
								<li>Auteur : <%=elements[2]%></li>
								<li>Etat d'emprunt : <%=(elements[3].equals(0) ? "Disponible" : "Emprunté par l'abonné n°" + elements[3])%></li>
							</ul>

						</div>
					</div>
				</div>
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>

</html>