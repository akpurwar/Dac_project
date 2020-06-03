package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IPatientDao;
import com.app.pojos.Appointment;
import com.app.pojos.Feedback;
import com.app.pojos.Specialisation;
import com.app.pojos.User;

@RestController
@RequestMapping("/patient")
@CrossOrigin(allowedHeaders = "*")
public class PatientController {
	
	@Autowired
	IPatientDao patdao;
	
	
	
	
	@PostMapping("/patregister")
	public Integer register(@RequestBody User user) {
		return patdao.registerpatient(user);
	}
	
	@GetMapping("/patapp")
	public ResponseEntity<List<Appointment>> getappointments(@RequestParam Integer patid)
	{
	   
		return new ResponseEntity<List<Appointment>>(patdao.getappointment(patid),HttpStatus.OK);
	}

	@GetMapping("/showdecline")
	public ResponseEntity<List<Appointment>> getdecappointments(@RequestParam Integer patid)
	{
	   
		return new ResponseEntity<List<Appointment>>(patdao.getdecappointment(patid),HttpStatus.OK);
	}
	
	@GetMapping("/getdoneapp")
	public ResponseEntity<List<Appointment>> getdoneappointments(@RequestParam Integer patid)
	{
	   
		return new ResponseEntity<List<Appointment>>(patdao.getdoneappointment(patid),HttpStatus.OK);
	}
	
	@PutMapping("/givefeedback")
	public ResponseEntity<?> givefeedback(@RequestParam Integer apid,@RequestBody Feedback feedback)
	{
		patdao.givefeedback(apid, feedback);
		return new ResponseEntity<Integer>(1,HttpStatus.OK);
	}
	
	
	
	@PutMapping("/requestapp")
	public ResponseEntity<Integer> requestapp(@RequestParam Integer patid,@RequestParam Integer docid,@RequestBody Appointment appointment)
	{
		System.out.println("in Patient Controller");
		System.out.println("docid "+docid);
		System.out.println("patid "+patid);
		patdao.requestdoc(patid, docid,appointment);
		return new ResponseEntity<Integer>(1,HttpStatus.OK);
		
	}
	
	
	

}
