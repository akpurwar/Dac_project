package com.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Appointment;
import com.app.pojos.Doctor;
import com.app.pojos.Feedback;
import com.app.pojos.Patient;
import com.app.pojos.Specialisation;
import com.app.pojos.User;

@Repository
@Transactional
public class PatientDaoImpl implements IPatientDao {

	@Autowired
	JavaMailSender sendmail;
	@Autowired
	SessionFactory sf;

	@Override
	public Integer registerpatient(User user) {
		Patient p = new Patient();
		user.setActive(true);
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmail());
		mail.setSubject("EMED REGISTRATION");
		mail.setText("WELCOME TO E-MED " + user.getFirstName() + ".You have succesfuly registerd with email "
				+ user.getEmail() + "and password " + user.getPassword());
		sendmail.send(mail);
		Integer i = (Integer) sf.getCurrentSession().save(user);
		p.setUser(user);
		user.setPatient(p);
		return i;
	}

	@Override
	public Integer requestdoc(Integer patid, Integer docid, Appointment appointment) {

		appointment.setApstatus("PENDING");
		// List<Appointment> aplist = new ArrayList<>();
		//
		// User pat=sf.getCurrentSession().get(User.class, patid);
		// User doc = sf.getCurrentSession().get(User.class, docid);
		// Patient p =new Patient();
		// Doctor d =new Doctor();
		// d.setDoctorId(doc.getUserId());
		// p.setPatientId(pat.getUserId());
		// ap.setApstatus("PENDING");
		// ap.setPatientdet(p);
		// ap.setDoctordet(d);
		// aplist.add(ap);
		// p.setAppdetails(aplist);
		// d.setApdetails(aplist);
		// sf.getCurrentSession().save(d);
		User doc = sf.getCurrentSession().get(User.class, docid);
		User pat = sf.getCurrentSession().get(User.class, patid);
		Doctor d = sf.getCurrentSession().get(Doctor.class, doc.getDoctor().getDoctorId());
		d.getApdetails().add(appointment);
		Patient p = sf.getCurrentSession().get(Patient.class, pat.getPatient().getPatientId());
		p.getAppdetails().add(appointment);
		appointment.setDoctordet(d);
		appointment.setPatientdet(p);
		System.out.println(d);

		System.out.println(doc);
		return 1;
	}

	@Override
	public List<Appointment> getappointment(Integer patid) {

		User u = sf.getCurrentSession().get(User.class, patid);
		String jpql = "Select a from Appointment a where  a.patientdet.patientId=:id and a.apstatus!='DECLINED'";
		return sf.getCurrentSession().createQuery(jpql, Appointment.class)
				.setParameter("id", u.getPatient().getPatientId()).getResultList();

	}

	@Override
	public List<Appointment> getdoneappointment(Integer patid) {

		User u = sf.getCurrentSession().get(User.class, patid);
		String jpql = "Select a from Appointment a where  a.patientdet.patientId=:id and a.apstatus='DONE'";
		return sf.getCurrentSession().createQuery(jpql, Appointment.class)
				.setParameter("id", u.getPatient().getPatientId()).getResultList();
	}

	@Override
	public Integer givefeedback(Integer apid, Feedback feedback) {
		Appointment a = sf.getCurrentSession().get(Appointment.class, apid);
		a.setApstatus("GIVEN");
		Doctor d = sf.getCurrentSession().get(Doctor.class, a.getDoctordet().getDoctorId());
		d.getFeeddetails().add(feedback);
		Patient p = sf.getCurrentSession().get(Patient.class, a.getPatientdet().getPatientId());
		p.getFeeddetails().add(feedback);
		feedback.setDoctordet(d);
		feedback.setPatientdet(p);
		String jpql = "select a from Appointment a where a.doctordet.doctorId=:docid and a.patientdet.patientId=:patid";

		return 1;

	}

	@Override
	public List<Appointment> getdecappointment(Integer patid) {

		User u = sf.getCurrentSession().get(User.class, patid);
		String jpql = "Select a from Appointment a where  a.patientdet.patientId=:id and a.apstatus='DECLINED'";
		return sf.getCurrentSession().createQuery(jpql, Appointment.class)
				.setParameter("id", u.getPatient().getPatientId()).getResultList();

	}

//	@Override
//	public Integer givefeedback(Integer patid, Integer docid, Appointment appointment) {
//
//		User doc = sf.getCurrentSession().get(User.class, docid);
//		User pat = sf.getCurrentSession().get(User.class, patid);
//		Doctor d = sf.getCurrentSession().get(Doctor.class, doc.getDoctor().getDoctorId());
//		d.getApdetails().add(appointment);
//		Patient p = sf.getCurrentSession().get(Patient.class, pat.getPatient().getPatientId());
//		p.getAppdetails().add(appointment);
//		appointment.setDoctordet(d);
//		appointment.setPatientdet(p);
//		System.out.println(d);
//
//		System.out.println(doc);
//		return 1;
}
