<%@ page pageEncoding="UTF-8" %>
<%@ page import="mediatheque.Utilisateur"%>
<%
Utilisateur user = (Utilisateur) session.getAttribute("user");
%>
<!DOCTYPE html>
<html>
<%@ include file="../common/head.jsp"%>

<body class="bg-dark">
    <div class="container">
        <div class="card mt-5">
            <h3 class="card-header">Fonctions de <%=user.toString()%>
            <button type="button" onclick="location.href='bibliotheque/?action=logoff'"class="btn btn-danger float-right">Logoff</button>
            </h3>
            <div class="card-body"></div>
        </div>
    </div>
</body>

</html>