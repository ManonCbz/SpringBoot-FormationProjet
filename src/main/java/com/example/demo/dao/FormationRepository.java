package com.example.demo.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Formation;

public interface FormationRepository extends JpaRepository<Formation, Long>{

	List<Formation> findAll();
	
	List<Formation> findByDebutAfter(Date date);
}
