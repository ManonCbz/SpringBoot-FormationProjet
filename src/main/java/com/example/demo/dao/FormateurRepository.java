package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Formateur;

public interface FormateurRepository extends JpaRepository<Formateur, Long>{

	List<Formateur> findByFormationNull();
	
}
