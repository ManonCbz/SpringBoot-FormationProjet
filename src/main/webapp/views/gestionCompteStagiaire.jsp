<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   

<c:if test="${ utilisateur.getClass().getName() != 'com.example.demo.model.Stagiaire' }"><c:redirect url="/accueil"></c:redirect></c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Gestion du compte</title>
<link rel="stylesheet" href="style/style.css">
<meta name="viewport" content="width=device-width">
</head>
<body>

	<div class="viewport">

	<div class="affichageViewport">
		
		<h1>Modifier le compte</h1>
		
		<div class="formCreerCompte">
			<form:form modelAttribute="compte" action="updateCompte" method="post" >
				
				<form:label path="nom">Nom</form:label>
				<form:input path="nom" value="${ utilisateur.nom }"/>
				<form:errors path="nom" cssClass="error"/>
				
				<form:label path="prenom">Prénom</form:label>
				<form:input path="prenom" value="${ utilisateur.prenom }"/>
				<form:errors path="prenom" cssClass="error"/>
				
				<form:label path="motDePasse">Mot de Passe</form:label> 
				<form:password path="motDePasse" value="${ utilisateur.motDePasse }"/>	
				<form:errors path="motDePasse" cssClass="error"/>
				
				<input type="submit" value="Modifier" class="">
				
			</form:form>
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