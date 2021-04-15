<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   

<c:if test="${ utilisateur.getClass().getName() != 'com.example.demo.model.Formateur' }"><c:redirect url="/accueil"></c:redirect></c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion des formations</title>
<link rel="stylesheet" href="style/style.css">
<meta name="viewport" content="width=device-width">
</head>
<body>

<div class="viewport">

	<div class="affichageViewport">
		
		<h1>Gestion des formations </h1>
		
		<section id="onglet" class="test">
			<ul id="ul">
				<li><a id="a3" href="#">Programmées</a></li>
				<li><a id="a1" href="#" class="active">En cours</a></li>
				<li><a id="a2" href="#">Terminées</a></li>
			</ul>
			
			<div id="contentProgrammees">
				<table>
					<thead>
						<tr>
							<th>Intitulé</th>
							<th>Centre de formation</th>
							<th>Début</th>
							<th>Fin</th>
							<th>Formateur</th>
							<th>Stagiaires</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ listeFormationProgrammees }" var="formation">
							<tr>
								<td><c:out value="${formation.intitule}" /></td>
								<td><c:out value="${formation.centreFormation}" /></td>
								<td><c:out value="${formation.debut}"/></td>
								<td><c:out value="${formation.fin}"/></td>
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
			
			<div id="contentEnCours">
				<table>
					<thead>
						<tr>
							<th>Intitulé</th>
							<th>Centre de formation</th>
							<th>Début</th>
							<th>Fin</th>
							<th>Formateur</th>
							<th>Stagiaires</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ listeFormationEnCours }" var="formation">
							<tr>
								<td><c:out value="${formation.intitule}" /></td>
								<td><c:out value="${formation.centreFormation}" /></td>
								<td><c:out value="${formation.debut}"/></td>
								<td><c:out value="${formation.fin}"/></td>
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
			
			<div id="contentPassees">
				<table>
					<thead>
						<tr>
							<th>Intitulé</th>
							<th>Centre de formation</th>
							<th>Début</th>
							<th>Fin</th>
							<th>Formateur</th>
							<th>Stagiaires</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ listeFormationPassees }" var="formation">
							<tr>
								<td><c:out value="${formation.intitule}" /></td>
								<td><c:out value="${formation.centreFormation}" /></td>
								<td><c:out value="${formation.debut}"/></td>
								<td><c:out value="${formation.fin}"/></td>
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
		</section>
	</div>

	<form:form action="menu" method="post" class="menuViewport">
	
		<div>
			<p><c:out value="${ utilisateur.prenom } ${ utilisateur.nom }"/><br>
			<span class="noteFormation">(Formation : <c:out value="${ utilisateur.formation.intitule }" default="Aucune formation en cours"/>)</span></p>		
		</div>
		
		<div class="btnMenu">
			<input type="submit" value="Programmer une formation" name="addFormation">
		</div>
		
		<div>
			<input type="submit" value="Retour" class="deconnexion" name="retourMenu">
			<input type="submit" value="Se déconnecter" class="deconnexion" name="deconnexion">		
		</div>
	</form:form>
	
</div>

<script type="text/javascript" src="script/script2.js"></script>
</body>
</html>