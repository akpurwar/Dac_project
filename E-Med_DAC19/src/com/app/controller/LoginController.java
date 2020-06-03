package com.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IDao;
import com.app.pojos.User;
import com.app.pojos.otp;

@RestController
@RequestMapping("/login")
@CrossOrigin(allowedHeaders = "*")
public class LoginController {
	@Autowired
	IDao dao;
	
	@Autowired
	JavaMailSender sendmail;

	
	@PostMapping("/processotp")
	public ResponseEntity<?> validateotp(@RequestBody otp newotp)

	{
		Integer uid = dao.processOtp(newotp);
		if(uid !=null)
			return new ResponseEntity<Integer>(uid,HttpStatus.OK);
		return new ResponseEntity<Integer>(0,HttpStatus.INTERNAL_SERVER_ERROR);


	}
	
	
	@PostMapping("/checkmail")
	public ResponseEntity<Integer> checkmail(@RequestBody User u)
	{
	
		System.out.println("in login contoller/check mail");
		System.out.println(u.getEmail());
	
	   if(dao.checkmail(u)==1)
		   return new ResponseEntity<Integer>(1,HttpStatus.OK);
	   return new ResponseEntity<Integer>(0,HttpStatus.OK);
		
	}
	
	
	@GetMapping("/deactivate")
	public ResponseEntity<?> deactivate(@RequestParam Integer userid)
	{
		System.out.println("In deactivate");
		System.out.println(userid);
		dao.deactivate(userid);
		return new ResponseEntity<Integer>(1,HttpStatus.OK);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(@RequestBody User u)
	{
		dao.logout(u);
		return new ResponseEntity<Integer>(1,HttpStatus.OK);
	}
	

	@PostMapping("/forgetpassword")
	public ResponseEntity<?> getUserByEmail(@RequestBody User user,HttpSession hs)
	{
		System.out.println(user.getEmail());
		int otp = dao.generateOtp();
		User u = dao.getUserByEmail(user,otp);

		{
			//		int otp = dao.generateOtp();
			//		hs.setAttribute("otp",otp);
			//		hs.setMaxInactiveInterval(60);
			//	try {
			//		TimeUnit.MINUTES.sleep(1);
			//	} catch (InterruptedException e) {
			//		// TODO Auto-generated catch block
			//		e.printStackTrace();
			//	}
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(user.getEmail());
			mail.setSubject("OTP");
			mail.setText("Your OTP is " + otp);
			sendmail.send(mail);



		}
		return new  ResponseEntity<Integer>(1,HttpStatus.OK);
	}
	
	
	
	@PutMapping("/newpassword")
	public ResponseEntity<?> newpassword(@RequestParam Integer uid,@RequestBody User u)
	{

		if(dao.setNewPassword(uid, u)==1)
			return new ResponseEntity<String>("Password set Successfully",HttpStatus.OK);
		return new ResponseEntity<String>("Failed to update password",HttpStatus.INTERNAL_SERVER_ERROR);
		
	}

}