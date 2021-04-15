ong1 = document.getElementById('a1');
ong2 = document.getElementById('a2');
contenuStagiaire = document.getElementById('contentStagiaire');
contenuFormateur = document.getElementById('contentFormateur');

contenuFormateur.style.cssText = "display: none";

ong1.addEventListener("click",function(){
	contenuStagiaire.style.cssText = "display: block";
	contenuFormateur.style.cssText = "display: none";
	ong1.className = "active";
	ong2.className = "";
})

ong2.addEventListener("click",function(){
	contenuStagiaire.style.cssText = "display: none";
	contenuFormateur.style.cssText = "display: block";
	ong1.className = "";
	ong2.className = "active";
})