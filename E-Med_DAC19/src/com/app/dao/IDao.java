package com.app.dao;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.pojos.Address;
import com.app.pojos.Clinic;
import com.app.pojos.Specialisation;
import com.app.pojos.User;
import com.app.pojos.otp;

public interface IDao {

	public Integer register(User user);
	public User getalldetails(Integer userid);
	User getUser(User user);
	List<User> getDoctors();
	List<User> getPatients();
	
	Integer generateOtp();

	Integer processOtp(otp newotp);
	Integer setNewPassword(Integer uid, User user);
	User getUserByEmail(User user, int rotp);
	Address getAddress(Integer addressId);
	
	Integer updateAddress(Address newAddress);
	Integer addNewAddress(Address newAddress);
	User getUserByEmail(User user);
	Integer addaddress(Integer userid, Address naddress);
	Integer logout(User u);
	Integer deactivate(Integer userid);
	Integer checkmail(User u);
	List<Specialisation> getDocbyId(Integer userid);
	List<Specialisation> getSpecialisation(String specialisation);
	
	

	

}
