package com.app.pojos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_FEEDBACK_TABLE")
public class Feedback {

	private Integer feedid;

	private String feedback;
	
	private String feedbackstatus;

	private Doctor doctordet;

	private Patient patientdet;

	public Feedback() {
		System.out.println("inside feedback pojo ctr");
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getFeedid() {
		return feedid;
	}

	public void setFeedid(Integer feedid) {
		this.feedid = feedid;
	}

	
	
	public String getFeedbackstatus() {
		return feedbackstatus;
	}


	public void setFeedbackstatus(String feedbackstatus) {
		this.feedbackstatus = feedbackstatus;
	}


	@Column(length = 255)
	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	@ManyToOne
	@JoinColumn(name = "docid")
	public Doctor getDoctordet() {
		return doctordet;
	}

	public void setDoctordet(Doctor doctordet) {
		this.doctordet = doctordet;
	}

	
	@ManyToOne
	@JoinColumn(name = "patid")
	public Patient getPatientdet() {
		return patientdet;
	}

	public void setPatientdet(Patient patientdet) {
		this.patientdet = patientdet;
	}

}