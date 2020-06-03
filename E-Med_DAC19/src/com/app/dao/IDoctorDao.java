package com.app.dao;

import java.util.List;

import com.app.pojos.Appointment;
import com.app.pojos.Clinic;
import com.app.pojos.Patient;
import com.app.pojos.Specialisation;
import com.app.pojos.User;

public interface IDoctorDao {

	



	Integer addClinic(Integer userid, Clinic clinicdata);

	Integer addspec(Integer docid, Specialisation spec);

	List<Clinic> getClinic(Integer userid);

	Integer registerdoctor(User user);

	List<Appointment> showrequest(Integer docid);

	Patient getpatient(Integer patid);

	Integer setappointment(Integer apid, Appointment appointment);

	Integer declinerequest(Integer apid);

	Integer appdone(Integer apid);

	List<Appointment> getconfirmedlist(Integer docid);

}
