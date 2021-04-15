<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   

<c:if test="${ utilisateur.getClass().getName() != 'com.example.demo.model.Stagiaire' }"><c:redirect url="/accueil"></c:redirect></c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajout d'un compte</title>
<link rel="stylesheet" href="style/style.css">
<meta name="viewport" content="width=device-width">
<link href="style/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="viewport">
	<div class="affichageViewport">
	
	<h1>Formations</h1>

		<c:if test="${ utilisateur.formation != null }">
			<h2>Formation actuelle </h2>
			<div class="contentProgrammees">
				<table>
					<thead>
						<tr>
							<th>Intitulé</th>
							<th>Centre de formation</th>
							<th>Début</th>
							<th>Fin</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><c:out value="${ utilisateur.formation.intitule }" /></td>
							<td><c:out value="${ utilisateur.formation.centreFormation }" /></td>
							<td><c:out value="${ utilisateur.formation.debut }"/></td>
							<td><c:out value="${ utilisateur.formation.fin }"/></td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:if>
		
		<h2>Formation disponible</h2><c:if test="${ utilisateur.formation != null }"></c:if>
		<div class="contentProgrammees">
			<table>
				<thead>
					<tr>
						<th>Intitulé</th>
						<th>Centre de formation</th>
						<th>Début</th>
						<th>Fin</th>
						<c:if test="${ utilisateur.formation == null }"><th></th></c:if>
						
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${ formationsProgrammees }" var="formation">
						<tr>
							<td><c:out value="${formation.intitule}" /></td>
							<td><c:out value="${formation.centreFormation}" /></td>
							<td><c:out value="${formation.debut}"/></td>
							<td><c:out value="${formation.fin}"/></td>
							<c:if test="${ utilisateur.formation == null }"><td>	
							<form:form action="action" method="post">
								<input type="hidden" value="${ formation.id }" name="formationInscription">
								<input type="submit" value="S'inscrire" class="deconnexion"/>
							</form:form>
							</td></c:if>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<form:form action="menu" method="post" class="menuViewport">
	
		<div>
			<p><c:out value="${ utilisateur.prenom } ${ utilisateur.nom }"/><br>
			<span class="noteFormation">(Formation : <c:out value="${ utilisateur.formation.intitule }" default="Aucune formation en cours"/>)</span></p>		
		</div>	
		
		<div>
			<input type="submit" value="Retour" class="deconnexion" name="retourMenuStagiaire">
			<input type="submit" value="Se déconnecter" class="deconnexion" name="deconnexion">
		</div>
	</form:form>
</div>

</body>
</html>