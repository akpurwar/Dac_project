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

import com.app.dao.IDoctorDao;
import com.app.pojos.Appointment;
import com.app.pojos.Clinic;
import com.app.pojos.Patient;
import com.app.pojos.Specialisation;
import com.app.pojos.User;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(allowedHeaders = "*")
public class DoctorController {
	
	public DoctorController() {
	System.out.println("in DocController");
	}
	@Autowired
	IDoctorDao docdao;
	
	
@PutMapping("/addclinic")
	public ResponseEntity<?> addClinic(@RequestParam Integer userid,@RequestBody Clinic data)
	{
		
	docdao.addClinic(userid,data);
		return new ResponseEntity<Integer>(1,HttpStatus.OK);
		
	}

@PostMapping("/docregister")
public Integer register(@RequestBody User user) {
	return docdao.registerdoctor(user);
}

@PutMapping("/addspec")
public ResponseEntity<?> addspec(@RequestParam Integer docid,@RequestBody Specialisation data)
{
	
docdao.addspec(docid,data);
	return new ResponseEntity<Integer>(1,HttpStatus.OK);
	
}
@GetMapping("/patdetails")
public ResponseEntity<?> patdetails(@RequestParam Integer patid)
{
	return new ResponseEntity<Patient>(docdao.getpatient(patid),HttpStatus.OK);
}

@PutMapping("/settime")
public ResponseEntity<?> settime(@RequestParam Integer apid,@RequestBody Appointment app )
{
	
return new ResponseEntity<Integer>(docdao.setappointment(apid, app),HttpStatus.OK);
}

@GetMapping("/decline")
public ResponseEntity<?> decline(@RequestParam Integer apid)
{
	return new ResponseEntity<Integer>(docdao.declinerequest(apid),HttpStatus.OK);
}


@GetMapping("/showrequest")
public ResponseEntity<?> showrequest(@RequestParam Integer docid)
{
	return new ResponseEntity<List<Appointment>>(docdao.showrequest(docid),HttpStatus.OK);
}

@GetMapping("/clinic")
public ResponseEntity<?> getClinic( @RequestParam Integer userid) {
System.out.println(docdao.getClinic(userid));
return new ResponseEntity<List<Clinic>>(docdao.getClinic(userid), HttpStatus.OK);
}

@GetMapping("/confirmed")
public ResponseEntity<?> getconfirmlist(@RequestParam Integer docid)
{
	return new ResponseEntity<List<Appointment>>(docdao.getconfirmedlist(docid),HttpStatus.OK);
	
}

@GetMapping("/appdone")
public ResponseEntity<?> appdone(@RequestParam Integer apid)
{
	return new ResponseEntity<Integer>(docdao.appdone(apid),HttpStatus.OK);
}




}
