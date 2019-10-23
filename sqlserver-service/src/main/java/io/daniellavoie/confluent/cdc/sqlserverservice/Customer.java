package io.daniellavoie.confluent.cdc.sqlserverservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer {
	private long id;
	private String lastName;
	private String firstName;
	private String country;
	private String addressCity;
	private String addressStreet;
	private String addressNumber;

	public Customer() {

	}

	public Customer(long id, String lastName, String firstName, String country, String addressCity,
			String addressStreet, String addressNumber) {
		super();
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.country = country;
		this.addressCity = addressCity;
		this.addressStreet = addressStreet;
		this.addressNumber = addressNumber;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressStreet() {
		return addressStreet;
	}

	public void setAddressStreet(String addressStreet) {
		this.addressStreet = addressStreet;
	}

	public String getAddressNumber() {
		return addressNumber;
	}

	public void setAddressNumber(String addressNumber) {
		this.addressNumber = addressNumber;
	}
}
