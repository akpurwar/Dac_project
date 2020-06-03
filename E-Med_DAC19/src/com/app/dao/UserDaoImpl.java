package com.app.dao;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Address;
import com.app.pojos.Doctor;
import com.app.pojos.Role;
import com.app.pojos.Specialisation;
import com.app.pojos.User;
import com.app.pojos.otp;

@Repository
@Transactional
public class UserDaoImpl implements IDao {

	@Autowired
	SessionFactory sf;
	
	@Autowired
	JavaMailSender sendmail;

	@Override
	public Integer register(User user) {

		
		user.setActive(true);
//		SimpleMailMessage mail = new SimpleMailMessage();
//		mail.setTo(user.getEmail());
//		mail.setSubject("EMED REGISTRATION");
//		mail.setText("WELCOME TO E-MED " + user.getFirstName() + ".You have succesfuly registerd with email "+user.getEmail() + "and password "+user.getPassword());
//		sendmail.send(mail);
		 Integer i= (Integer) sf.getCurrentSession().save(user);
	
		return i;

	}
	@Override
	public List<Specialisation> getDocbyId(Integer userid) {
		String jpql = "Select s from Specialisation s join fetch s.user where s.user.userId=:id";
	//String jpql = "Select u from User u  left outer join fetch u.docspecilaisation where u.userId=:id";
//		String jpql= "Select u from User u where u.userId=:userid and u.docspecilaisation.specid IN(select u.docspecilaisation.specid from User u where u.userId=:userid"; 
		
		return sf.getCurrentSession().createQuery(jpql, Specialisation.class).setParameter("id", userid).getResultList();
	}
	
	@Override
	public Integer checkmail(User u)
	{
		System.out.println("in check mail");
		System.out.println(u.getEmail());
		
		String jpql="Select u from User u where email=:em";
		User user = sf.getCurrentSession().createQuery(jpql,User.class).setParameter("em", u.getEmail()).getSingleResult();
		if(user==null)
			return 1;
		return 0;
		
	}
	
	
	public Integer deactivate(Integer userid)
	{
		sf.getCurrentSession().get(User.class, userid).setActive(false);
		return 1;
	}
	
	@Override
	public Integer logout(User u)
	{

		System.out.println("in logout dao layer");
		String jpql ="Select u from User u where email=:em";
		System.out.println(u.getEmail());
		User user = sf.getCurrentSession().createQuery(jpql,User.class).setParameter("em", u.getEmail()).getSingleResult();
		
		user.setOnline(false);
		return 1;
	}

	@Override
	public User getUser(User user)
	{
		System.out.println("in user");
		try
		{
			String jpql = "Select u from User u where email=:em and password=:pwd";

         
			User u = sf.getCurrentSession().createQuery(jpql,User.class).setParameter("em",user.getEmail()).setParameter("pwd",user.getPassword()).getSingleResult();
			 System.out.println(u);
			u.setOnline(true);
			return u;
		}
		catch (Exception e) {

			System.out.println("Not Found");
			return null;

		}

	}
	
	

	@Override
	public List<Specialisation> getSpecialisation(String specialisation) {

		String jpql = "select s from Specialisation s  join fetch s.user where s.specialisation=:specialisation";
		return sf.getCurrentSession().createQuery(jpql,Specialisation.class).setParameter("specialisation", specialisation).getResultList(); 
		
	}
	
	
	@Override
	public User getUserByEmail(User user)
	{
		System.out.println("in user");
		try
		{
			String jpql = "Select u from User u where email=:em ";

         
			User u = sf.getCurrentSession().createQuery(jpql,User.class).setParameter("em",user.getEmail()).getSingleResult();
			 System.out.println(u);
			u.setOnline(true);
			return u;
		}
		catch (Exception e) {

			System.out.println("Not Found");
			return null;

		}
		
	
		

	}
	@Override 
	public Integer addaddress(Integer userid,Address naddress)
	{
		User u = sf.getCurrentSession().get(User.class, userid);
		u.setAddress(naddress);
		naddress.setUser(u);
		return 1;
		
	}


	@Override
	public User getalldetails(Integer userid) {


		String jpql="Select u from User u left outer join fetch u.address where u.userId=:id";
		return sf.getCurrentSession().createQuery(jpql,User.class).setParameter("id",userid).getSingleResult();

	}

	@Override
	public List<User> getDoctors()
	{


		String jpql="Select u from User u where u.role='DOCTOR' and u.active=true";
		return sf.getCurrentSession().createQuery(jpql,User.class).getResultList();


	}
	@Override
	public List<User> getPatients()
	{


		String jpql="Select u from User u where u.role='PATIENT'";
		return sf.getCurrentSession().createQuery(jpql,User.class).getResultList();


	}
	
	@Override
	public Address getAddress(Integer addressId)
	{
		return sf.getCurrentSession().get(Address.class, addressId);
	}
	
	
	



	@Override
	public Integer setNewPassword(Integer uid,User user)
	{
		User u = sf.getCurrentSession().get(User.class, uid);
		if(u!=null)
		{
			u.setPassword(user.getPassword());
			return 1;
		}
		return 0 ;

	}


	@Override
	public User getUserByEmail(User user,int rotp)
	{
		String jpql="Select u from User u left outer join fetch u.otpnumbers where u.email=:em";
		otp newotp=new otp(0,new Date(),null);


		User u = sf.getCurrentSession().createQuery(jpql,User.class).setParameter("em",user.getEmail()).getSingleResult();
		if(u!=null)
		{
			newotp.setOtpNo(rotp);
			newotp.setUser(u);
			Date d2= new Date();
			d2.setMinutes(newotp.getValidFrom().getMinutes()+10);
			newotp.setValidTo(d2);
			u.getOtpnumbers().add(newotp);

		}
		return u;

	}



	@Override
	public Integer processOtp(otp newotp)
	{
		otp o = new otp();
		System.out.println(newotp.getOtpNo());
		String jpql = "Select o from otp o where o.otpNo=:id";
		o =   sf.getCurrentSession().createQuery(jpql,otp.class).setParameter("id",newotp.getOtpNo()).getSingleResult();
		System.out.println(o.getOtpNo());
		if(o!=null)

			;
			int c = o.getValidTo().compareTo(new Date());
			if(c>0)
			{
				System.out.println(c);
				return o.getUser().getUserId();
			}
			else
			{
				return 0;
			}
		
		

	}




	@Override
	public Integer generateOtp()
	{
		System.out.println("In generate otp");
		Random random = new Random();
		int num = random.nextInt(99999) + 99999;
		if (num < 100000 || num > 999999) 
		{
			num = random.nextInt(99999) + 99999;
			if (num < 100000 || num > 999999)
			{
				System.out.println("Unable to generate PIN at this time..");
			}
		}
		return num;


	}
	
	
	@Override
	public Integer updateAddress(Address newAddress)
	{
		try
		{
			System.out.println("In address dao method");
			System.out.println(newAddress.getAddressid());
		Address a = sf.getCurrentSession().get(Address.class, newAddress.getAddressid());
		a.setCity(newAddress.getCity());
		a.setState(newAddress.getState());
		a.setZip(newAddress.getZip());
		a.setStreet(newAddress.getStreet());
		return 1;
	}
		catch (Exception e) {
		System.out.println(e);
			return 0;
		}
		
	}
	
	@Override
	public Integer addNewAddress(Address newAddress)
	{
	  return (Integer) sf.getCurrentSession().save(newAddress);
	  
	}
	
	
		
	}




