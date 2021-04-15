package com.example.demo.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Formateur extends Compte {

	@OneToOne(cascade = {CascadeType.ALL}, mappedBy = "formateur")
	private Formation formation;
	
	//
	
	public Formateur() {
		
	}
	
	//

	public Formation getFormation() {
		return formation;
	}

	public void setFormation(Formation formation) {
		this.formation = formation;
	}
	
}