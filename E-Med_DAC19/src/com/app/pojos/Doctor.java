package com.app.pojos;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GeneratorType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_DOCTOR_TABLE")
public class Doctor {
	
	private Integer doctorId;
	@JsonIgnore
	private List<Appointment> apdetails;
	
	@JsonIgnore
	private List<Feedback> feeddetails;
	
	
	
	private User user;
	
	public Doctor() {
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	
	
	
	@OneToOne
	@JoinColumn(name = "userid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@OneToMany(mappedBy = "doctordet",cascade = CascadeType.ALL)
	public List<Appointment> getApdetails() {
		return apdetails;
	}
	public void setApdetails(List<Appointment> apdetails) {
		this.apdetails = apdetails;
	}
	
	@OneToMany(mappedBy = "doctordet",cascade = CascadeType.ALL)
	public List<Feedback> getFeeddetails() {
		return feeddetails;
	}
	public void setFeeddetails(List<Feedback> feeddetails) {
		this.feeddetails = feeddetails;
	}
	
	

}
