package com.app.pojos;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "T_ADDRESS_TABLE")
public class Address {
	private Integer addressId;
	private String street;
	private String city;
	private String state;
	private String zip;
	@JsonBackReference
	private User user;


	public Address() {
		// TODO Auto-generated constructor stub
	}





	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getAddressid() {
		return addressId;
	}
	public void setAddressid(Integer addressid) {
		this.addressId = addressid;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}

	@OneToOne
	@JoinColumn(name = "userid")
	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Address [addressid=" + addressId + ", street=" + street + ", city=" + city + ", state=" + state + ", zip="
				+ zip + "]";
	}





}
