<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="mediatheque.Document"%>
<%@page import="mediatheque.Mediatheque"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="mediatheque.Utilisateur"%>
<%
	Utilisateur user = (Utilisateur) session.getAttribute("user");
	List<Document> empruntables = new ArrayList<>();
	List<Document> retournables = new ArrayList<>();
	Object[] doc = null;
	String type;
	int id = Integer.parseInt(user.toString().split(" ")[1]);
	for (Document d : Mediatheque.getInstance().tousLesDocuments()) {
		int idDoc = (int) d.affiche()[3];
		if (idDoc == id)
			retournables.add(d);
		else if (idDoc == 0)
			empruntables.add(d);
	}
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
					onclick="location.href='bibliotheque/?action=logoff'"
					class="btn btn-danger float-right">Logoff</button>
			</h3>
			<div class="card-body">
				<h5 class="mt-2">Emprunter des documents :</h5>
				<%
					if (empruntables.isEmpty()) {
				%>
				<p>Aucun document n'est disponible à l'emprunt</p>
				<%
					} else {
				%>
				<form action="?action=emprunt" method="post" accept-charset="UTF-8">
					<div class="input-group mb-3">
						<select class="form-control" name="emprunt"
							aria-label="Document à emprunter" aria-describedby="btnEmprunt">
							<%
								doc = empruntables.get(0).affiche();
									type = empruntables.get(0).toString().split(" ")[0];
							%>
							<option selected="selected" value=<%=doc[0]%>>
								<%=type + " " + doc[1] + " " + doc[2]%>
							</option>
							<%
								for (int i = 1; i < empruntables.size() - 1; ++i) {
										doc = empruntables.get(i).affiche();
										type = empruntables.get(i).toString().split(" ")[0];
							%>
							<option selected="selected" value=<%=doc[0]%>>
								<%=type + " " + doc[1] + " " + doc[2]%>
							</option>
							<%
								}
							%>
						</select>
						<div class="input-group-append">
							<button type="submit" id="btnEmprunt"
								class="btn btn-primary input-group-text">Emprunter document</button>
						</div>
					</div>
				</form>
				<%
					}
				%>
				<hr>

				<h5 class="mt-3">Retour des documents :</h5>

				<%
					if (retournables.isEmpty()) {
				%>
				<p>Vous n'avez aucun document à retourner</p>
				<%
					} else {
				%>
				<form action="?action=retour" method="post" accept-charset="UTF-8">
					<div class="input-group mb-3">
						<select class="form-control" name="retour"
							aria-label="Document à emprunter" aria-describedby="btnRetour">
							<%
								doc = retournables.get(0).affiche();
									type = retournables.get(0).toString().split(" ")[0];
							%>
							<option selected="selected" value=<%=doc[0]%>>
								<%=type + " " + doc[1] + " " + doc[2]%>
							</option>
							<%
								for (int i = 1; i < retournables.size(); ++i) {
										doc = retournables.get(i).affiche();
										type = retournables.get(i).toString().split(" ")[0];
							%>
							<option selected="selected" value=<%=doc[0]%>>
								<%=type + " " + doc[1] + " " + doc[2]%>
							</option>
							<%
								}
							%>
						</select>
						<div class="input-group-append">
							<button type="submit" id="btnRetour"
								class="btn btn-primary input-group-text">Retourner
								document</button>
						</div>
					</div>
				</form>
				<%
					}
				%>
			</div>
		</div>
	</div>
</body>

</html>