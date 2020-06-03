package com.app.pojos;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.GeneratorType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "T_SPECIALISATION_TABLE")
public class Specialisation {
	private Integer specid;
	private String specialisation;
	
	private User user;
	
	
	private DocQualifiactions qualification;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getSpecid() {
		return specid;
	}

	public void setSpecid(Integer specid) {
		this.specid = specid;
	}

	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
	}

	
	@ManyToOne
	@JoinColumn(name = "userid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

	@Enumerated(EnumType.STRING)
	public DocQualifiactions getQualification() {
		return qualification;
	}

	public void setQualification(DocQualifiactions qualification) {
		this.qualification = qualification;
	}


	}

	
	
	
