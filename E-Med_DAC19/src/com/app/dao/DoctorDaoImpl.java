package com.app.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Appointment;
import com.app.pojos.Clinic;
import com.app.pojos.Doctor;
import com.app.pojos.Patient;
import com.app.pojos.Specialisation;
import com.app.pojos.User;

@Repository
@Transactional
public class DoctorDaoImpl implements IDoctorDao {
	@Autowired
	SessionFactory sf;
	
	@Autowired
	JavaMailSender sendmail;
	
	@Override
	public Integer addClinic(Integer userid, Clinic clinicdata)
	{
		User u = sf.getCurrentSession().get(User.class, userid);
		u.setClinic(clinicdata);
		clinicdata.setUser(u);
		return 1;
	}

	@Override
	public Integer registerdoctor(User user)
	{
	Doctor d =new Doctor();
	user.setActive(true);
	SimpleMailMessage mail = new SimpleMailMessage();
	mail.setTo(user.getEmail());
	mail.setSubject("EMED REGISTRATION");
	mail.setText("WELCOME TO E-MED " + user.getFirstName() + ".You have succesfuly registerd with email "+user.getEmail() + "and password "+user.getPassword());
	sendmail.send(mail);
	 Integer i= (Integer) sf.getCurrentSession().save(user);
	d.setUser(user);
	user.setDoctor(d);
	return i;
	}

	@Override
	public Integer setappointment(Integer apid,Appointment appointment)
	{
		Appointment a = sf.getCurrentSession().get(Appointment.class, apid);
		a.setApptime(appointment.getApptime());
		a.setApstatus("CONFIRMED");
		return 1;
	}
	
	@Override
	public Integer addspec(Integer docid,Specialisation spec)
	{
	
		User u  = sf.getCurrentSession().get(User.class,docid);
	List<Specialisation> speclist =new ArrayList<>();
	speclist.add(spec);
	u.setDocspecilaisation(speclist);
	spec.setUser(u);
	return 1;
	
		
	}
	
	
	@Override
	public List<Appointment> showrequest(Integer docid)
	{
		User u =sf.getCurrentSession().get(User.class, docid);
		
		
		String jpql = "Select a from Appointment a where  a.doctordet.doctorId=:id and a.apstatus='PENDING'";
		return sf.getCurrentSession().createQuery(jpql,Appointment.class).setParameter("id",u.getDoctor().getDoctorId()).getResultList();
		
	}
	
	@Override
	public List<Appointment> getconfirmedlist(Integer docid)
	{
		User u =sf.getCurrentSession().get(User.class, docid);
		
		
		String jpql = "Select a from Appointment a where  a.doctordet.doctorId=:id and a.apstatus='CONFIRMED'";
		return sf.getCurrentSession().createQuery(jpql,Appointment.class).setParameter("id",u.getDoctor().getDoctorId()).getResultList();
		
	}
	
	
	
	@Override
	public Patient getpatient(Integer patid)
	{
		Patient p = sf.getCurrentSession().get(Patient.class, patid);
		return p;
	}

	@Override
	public Integer declinerequest(Integer apid)
	{
		Appointment a = sf.getCurrentSession().get(Appointment.class,apid);
		a.setApstatus("DECLINED");
		return 1;
	}
	 
	
	
	@Override
	public List<Clinic> getClinic(Integer userid) {
		String jpql = "Select c from Clinic c join fetch c.user where c.user.userId=:id";
	return sf.getCurrentSession().createQuery(jpql,Clinic.class).setParameter("id", userid).getResultList(); 
		
	}
	
	@Override
	public Integer appdone(Integer apid)
	{
		Appointment a=sf.getCurrentSession().get(Appointment.class, apid);
		a.setApstatus("DONE");
		return 1;
	}
	
	
}
