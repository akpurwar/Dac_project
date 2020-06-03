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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_PATIENT_TABLE")
public class Patient {
	private Integer patientId;

	private User user;
	@JsonIgnore
	private List<Appointment> appdetails;
	@JsonIgnore
	private List<Feedback> feeddetails;

	public Patient() {
		// TODO Auto-generated constructor stub
	}

	@OneToOne
	@JoinColumn(name = "userid")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	@OneToMany(mappedBy = "patientdet", cascade = CascadeType.ALL)
	public List<Appointment> getAppdetails() {
		return appdetails;
	}

	public void setAppdetails(List<Appointment> appdetails) {
		this.appdetails = appdetails;
	}

	@OneToMany(mappedBy = "patientdet", cascade = CascadeType.ALL)
	public List<Feedback> getFeeddetails() {
		return feeddetails;
	}

	public void setFeeddetails(List<Feedback> feeddetails) {
		this.feeddetails = feeddetails;
	}

}
