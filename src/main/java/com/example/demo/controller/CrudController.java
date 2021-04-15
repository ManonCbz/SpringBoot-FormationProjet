package com.example.demo.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.FormationRepository;
import com.example.demo.model.Compte;
import com.example.demo.model.Formateur;
import com.example.demo.model.Formation;
import com.example.demo.model.Stagiaire;

@Controller
public class CrudController {

	@Autowired
	CompteRepository compteRepository;
	
	@Autowired
	FormationRepository formationRepository;
	
	// ======================================== Formateur ======================================== //
	
	// Création nouveau compte (/create)
	@PostMapping("/create")
	public String addCompte(@ModelAttribute("compte") @Valid Compte compte, BindingResult result, Model model, WebRequest request){
		
		// Vérifie les erreurs de saisies : Si il y en a -> renvoi à la page de création
		if(result.hasErrors()) {
			return "addCompte";
		}
		
		String type = request.getParameter("statut");

		if(type.equals("stagiaire")) {
			
			Stagiaire s = new Stagiaire();
			s.setMotDePasse(compte.getMotDePasse());
			s.setNom(compte.getNom());
			s.setPrenom(compte.getPrenom());
			
			compteRepository.save(s);
		}
		else {
			
			Formateur f = new Formateur();
			f.setNom(compte.getNom());
			f.setMotDePasse(compte.getMotDePasse());
			f.setPrenom(compte.getPrenom());
			
			compteRepository.save(f);
		}
		
		//
		
		List<Compte> listeUtilisateur = compteRepository.findAll();
		List<Stagiaire> listeStagiaire = new ArrayList<Stagiaire>();
		List<Formateur> listeFormateur = new ArrayList<Formateur>();
		
		for(Compte c : listeUtilisateur) {
			
			if(c.getClass().getName() == "com.example.demo.model.Stagiaire") listeStagiaire.add((Stagiaire) c);
			else listeFormateur.add((Formateur) c);
		}
		
		request.setAttribute("listeStagiaire", listeStagiaire, WebRequest.SCOPE_REQUEST);
		request.setAttribute("listeFormateur", listeFormateur, WebRequest.SCOPE_REQUEST);
		
		return "gestionCompteFormateur";
	}

	// Création d'une nouvelle formation (/createFormation)
	@PostMapping("/createFormation")
	public String addFormation(@ModelAttribute("formation") @Valid Formation formation, BindingResult result, Model model, WebRequest request){
				
		System.out.println(formation.getDebut());
		System.out.println(formation.getFin());
		
		Formation f = new Formation();
		f.setCentreFormation(formation.getCentreFormation());
		f.setIntitule(formation.getIntitule());
		f.setDebut(formation.getDebut());
		f.setFin(formation.getFin());
		f.setFormateur(formation.getFormateur());
		
		formation.getFormateur().setFormation(f);
		
		formationRepository.save(f);
		compteRepository.save(formation.getFormateur());
		
		
		List<Formation> allFormation = formationRepository.findAll();
		
		List<Formation> listeFormationProgrammees = new ArrayList<Formation>();
		List<Formation> listeFormationEnCours = new ArrayList<Formation>();
		List<Formation> listeFormationPassees = new ArrayList<Formation>();
		
		for(Formation form : allFormation) {
			
			Date debutFormation = form.getDebut();
			Date finFormation = form.getFin();
			
			Date today = new Date(Calendar.getInstance().getTime().getTime());
			
			System.out.println(today);

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


	// ======================================== Stagiaire ======================================== //

	// Modifier son compte (/updateCompte)
	@PostMapping("/updateCompte")
	public String updateCompte(@ModelAttribute("compte") @Valid Compte compte, BindingResult result, Model model, WebRequest request){

		// Vérifie les erreurs de saisies : Si il y en a -> renvoi à la page de modification
		if(result.hasErrors()) {
			return "addCompte";
		}
		
		Stagiaire s = (Stagiaire) request.getAttribute("utilisateur", WebRequest.SCOPE_SESSION);
		
		s.setNom(compte.getNom());
		s.setPrenom(compte.getPrenom());
		s.setMotDePasse(compte.getMotDePasse());
		
		compteRepository.save(s);
		
		return "menuStagiaire";
	}
	
	// S'inscrire à une formation  (/action -> paramètre : formationInscription)
	@RequestMapping(value = "/action", method = RequestMethod.POST, params = "formationInscription")
	public String retourMenuStagiaire(Model model, WebRequest request) {
		model.addAttribute("compte", new Compte());
		
		Stagiaire s = (Stagiaire) request.getAttribute("utilisateur", WebRequest.SCOPE_SESSION);

		Long id = Long.parseLong(request.getParameter("formationInscription"));
		
		Optional<Formation> form = formationRepository.findById(id);
			
		form.get().addStagiaire(s);
		
		formationRepository.save(form.get());
		compteRepository.save(s);
		
		request.setAttribute("utilisateur", s, WebRequest.SCOPE_SESSION);
		
		return "gestionFormationStagiaire";
	}

}
