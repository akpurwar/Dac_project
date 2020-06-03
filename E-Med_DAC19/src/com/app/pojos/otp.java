package com.app.pojos;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name="T_OTP_HISTORY")
public class otp {

	private Integer otpId;
	private Integer  otpNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date validFrom;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date validTo;


	private User user;
	
	public otp() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getOtpId() {
		return otpId;
	}
	public void setOtpId(Integer otpId) {
		this.otpId = otpId;
	}




	public otp(Integer otpNo, Date validFrom, Date validTo) {
		super();
		this.otpNo = otpNo;
		this.validFrom = validFrom;
		this.validTo = validTo;
	}
	public Integer getOtpNo() {
		return otpNo;
	}
	public void setOtpNo(Integer otpNo) {
		this.otpNo = otpNo;
	}
	public Date getValidFrom() {
		return validFrom;
	}
	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}
	public Date getValidTo() {
		return validTo;
	}
	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	@ManyToOne
	@JoinColumn(name = "userid")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}




}
