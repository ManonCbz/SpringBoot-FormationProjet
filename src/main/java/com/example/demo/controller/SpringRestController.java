package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.CompteRepository;
import com.example.demo.dao.FormateurRepository;
import com.example.demo.dao.FormationRepository;
import com.example.demo.dao.StagiaireRepository;
import com.example.demo.model.Compte;
import com.example.demo.model.Formateur;
import com.example.demo.model.Stagiaire;

@RestController
public class SpringRestController {

	@Autowired
	CompteRepository compteRepository;
	@Autowired
	FormateurRepository formateurRepository;
	@Autowired
	StagiaireRepository stagiaireRepository;
	@Autowired
	FormationRepository formationRepository;
	
	// ============ Get ============ //
	
	@GetMapping("/comptes")
	public List<Compte> getAllUtilisateurs() {
		return compteRepository.findAll();
	}
	
	@GetMapping("/stagiaires")
	public List<Stagiaire> getAllStagiaires() {
		return stagiaireRepository.findAll();
	}	
	
	@GetMapping("/formateurs")
	public List<Formateur> getAllUFormateurs() {
		return formateurRepository.findAll();
	}
	
	@GetMapping("/compte/{id}")
	public Compte getPersonne(@PathVariable("id") long id) {
		return compteRepository.findById(id).orElse(null);
	}
	
	
}
