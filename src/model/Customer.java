package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * This class is to be used to create a Customer. The Customer's sole purpose is to provide
 * identification for the orders. The Customers do not use this program at all. The customer is
 * present at the store while the employee is creating an order for them.
 * 
 * @author Nick Pinzon
 * @version 1.0.0 -- December 15, 2017
 *
 */

public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	private static int idInt;

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private Address billingAddress;
	private Address shippingAddress;
	private ArrayList<InvItem> cart;
	private HashMap<String, Invoice> orders;
	private String shippingMethod;
	private double subtotal;
	private double shippingCost;
	private double total;

	/**
	 * 
	 * Constructs a Customer object.
	 * 
	 * @param firstName First Name
	 * @param lastName Last Name
	 * @param email Email Address
	 * @param phone Phone Number
	 * @param shippingAddress Shipping Address
	 * @param billingAddress Billing Address
	 */
	
	public Customer(String firstName, String lastName, String email, String phone, Address shippingAddress,
			Address billingAddress) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phone = phone;
		this.billingAddress = billingAddress;
		this.shippingAddress = shippingAddress;
		this.id = "C" + String.valueOf(idInt++);
	}

	/**
	 * No arg constructor for creating a customer.
	 */
	
	public Customer() {
		this.id = "C" + String.valueOf(idInt++);
		cart = new ArrayList<>();
		orders = new HashMap<>();
	}

	/**
	 * Get the customer's first name
	 * 
	 * @return The customer's first name
	 */
	
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Changes the customer's first name
	 * 
	 * @param New first name
	 */
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Get the customer's last name
	 * 
	 * @return The customer's last name
	 */
	
	public String getLastName() {
		return lastName;
	}

	/**
	 * Changes the customer's last name
	 * 
	 * @param New last name
	 */
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Get the customer's email address
	 * 
	 * @return The customer's email address
	 */
	
	public String getEmail() {
		return email;
	}

	/**
	 * Changes the customer's email address
	 * 
	 * @param New email address
	 */
	
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Get the customer's phone number
	 * 
	 * @return The customer's phone number
	 */
	
	public String getPhone() {
		return phone;
	}

	/**
	 * Changes the customer's phone number
	 * 
	 * @param New phone number
	 */
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Get the customer's ID
	 * 
	 * @return The customer's ID
	 */
	
	public String getId() {
		return id;
	}

	/**
	 * Get the customer's ID integer value
	 * 
	 * @return The customer's ID integer value
	 */
	
	public static int getIdInt() {
		return idInt;
	}

	/**
	 * Changes the customer's ID integer value
	 * 
	 * @param New ID integer value
	 */
	
	public static void setIdInt(int idInt) {
		Customer.idInt = idInt;
	}

	/**
	 * Get the customer's billing address
	 * 
	 * @return The customer's billing address
	 */
	
	public Address getBillingAddress() {
		return billingAddress;
	}

	/**
	 * Changes the customer's billing address
	 * 
	 * @param New billing address
	 */
	
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	/**
	 * Get the customer's shipping address
	 * 
	 * @return The customer's shipping address
	 */
	
	public Address getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * Changes the customer's shipping address
	 * 
	 * @param New shipping address
	 */
	
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	/**
	 * Get the customer's shopping cart
	 * 
	 * @return The customer's shopping cart
	 */
	
	public ArrayList<InvItem> getCart() {
		return cart;
	}

	/**
	 * Changes the customer's shopping cart
	 * 
	 * @param New shopping cart
	 */
	
	public void setCart(ArrayList<InvItem> cart) {
		this.cart = cart;
	}

	/**
	 * Get the customer's shipping method
	 * 
	 * @return The customer's first name
	 */
	
	public String getShippingMethod() {
		return shippingMethod;
	}

	/**
	 * Changes the customer's shipping method for their current order
	 * 
	 * @param New shipping method for current order
	 */
	
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	/**
	 * Get the customer's orders
	 * 
	 * @return The customer's orders
	 */
	
	public HashMap<String, Invoice> getOrders() {
		return orders;
	}

	/**
	 * Replaces the customer's orders with a new HashMap of orders
	 * 
	 * @param New order HashMao
	 */
	
	public void setOrders(HashMap<String, Invoice> orders) {
		this.orders = orders;
	}

	/**
	 * Get the customer's current order sub total
	 * 
	 * @return The customer's current order sub total
	 */
	
	public double getSubtotal() {
		return subtotal;
	}

	/**
	 * Changes the customer's sub total for their current order
	 * 
	 * @param New sub total for current order
	 */
	
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	/**
	 * Get the customer's shipping cost for their current order
	 * 
	 * @return The customer's shipping cost for their current order
	 */
	
	public double getShippingCost() {
		return shippingCost;
	}

	/**
	 * Changes the customer's shipping cost for their current order
	 * 
	 * @param New shipping cost
	 */
	
	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}

	/**
	 * Get the customer's total cost for their current order
	 * 
	 * @return The customer's total cost for their current order
	 */
	
	public double getTotal() {
		return total;
	}

	/**
	 * Changes the customer's total for their current order
	 * 
	 * @param New order total
	 */
	
	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return lastName + ", " + firstName;
	}

}
