package model;

import java.io.Serializable;

/**
 * 
 * This class is to be used to create an Administrator. The Administrator can perform every function created for this application.
 * THe Administrator has the ability to:
 * 
 * 1) Add/Remove/Search employees
 * 2) Add/Remove/Search inventory
 * 3) Add/Search customers
 * 4) Create customer orders
 * 5) Search/Return orders
 * 6) Pick orders
 * 7) Pack/Ship orders
 * 8) Mark Customers' orders as picked up
 * 
 * @author Nick Pinzon
 * @version 1.0.0 -- December 15, 2017
 *
 */

public class Administrator extends CustomerServiceRep implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Constructs an Administrator object
	 * 
	 * @param firstName The admin's first name
	 * @param lastName The admin's last name
	 * @param password The admin's password
	 * @param hireDate The admin's hire date
	 * @param termDate The admin's term date
	 * @param status The admin's employment status
	 * @param position The admin's position
	 */
	
	public Administrator(String firstName, String lastName, String password, String hireDate, String termDate,
			String status, String position) {
		super(firstName, lastName, password, hireDate, termDate, status, position);
		setStoreLevel(3);
		setPosition("Administrator");

	}

	/**
	 * No arg constructor for Administrators
	 */
	
	public Administrator() {
		setStoreLevel(3);
		setPosition("Administrator");
	}

}
