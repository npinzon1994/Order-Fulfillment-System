package model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * 
 * This class is to be used to create a Customer Service Representative. The Customer Service Representative 
 * has restricted access to this application. The Customer Service Representative has the ability to:
 * 
 * 1) Search inventory
 * 2) Add/Search customers
 * 3) Create customer orders
 * 4) Search/Return orders
 * 5) Pick orders
 * 6) Mark Customers' orders as picked up
 * 
 * @author Nick Pinzon
 * @version 1.0.0 -- December 15, 2017
 *
 */

public class CustomerServiceRep implements Serializable {

	private static final long serialVersionUID = 1L;

	private static int idInt = 1;

	private String id;
	private String firstName;
	private String lastName;
	private LocalDate date = LocalDate.now();
	private String hireDate;
	private String termDate;
	private String status;
	private String position;
	private int storeLevel;
	private String password;

	/**
	 * This constructs a Customer Service Representative object.
	 * 
	 * @param firstName First Name
	 * @param lastName Last Name
	 * @param password Password
	 * @param hireDate Hire Date
	 * @param termDate Term Date
	 * @param status Employment Status
	 * @param position Store Position
	 */
	
	public CustomerServiceRep(String firstName, String lastName, String password, String hireDate, String termDate,
			String status, String position) {
		this.id = "E" + String.valueOf(idInt++);
		this.firstName = firstName;
		this.lastName = lastName;
		this.hireDate = date.toString();
		setStoreLevel(1);
		setPosition("Customer Service Rep");
	}

	/**
	 * No arg constructor to create a Customer Service Representative object.
	 */
	
	public CustomerServiceRep() {
		this.id = "E" + String.valueOf(idInt++);
		setStoreLevel(1);
		setPosition("Customer Service Rep");
		setHireDate(date.toString());
	}

	/**
	 * Get the customer service rep's ID
	 * 
	 * @return The customer service rep's ID
	 */
	
	public String getId() {
		return id;
	}

	/**
	 * Get the customer service rep's ID integer value
	 * 
	 * @return The customer service rep's ID integer value
	 */
	
	public static int getIdInt() {
		return idInt;
	}

	/**
	 * Changes the customer service rep's ID integer value
	 * 
	 * @param New ID integer value
	 */
	
	public static void setIdInt(int idInt) {
		CustomerServiceRep.idInt = idInt;
	}

	/**
	 * Get the customer service rep's first name
	 * 
	 * @return The customer service rep's first name
	 */
	
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Changes the customer service rep's first name
	 * 
	 * @param New first name
	 */
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the customer service last name
	 * 
	 * @return The customer service last name
	 */
	
	public String getLastName() {
		return lastName;
	}

	/**
	 * Changes the customer service rep's last name
	 * 
	 * @param New last name
	 */
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * Changes the customer service rep's hire date
	 * 
	 * @param New hire date
	 */

	public void setHireDate(String date) {
		this.hireDate = date;
	}

	/**
	 * Get the customer service rep's hire date
	 * 
	 * @return The customer service rep's hire date
	 */
	
	public String getHireDate() {
		return hireDate;
	}

	/**
	 * Get the customer service rep's termination date
	 * 
	 * @return The customer service rep's termination date
	 */
	
	public String getTermDate() {
		return termDate;
	}

	/**
	 * Changes the customer service rep's termination date
	 * 
	 * @param New termination date
	 */
	
	public void setTermDate(String termDate) {
		this.termDate = termDate;
	}

	/**
	 * Get the customer service rep's employment status
	 * 
	 * @return The customer service rep's employment status
	 */
	
	public String getStatus() {
		return status;
	}

	/**
	 * Changes the customer service rep's employment status
	 * 
	 * @param New employment status
	 */
	
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Get the customer service rep's store position
	 * 
	 * @return The customer service rep's store position
	 */
	
	public String getPosition() {
		return position;
	}

	/**
	 * Changes the customer service rep's store position
	 * 
	 * @param New store position
	 */
	
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * Get the customer service rep's store level
	 * 
	 * @return The customer service rep's store level
	 */
	
	public int getStoreLevel() {
		return storeLevel;
	}

	/**
	 * Changes the customer service rep's store level
	 * 
	 * @param New store level
	 */
	
	public void setStoreLevel(int storeLevel) {
		this.storeLevel = storeLevel;
	}

	/**
	 * Get the customer service rep's password
	 * 
	 * @return The customer service rep's password
	 */
	
	public String getPassword() {
		return password;
	}

	/**
	 * Changes the customer service rep's password
	 * 
	 * @param New password
	 */
	
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return id + " - " + lastName + ", " + firstName;
	}

}
