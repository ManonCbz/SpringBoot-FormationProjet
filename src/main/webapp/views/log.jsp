<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connexion</title>
<link rel="stylesheet" href="style/style.css">
<meta name="viewport" content="width=device-width">
</head>
<body>

<form:form modelAttribute="compte" action="login" method="post" class="formLog">
	
	<form:label path="nom">Nom</form:label>
	<form:input path="nom"/>
	<form:errors path="nom" cssClass="error"/>
	
	<form:label path="prenom">Prénom</form:label>
	<form:input path="prenom"/>
	<form:errors path="prenom" cssClass="error"/>
	
	<form:label path="motDePasse">Mot de Passe</form:label> 
	<form:password path="motDePasse"/>	
	<form:errors path="motDePasse" cssClass="error"/>
	
	<input type="submit" value="Se connecter" class="btnForm">
	
</form:form>

</body>
</html>