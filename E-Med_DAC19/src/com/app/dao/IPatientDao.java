package com.app.dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Appointment;
import com.app.pojos.Feedback;
import com.app.pojos.User;

public interface IPatientDao {

	Integer registerpatient(User user);


	Integer requestdoc(Integer patid, Integer docid, Appointment appointment);


	


	List<Appointment> getappointment(Integer patid);


	List<Appointment> getdecappointment(Integer patid);


	List<Appointment> getdoneappointment(Integer patid);





	Integer givefeedback(Integer apid, Feedback feedback);
	
	

}
