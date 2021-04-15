package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Compte;

public interface CompteRepository extends JpaRepository<Compte, Long>{

	List<Compte> findByNomAndPrenomAndMotDePasse(String nom, String prenom, String password);
	
	List<Compte> findByNomAndPrenom(String nom, String prenom);
	
	List<Compte> findAll();
	
}