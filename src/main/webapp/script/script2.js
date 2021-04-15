encours = document.getElementById('a1');
terminees = document.getElementById('a2');
programmees = document.getElementById('a3');

contenuProgrammees = document.getElementById('contentProgrammees');
contenuEnCours = document.getElementById('contentEnCours');
contenuPassees = document.getElementById('contentPassees');

contenuProgrammees.style.cssText = "display: none";
contenuPassees.style.cssText = "display: none";

encours.addEventListener("click",function(){
	contenuProgrammees.style.cssText = "display: none";
	contenuEnCours.style.cssText = "display: block";
	contenuPassees.style.cssText = "display: none";
	encours.className = "active";
	terminees.className = "";
	programmees.className = "";
})

terminees.addEventListener("click",function(){
	contenuProgrammees.style.cssText = "display: none";
	contenuEnCours.style.cssText = "display: none";
	contenuPassees.style.cssText = "display: block";
	encours.className = "";
	terminees.className = "active";
	programmees.className = "";
})

programmees.addEventListener("click",function(){
	contenuProgrammees.style.cssText = "display: block";
	contenuEnCours.style.cssText = "display: none";
	contenuPassees.style.cssText = "display: none";
	encours.className = "";
	terminees.className = "";
	programmees.className = "active";
})