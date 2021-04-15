package com.example.demo.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Formation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String intitule;
	private String centreFormation;
	private Date debut;
	private Date fin;
	
	@OneToOne
	private Formateur formateur;
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy = "formation")
	private List<Stagiaire> stagiaires = new ArrayList<Stagiaire>();
	
	//

	public Formation() {
		
	}
	
	//

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getCentreFormation() {
		return centreFormation;
	}

	public void setCentreFormation(String centreFormation) {
		this.centreFormation = centreFormation;
	}

	public Date getDebut() {
		return debut;
	}

	public void setDebut(Date debut) {
		this.debut = debut;
	}

	public Date getFin() {
		return fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}
	
	public Formateur getFormateur() {
		return formateur;
	}

	public void setFormateur(Formateur formateur) {
		this.formateur = formateur;
	}

	public List<Stagiaire> getStagiaires() {
		return stagiaires;
	}

	public void setStagiaires(List<Stagiaire> stagiaires) {
		this.stagiaires = stagiaires;
	}
	
	//

	public void addStagiaire(Stagiaire e) {
		stagiaires.add(e);
		e.setFormation(this);
	}

	public void removeStagiaire(Stagiaire e) {
		stagiaires.remove(e);
		e.setFormation(null);
	}
	
	//

	@Override
	public String toString() {
		return "Formation [id=" + id + ", intitule=" + intitule + ", centreFormation=" + centreFormation + ", debut="
				+ debut + ", fin=" + fin + ", formateur=" + formateur + ", stagiaires=" + stagiaires + "]";
	}
	
}
