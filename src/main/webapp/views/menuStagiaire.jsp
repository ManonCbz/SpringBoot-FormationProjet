<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   

<c:if test="${ utilisateur.getClass().getName() != 'com.example.demo.model.Stagiaire' }"><c:redirect url="/accueil"></c:redirect></c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Menu</title>
<link rel="stylesheet" href="style/style.css">
<meta name="viewport" content="width=device-width">
</head>
<body>

<div class="viewport">
	<div class="affichageViewport">
		<div class="test">
			<c:if test="${ utilisateur.formation != null }">
				<p>Formation en cours : <span style="font-weight: bold"><c:out value="${ utilisateur.formation.intitule }"/></span></p>
				<table>
					<thead>
						<tr>
							<th>Centre de formation</th>
							<th>Début</th>
							<th>Fin</th>
							<th>Formateur</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><c:out value="${ utilisateur.formation.centreFormation }" /></td>
							<td><c:out value="${ utilisateur.formation.debut }"/></td>
							<td><c:out value="${ utilisateur.formation.fin }"/></td>
							<td><c:out value="${ utilisateur.formation.formateur.prenom } ${ utilisateur.formation.formateur.nom }"/></td>
						</tr>
					</tbody>
				</table>
			</c:if>
			<c:if test="${ utilisateur.formation == null }">
				<p>Aucune formation en cours</p>
			</c:if>
		 </div>
	</div>

	<form:form action="menu" method="post" class="menuViewport">
	
		<div>
			<p><c:out value="${ utilisateur.prenom } ${ utilisateur.nom }"/><br>
			<span class="noteFormation">(Formation : <c:out value="${ utilisateur.formation.intitule }" default="Aucune formation en cours"/>)</span></p>		
		</div>
	
		<div class="btnMenu">
			<input type="submit" value="Gestion du compte" name="gestionCompteStagiaire">
			<input type="submit" value="Voir les formations" name="gestionFormationStagiaire">
			<input type="submit" value="QCM" name="">
			<input type="submit" value="Évaluations" name="">
		</div>
		
		<div>
			<input type="submit" value="Se déconnecter" name="deconnexion" class="deconnexion">				
		</div>
	</form:form>
	
</div>

</body>
</html>