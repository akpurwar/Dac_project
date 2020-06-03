package com.app.pojos;

import java.util.Date;

import java.util.List;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "T_USER_TABLE")
public class User {
	private Integer userId;
	private String firstName;
	private String lastName;
	private String mobileNo;
	private String email;
	private String gender;
	private String bloodGrp;
	private Role role;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dob;
	private String password;
	private boolean isActive;
	private boolean isOnline;
	
	@JsonIgnore
	private Doctor doctor;
	@JsonBackReference
	private Patient patient;

	@OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL)
	public Doctor getDoctor() {
		return doctor;
	}

	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@OneToOne(mappedBy = "user" ,cascade = CascadeType.ALL)
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}



	@JsonIgnore
	private Clinic clinic;

	@JsonIgnore
	private List<otp> otpnumbers;


	@JsonIgnore
	private List<Specialisation> docspecilaisation;
	@JsonIgnore
	private List<Schedule> patientschedule;
	// @JsonBackReference
	private Address address;

	public User() {
		// Default Constructor
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public List<Schedule> getPatientschedule() {
		return patientschedule;
	}

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	public Clinic getClinic() {
		return clinic;
	}

	public void setClinic(Clinic clinic) {
		this.clinic = clinic;
	}

	public void setPatientschedule(List<Schedule> patientschedule) {
		this.patientschedule = patientschedule;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getUserId() {
		return userId;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	public List<Specialisation> getDocspecilaisation() {
		return docspecilaisation;
	}

	public void setDocspecilaisation(List<Specialisation> docspecilaisation) {
		this.docspecilaisation = docspecilaisation;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Column(nullable = false)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(nullable = false)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(nullable = false)
	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Column(nullable = false, unique = true)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(nullable = false)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBloodGrp() {
		return bloodGrp;
	}

	public void setBloodGrp(String bloodGrp) {
		this.bloodGrp = bloodGrp;
	}

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	@Column(nullable = false)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	// helper methods
	public void addlocation(Address add) {
		this.setAddress(add);
		add.setUser(this);
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isOnline() {
		return isOnline;
	}

	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	public List<otp> getOtpnumbers() {
		return otpnumbers;
	}

	public void setOtpnumbers(List<otp> otpnumbers) {
		this.otpnumbers = otpnumbers;
	}
	
	

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", mobileNo="
				+ mobileNo + ", email=" + email + ", gender=" + gender + ", bloodGrp=" + bloodGrp + ", role=" + role
				+ ", dob=" + dob + ", password=" + password + ", address=" + address + "]";
	}

}
