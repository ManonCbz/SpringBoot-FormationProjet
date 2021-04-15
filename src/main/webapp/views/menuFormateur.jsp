<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   

<c:if test="${ utilisateur.getClass().getName() != 'com.example.demo.model.Formateur' }"><c:redirect url="/accueil"></c:redirect></c:if>

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
			
			<h1>Formation en cours</h1>
		
			<table>
				<thead>
					<tr>
						<th>Formation</th>
						<th>Formateur</th>
						<th>Stagiaires</th>
					</tr>
				</thead>
					<tbody>
						<c:forEach items="${ listeFormationEnCours }" var="formation">
							<tr>
								<td><c:out value="${formation.intitule} (${formation.centreFormation})"/></td>
								<td><c:out value="${formation.formateur.prenom} ${ formation.formateur.nom }"/></td>
								<td>
									<ul>
										<c:forEach items="${ formation.stagiaires }" var="stagiaire">
											<li><c:out value="${stagiaire.prenom} ${ stagiaire.nom }"/></li>											
										</c:forEach>
									</ul>
								</td>
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
		
		<div class="btnMenu">
			<input type="submit" value="Gestion des comptes" name="gestionCompteFormateur">
			<input type="submit" value="Gestion des formations" name="gestionFormationFormateur">		
			<input type="submit" value="QCM" name="">
			<input type="submit" value="Évaluations" name="">
		</div>
		
		<div>
			<input type="submit" value="Se déconnecter" class="deconnexion" name="deconnexion">				
		</div>
		
	</form:form>
	
</div>

</body>
</html>