package model;

import java.io.Serializable;

/**
 * 
 * This class is to be used to create Addresses for Customers. Customers and Invoices will
 * have two types of addresses: Shipping Address and Billing Address.
 * 
 * @author Nick Pinzon
 * @version 1.0.0 -- December 15, 2017
 *
 */

public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	private String streetAddress;
	private String city;
	private String state;
	private String zip;

	/**
	 * 
	 * Constructs an Address object.
	 * 
	 * @param streetAddress - The person's street address
	 * @param city - The person's city/town
	 * @param state - The person's state
	 * @param zip - The person's zip code
	 */
	
	public Address(String streetAddress, String city, String state, String zip) {
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	/**
	 * Constructs an Address object
	 */
	
	public Address() {

	}

	/**
	 * Makes a deep copy of the person's address
	 * @param address - The address to be copied
	 */
	
	public Address(Address address) {
		this.streetAddress = address.streetAddress;
		this.city = address.city;
		this.state = address.state;
		this.zip = address.zip;
	}

	/**
	 * Get the street address
	 * @return The street address
	 */
	
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * Allows you to change the street address
	 * @param streetAddress The new street address to replace the old one
	 */
	
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	/**
	 * Get the city
	 * @return The city
	 */
	
	public String getCity() {
		return city;
	}

	/**
	 * Allows you to change the city
	 * @param city City to be changed
	 */
	
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Returns the state
	 * @return The state
	 */
	
	public String getState() {
		return state;
	}

	/**
	 * Allows you to change the state
	 * @param state State to be changed
	 */
	
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Returns the zip code
	 * @return The zip code
	 */
	
	public String getZip() {
		return zip;
	}

	/**
	 * Allows you to change the zip code
	 * @param zip Zip to be changed
	 */
	
	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return streetAddress + "\n" + city + " " + state + ", " + zip;
	}
	
	

}
