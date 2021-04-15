<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   

<c:if test="${ utilisateur.getClass().getName() != 'com.example.demo.model.Formateur' }"><c:redirect url="/accueil"></c:redirect></c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion des comptes</title>
<link rel="stylesheet" href="style/style.css">
<meta name="viewport" content="width=device-width">
</head>
<body>

<div class="viewport">

	<div class="affichageViewport">
		
		<h1>Gestion des comptes</h1>
		
		<section id="onglet" class="test">
			<ul id="ul">
				<li><a id="a1" href="#" class="active">Stagiaires</a></li>
				<li><a id="a2" href="#">Formateurs</a></li>
			</ul>
			
			<div id="contentStagiaire">
				<table>
					<thead>
						<tr>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Formation</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listeStagiaire}" var="stagiaire">
							<tr>
								<td><c:out value="${stagiaire.nom}" /></td>
								<td><c:out value="${stagiaire.prenom}" /></td>
								<td><c:out value="${stagiaire.formation.intitule} (${ stagiaire.formation.centreFormation })"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<div id="contentFormateur">
				<table>
					<thead>
						<tr>
							<th>Nom</th>
							<th>Prénom</th>
							<th>Formation</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listeFormateur}" var="formateur">
							<tr>
								<td><c:out value="${formateur.nom}" /></td>
								<td><c:out value="${formateur.prenom}" /></td>
								<td><c:out value="${formateur.formation.intitule} (${ formateur.formation.centreFormation })" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</section>
	</div>

	<form:form action="menu" method="post" class="menuViewport">
		
		<div style="cursor: default;">
			<p><c:out value="${ utilisateur.prenom } ${ utilisateur.nom }"/><br>
			<span class="noteFormation">(Formation : <c:out value="${ utilisateur.formation.intitule }" default="Aucune formation en cours"/>)</span></p>		
		</div>
	
		<div class="btnMenu">
			<input type="submit" value="Créer un compte" name="addCompte">
		</div>
		
		<div>
			<input type="submit" value="Retour" class="deconnexion" name="retourMenu">
			<input type="submit" value="Se déconnecter" class="deconnexion" name="deconnexion">		
		</div>
	</form:form>
	
</div>

<script type="text/javascript" src="script/script1.js"></script>
</body>
</html>