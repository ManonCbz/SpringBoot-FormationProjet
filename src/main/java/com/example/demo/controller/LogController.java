package com.example.demo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.FormateurRepository;
import com.example.demo.dao.FormationRepository;
import com.example.demo.model.Compte;
import com.example.demo.model.Formateur;
import com.example.demo.model.Formation;
import com.example.demo.model.Stagiaire;

@Controller
public class LogController {

	@Autowired
	CompteRepository compteRepository;
	
	@Autowired
	FormationRepository formationRepository;
	
	@Autowired
	FormateurRepository formateurRepository;
	
	// Accueil (/accueil)
	@GetMapping("/accueil")
	public String test(Model model) {
		
		model.addAttribute("compte", new Compte());
		return "log";
	}
	
	// ========================================== Connexion / Déconnexion ========================================== //
	
	// Connexion (/login)
	@PostMapping("/login")
	public String loginCompte(@ModelAttribute("compte") @Valid Compte compte, BindingResult result, Model model, WebRequest request){

		// Vérifie les erreurs de saisies : Si il y en a -> renvoi à la page de connexion
		if(result.hasErrors()) {
			return "log";
		}
		
		List<Compte> test = compteRepository.findByNomAndPrenom("Lovelace", "Ada");
		if(test.isEmpty()) {
			Formateur formateur = new Formateur();
			formateur.setMotDePasse("test");
			formateur.setNom("Lovelace");
			formateur.setPrenom("Ada");
			
			compteRepository.save(formateur);
		}
		
		List<Compte> user = compteRepository.findByNomAndPrenomAndMotDePasse(compte.getNom(), compte.getPrenom(), compte.getMotDePasse());
				
		// Nom de la jsp de redirection
		String menu = "";
		
		// Contrôle si les informations données correspondent à un compte enregistré
		if(user.isEmpty() != true) {
			
			request.setAttribute("utilisateur", user.get(0), WebRequest.SCOPE_SESSION);
			
			if(user.get(0).getClass().getName() == "com.example.demo.model.Formateur") {
				
				List<Formation> li = formationRepository.findAll();
				List<Formation> listeFormationEnCours = new ArrayList<Formation>();
				
				for(Formation form : li) {
					
					Date debutFormation = form.getDebut();
					Date finFormation = form.getFin();
					
					Date today = new Date(Calendar.getInstance().getTime().getTime());

					if(today.after(debutFormation) && today.before(finFormation)) {
						listeFormationEnCours.add(form);
					}
				}
				
				request.setAttribute("listeFormationEnCours", listeFormationEnCours, WebRequest.SCOPE_REQUEST);
				
				menu = "menuFormateur";
			}
			else {
				menu = "menuStagiaire";
			}
			
		}
		// Si les infos ne sont pas correctes -> renvoi à la page de connexion
		else {
			menu = "log";
			// ... add session erreur
		}
		
		return menu;
	}
	
