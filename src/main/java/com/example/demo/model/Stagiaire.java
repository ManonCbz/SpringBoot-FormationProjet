package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Stagiaire extends Compte {

	@ManyToOne (cascade = CascadeType.PERSIST)
	private Formation formation;
	
	//
	
	public Stagiaire() {
		
	}

	//
	
	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	
}
