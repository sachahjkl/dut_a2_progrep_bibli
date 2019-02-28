<%@page import="java.util.List"%>
<%@page import="mediatheque.Document"%>
<%@page import="mediatheque.Mediatheque"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="mediatheque.Utilisateur"%>
<%
	Utilisateur user = (Utilisateur) session.getAttribute("user");
	List<Document> docs = Mediatheque.getInstance().tousLesDocuments();
	System.out.println(Mediatheque.getInstance().tousLesDocuments());
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
				<%
					for (Document d : docs) {
						Object[] elements = d.affiche();
						String id = elements[1].toString().replace(" ", "_");
				%>
				<div class="card shadow-sm mt-2">
					<h5 class="card-header align-items-center">
						<a class="text-dark" data-toggle="collapse" href=<%="#" + id%>
							role="button" aria-expanded="false" aria-controls=<%=id%>> <i
							class="fas fa-plus"></i></a> Document n°<%=d.affiche()[0]%>
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