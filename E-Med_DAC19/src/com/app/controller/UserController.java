package com.app.controller;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.omg.CORBA.TIMEOUT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;



import com.app.dao.IDao;
import com.app.pojos.Address;
import com.app.pojos.Specialisation;
import com.app.pojos.User;
import com.app.pojos.otp;
import com.fasterxml.jackson.core.JsonParser;

@RestController
@RequestMapping("/user")
@CrossOrigin(allowedHeaders = "*")
public class UserController {

	@Autowired
	IDao dao;

	@Autowired
	JavaMailSender sendmail;

	public UserController() {
		System.out.println("In User Controller");
	}

	
	
	@PostMapping("/register")
	public Integer register(@RequestBody User user) {
		return dao.register(user);
	}

	@PostMapping("/validate")
	public ResponseEntity<?> validate(@RequestBody User user) {
		User u = dao.getUser(user);
		if (u == null)
			return new ResponseEntity<String>("User Not Found", HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<User>(u, HttpStatus.OK);
	}

	@GetMapping("/details")
	public ResponseEntity<?> getdetails(@RequestParam Integer userid) {
		System.out.println(dao.getalldetails(userid).getAddress());
		return new ResponseEntity<User>(dao.getalldetails(userid), HttpStatus.OK);
	}

	@GetMapping("/doctors")
	public ResponseEntity<?> getDoctors() {
		return new ResponseEntity<List<User>>(dao.getDoctors(), HttpStatus.OK);

	}

	@GetMapping("/patients")
	public ResponseEntity<?> getPatients() {
		return new ResponseEntity<List<User>>(dao.getPatients(), HttpStatus.OK);

	}

	@PostMapping("/address")
	public ResponseEntity<?> getAddress(@RequestBody String email)
	{
//		System.out.println(email);
//		return null;
		User user =new User();
		user.setEmail(email);
		return new ResponseEntity<User>(dao.getUserByEmail(user),HttpStatus.OK);
	}
	// @PostMapping("/processotp")
	// public ResponseEntity<?> validateotp(@RequestBody otp newotp,HttpSession hs)
	//
	// {
	//
	// System.out.println("In process otp");
	// System.out.println("recievd otp" + newotp.getOtpNo());
	// System.out.println("session att otp " + hs.getAttribute("otp"));
	// try
	// {
	//
	//
	// {
	// hs.removeAttribute("otp");
	// return new ResponseEntity<Integer>(1,HttpStatus.OK);
	//
	// }
	// else
	// {
	// return new ResponseEntity<Integer>(0,HttpStatus.UNAUTHORIZED);
	// }
	// }
	// catch (Exception e) {
	// System.out.println("session invalidated");
	// return new ResponseEntity<>("Otp Doesnt exist,Resend
	// Otp",HttpStatus.INTERNAL_SERVER_ERROR);
	// }
	// }
@PostMapping("/updateaddress")
public ResponseEntity<?> updateAddress(@RequestBody Address newAddress)
{
	if(newAddress.getAddressid()==null)
	{
		dao.addNewAddress(newAddress);
	}
	else
  	if(dao.updateAddress(newAddress)!=0)
  		return new ResponseEntity<Integer>(1,HttpStatus.OK);
  	return new ResponseEntity<Integer>(0,HttpStatus.INTERNAL_SERVER_ERROR);
  	
	
}

@PutMapping("/addnewaddress")
public ResponseEntity<?> addnewaddress(@RequestParam Integer userid,@RequestBody Address address)
{
	dao.addaddress(userid, address);
	return new ResponseEntity<Integer>(1,HttpStatus.OK);
}
@GetMapping("/qualification")
public ResponseEntity<?> getdetails(@RequestParam String specialisation) {
	System.out.println(dao.getSpecialisation(specialisation));
	return new ResponseEntity<List<Specialisation>>( dao.getSpecialisation(specialisation), HttpStatus.OK);
}
@GetMapping("/doctorid")
public ResponseEntity<?>getDocbyId( @RequestParam Integer userid) {
	System.out.println(dao.getDocbyId(userid));
	return new ResponseEntity<List<Specialisation>>(dao.getDocbyId(userid), HttpStatus.OK);
	
	
}
}
