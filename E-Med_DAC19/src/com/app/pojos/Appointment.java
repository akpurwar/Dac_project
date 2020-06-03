package com.app.pojos;

import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_APPOINTMENT_TABLE")
public class Appointment {
	
	private Integer apid;
	private String apstatus;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date appdate;
	
	@JsonFormat(timezone = "GMT+5:30",pattern = "HH:mm")
	private Date apptime;
	private Doctor doctordet;

	private Patient patientdet;
	
	
	public Appointment() {
		// TODO Auto-generated constructor stub
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getApid() {
		return apid;
	}


	public void setApid(Integer apid) {
		this.apid = apid;
	}


	public String getApstatus() {
		return apstatus;
	}


	public void setApstatus(String apstatus) {
		this.apstatus = apstatus;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getAppdate() {
		return appdate;
	}


	public void setAppdate(Date appdate) {
		this.appdate = appdate;
	}


@ManyToOne
@JoinColumn(name = "doctorid")
	public Doctor getDoctordet() {
		return doctordet;
	}


	public void setDoctordet(Doctor doctordet) {
		this.doctordet = doctordet;
	}

@ManyToOne
@JoinColumn(name = "patientid")
	public Patient getPatientdet() {
		return patientdet;
	}


	public void setPatientdet(Patient patientdet) {
		this.patientdet = patientdet;
	}


	@Temporal(TemporalType.TIME)
	public Date getApptime() {
		return apptime;
	}


	public void setApptime(Date apptime) {
		this.apptime = apptime;
	}
	
	
	
	
	

}
