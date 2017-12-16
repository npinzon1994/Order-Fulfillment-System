package model;

import java.io.Serializable;

/**
 * 
 * This class is to be used to create an Operations Associate. The Operations Associate can perform most 
 * functions created for this application. The Operations Associate is able to:
 * 
 * 1) Add/Remove/Search inventory
 * 2) Add/Search customers
 * 3) Create customer orders
 * 4) Search/Return orders
 * 5) Pick orders
 * 6) Pack/Ship orders
 * 7) Mark Customers' orders as picked up
 * 
 * @author Nick Pinzon
 * @version 1.0.0 -- December 15, 2017
 *
 */

public class OperationsAssociate extends CustomerServiceRep implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor used to create an OperationsAssociate object.
	 * 
	 * @param firstName Operation Associate's first name
	 * @param lastName Operation Associate's last name
	 * @param password Operation Associate's password
	 * @param hireDate Operation Associate's hire date
	 * @param termDate Operation Associate's termination date
	 * @param status Operation Associate's employment status
	 * @param position Operation Associate's job title
	 */
	
	public OperationsAssociate(String firstName, String lastName, String password, String hireDate, String termDate,
			String status, String position) {
		super(firstName, lastName, password, hireDate, termDate, status, position);
		setStoreLevel(2);
		setPosition("Operations Associate");

	}

	/**
	 * No-arg constructor used to create an OperationsAssociate object.
	 */
	
	public OperationsAssociate() {
		setStoreLevel(2);
		setPosition("Operations Associate");
	}

}
