package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "T_CLINIC_TABLE")
public class Clinic {
	private Integer clinicId;
	private String clinicName;
	private String Area;
	private String city;
	private String state;

	private User user;
	
public Clinic() {
	// TODO Auto-generated constructor stub
}


	public Clinic(String clinicName, String area, String city, String state) {
	super();
	this.clinicName = clinicName;
	Area = area;
	this.city = city;
	this.state = state;
}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getClinicId() {
		return clinicId;
	}
	public void setClinicId(Integer clinicId) {
		this.clinicId = clinicId;
	}
	public String getClinicName() {
		return clinicName;
	}
	public void setClinicName(String clinicName) {
		this.clinicName = clinicName;
	}
	
	@Column(length = 255)
	public String getArea() {
		return Area;
	}
	public void setArea(String area) {
		Area = area;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	@OneToOne
	@JoinColumn(name = "docid")
	public User getUser() {
		return user;
	}
	
	
	public void setUser(User user) {
		this.user = user;
	}
	
	
	


}
