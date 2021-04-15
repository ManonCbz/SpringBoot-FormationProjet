<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   

<c:if test="${ utilisateur.getClass().getName() != 'com.example.demo.model.Formateur' }"><c:redirect url="/accueil"></c:redirect></c:if>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ajout d'une formation</title>
<link rel="stylesheet" href="style/style.css">
<meta name="viewport" content="width=device-width">
<link href="style/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="viewport">
	<div class="affichageViewport">
		
		<h1>Programmer une formation</h1>
		
		<div class="formCreerCompte">

			<form:form modelAttribute="formation" action="createFormation" method="post" >
				
				<form:label path="intitule">Intitulé</form:label>
				<form:input path="intitule"/>
				<form:errors path="intitule" cssClas="error"/>
				
				<form:label path="centreFormation">Centre de Formation</form:label>
				<form:input path="centreFormation"/>
				<form:errors path="centreFormation" cssClass="error"/>
				
				<form:label path="debut">Date de début</form:label> 
				<form:input type="date" path="debut"/>	
				<form:errors path="debut" cssClass="error"/>
				
				<form:label path="fin">Date de fin</form:label> 
				<form:input type="date" path="fin"/>	
				<form:errors path="fin" cssClass="error"/>
				
				<form:select path="formateur">
					<c:forEach items="${ listeFormateur }" var="f">
						<form:option path="formateur" value="${ f }"><c:out value="${ f.prenom } ${ f.nom }"/></form:option>					
					</c:forEach>
				</form:select>
				
				<input type="submit" value="Programmer" class="">
				
			</form:form>
			
		</div>
	</div>

	<form:form action="menu" method="post" class="menuViewport">
	
		<div>
			<p><c:out value="${ utilisateur.prenom } ${ utilisateur.nom }"/><br>
			<span class="noteFormation">(Formation : <c:out value="${ utilisateur.formation.intitule }" default="Aucune formation en cours"/>)</span></p>		
		</div>	
	
		<div>
			<input type="submit" value="Retour" class="deconnexion" name="retourGestionFormation">
			<input type="submit" value="Se déconnecter" class="deconnexion" name="deconnexion">		
		</div>
	</form:form>
	
</div>

</body>
</html>