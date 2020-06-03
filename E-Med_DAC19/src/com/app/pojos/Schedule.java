package com.app.pojos;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "T_SCHEDULE_TABLE")
public class Schedule {
	
	private Integer scheduleid;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date schedule;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date starttime;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endtime;
	private boolean availability;
	private User user;
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getScheduleid() {
		return scheduleid;
	}

	public void setScheduleid(Integer scheduleid) {
		this.scheduleid = scheduleid;
	}

	public Date getSchedule() {
		return schedule;
	}

	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
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