	// Déconnexion (/menu -> paramètre : déconnexion)
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "deconnexion")
	public String menu(Model model, WebRequest request) {
		
		model.addAttribute("compte", new Compte());
		request.removeAttribute("utilisateur", WebRequest.SCOPE_SESSION);
		
		return "log";
	}
	
	// ========================================== Redirection ========================================== //

	// ========= Formateur ======== //
	
	// Retour menu (/menu -> paramètre : retourMenu)
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "retourMenu")
	public String retourMenu(Model model, WebRequest request) {
		model.addAttribute("compte", new Compte());
		
		List<Formation> li = formationRepository.findAll();
		List<Formation> listeFormationEnCours = new ArrayList<Formation>();
		
		for(Formation form : li) {
			
			Date debutFormation = form.getDebut();
			Date finFormation = form.getFin();
			
			Date today = new Date(Calendar.getInstance().getTime().getTime());

			if(today.after(debutFormation) && today.before(finFormation)) {
				listeFormationEnCours.add(form);
			}
		}
		
		request.setAttribute("listeFormationEnCours", listeFormationEnCours, WebRequest.SCOPE_REQUEST);
		
		
		return "menuFormateur";
	}
		
	// Redirection Gestion des comptes (/menu -> paramètre : gestionCompteFormateur)
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "gestionCompteFormateur")
	public String gestionCompteFormateur(Model model, WebRequest request) {
		model.addAttribute("compte", new Compte());
		
		List<Compte> listeUtilisateur = compteRepository.findAll();
		
		List<Stagiaire> listeStagiaire = new ArrayList<Stagiaire>();
		List<Formateur> listeFormateur = new ArrayList<Formateur>();
		
		for(Compte c : listeUtilisateur) {
			
			if(c.getClass().getName() == "com.example.demo.model.Stagiaire") {
				listeStagiaire.add((Stagiaire) c);
			}
			else {
				listeFormateur.add((Formateur) c);
			}
			
		}
		
		request.setAttribute("listeStagiaire", listeStagiaire, WebRequest.SCOPE_REQUEST);
		request.setAttribute("listeFormateur", listeFormateur, WebRequest.SCOPE_REQUEST);
		
		return "gestionCompteFormateur";
	}
	
	// Retour Gestion des comptes (/menu -> paramètre : retourGestionCompte)
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "retourGestionCompte")
	public String retourGestionCompte(Model model, WebRequest request) {
		model.addAttribute("compte", new Compte());
		
		List<Compte> listeUtilisateur = compteRepository.findAll();
		
		List<Stagiaire> listeStagiaire = new ArrayList<Stagiaire>();
		List<Formateur> listeFormateur = new ArrayList<Formateur>();
		
		for(Compte c : listeUtilisateur) {
			
			if(c.getClass().getName() == "com.example.demo.model.Stagiaire") {
				listeStagiaire.add((Stagiaire) c);
			}
			else {
				listeFormateur.add((Formateur) c);
			}
			
		}
		
		request.setAttribute("listeStagiaire", listeStagiaire, WebRequest.SCOPE_REQUEST);
		request.setAttribute("listeFormateur", listeFormateur, WebRequest.SCOPE_REQUEST);
		
		
		return "gestionCompteFormateur";
	}
	
	// Redirection Création nouveau compte (/menu -> paramètre : addCompte)
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "addCompte")
 	public String addCompte(Model model, WebRequest request) {
		model.addAttribute("compte", new Compte());
		return "addCompte";
	}
	
	// Redirection Gestion des formations (/menu -> paramètre : gestionFormationFormateur)
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "gestionFormationFormateur")
 	public String gestionFormationFormateur(Model model, WebRequest request) {
		model.addAttribute("compte", new Compte());
		
		List<Formation> allFormation = formationRepository.findAll();
		
		List<Formation> listeFormationProgrammees = new ArrayList<Formation>();
		List<Formation> listeFormationEnCours = new ArrayList<Formation>();
		List<Formation> listeFormationPassees = new ArrayList<Formation>();
		
		for(Formation form : allFormation) {
			
			Date debutFormation = form.getDebut();
			Date finFormation = form.getFin();
			
			Date today = new Date(Calendar.getInstance().getTime().getTime());

			if(today.after(debutFormation) && today.before(finFormation)) {
				listeFormationEnCours.add(form);
			}
			else if (today.after(finFormation)) {
				listeFormationPassees.add(form);
			}
			else if (today.before(debutFormation)) {
				listeFormationProgrammees.add(form);
			}
		}
		
		request.setAttribute("listeFormationProgrammees", listeFormationProgrammees, WebRequest.SCOPE_REQUEST);
		request.setAttribute("listeFormationEnCours", listeFormationEnCours, WebRequest.SCOPE_REQUEST);
		request.setAttribute("listeFormationPassees", listeFormationPassees, WebRequest.SCOPE_REQUEST);

		return "gestionFormationFormateur";
	}
	
	// Redirection Créer nouvelle formation (/menu -> paramètre : addFormation)
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "addFormation")
 	public String creerFormationFormateur(Model model, WebRequest request) {
		model.addAttribute("formation", new Formation());
		
		List<Formateur> li = formateurRepository.findByFormationNull();
		
		request.setAttribute("listeFormateur", li, WebRequest.SCOPE_REQUEST);
		
		return "addFormation";
	}
	
	// Retour Gestion des formations (/menu -> paramètre : retourGestionFormation)
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "retourGestionFormation")
	public String retourGestioFormation(Model model, WebRequest request) {
		model.addAttribute("compte", new Compte());
		
		List<Formation> allFormation = formationRepository.findAll();
		
		List<Formation> listeFormationProgrammees = new ArrayList<Formation>();
		List<Formation> listeFormationEnCours = new ArrayList<Formation>();
		List<Formation> listeFormationPassees = new ArrayList<Formation>();
		
		for(Formation form : allFormation) {
			
			Date debutFormation = form.getDebut();
			Date finFormation = form.getFin();
			
			Date today = new Date(Calendar.getInstance().getTime().getTime());

			if(today.after(debutFormation) && today.before(finFormation)) {
				listeFormationEnCours.add(form);
			}
			else if (today.after(finFormation)) {
				listeFormationPassees.add(form);
			}
			else if (today.before(debutFormation)) {
				listeFormationProgrammees.add(form);
			}
		}
		
		request.setAttribute("listeFormationProgrammees", listeFormationProgrammees, WebRequest.SCOPE_REQUEST);
		request.setAttribute("listeFormationEnCours", listeFormationEnCours, WebRequest.SCOPE_REQUEST);
		request.setAttribute("listeFormationPassees", listeFormationPassees, WebRequest.SCOPE_REQUEST);

		return "gestionFormationFormateur";
	}
	
	// ========= Stagiaire ======== //
	
	// Redirection Gestion du comptes (/menu -> paramètre : gestionCompteStagiaire)
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "gestionCompteStagiaire")
	public String gestionCompteStagiaire(Model model, WebRequest request) {
		model.addAttribute("compte", new Compte());
		return "gestionCompteStagiaire";
	}
	
	// Retour menu (/menu -> paramètre : retourMenu)
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "retourMenuStagiaire")
	public String retourMenuStagiaire(Model model, WebRequest request) {
		model.addAttribute("compte", new Compte());
		return "menuStagiaire";
	}

	// Redirection Gestion de la formation (/menu -> paramètre : gestionFormationStagiaire)
	@RequestMapping(value = "/menu", method = RequestMethod.POST, params = "gestionFormationStagiaire")
	public String gestionFormationStagiaire(Model model, WebRequest request) {
		model.addAttribute("compte", new Compte());
		
		Date today = new Date(Calendar.getInstance().getTime().getTime());
		
		List<Formation> li = formationRepository.findByDebutAfter(today);
		
		request.setAttribute("formationsProgrammees", li, WebRequest.SCOPE_REQUEST);
		
		return "gestionFormationStagiaire";
	}
}