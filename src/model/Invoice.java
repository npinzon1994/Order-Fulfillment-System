package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * 
 * This class is to be used to create an Order for a customer. 
 * 
 * @author Nick Pinzon
 * @version 1.0.0 -- December 15, 2017
 *
 */

public class Invoice implements Serializable {

	private static final long serialVersionUID = 1L;

	private static int idCounter = 1;

	private String invoiceNumber;
	private LocalDate date = LocalDate.now();
	private String dateString;
	private Customer customer;
	private Address shippingAddress;
	private Address billingAddress;
	private String orderStatus;
	private ArrayList<InvItem> items = new ArrayList<>();
	private double subtotal;
	private double shippingCost;
	private double total;
	private String shippingMethod;
	private String remark1;
	private String remark2;

	/**
	 * No-arg constructor for creating an Invoice object.
	 */
	
	public Invoice() {
		this.invoiceNumber = "#HFH" + String.valueOf(idCounter++);
		this.dateString = date.toString();
	}

	/**
	 * Get the order's invoice number.
	 * 
	 * @return invoiceNumber
	 */
	
	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	/**
	 * Get the order's shipping address
	 * 
	 * @return shippingAddress
	 */
	
	public Address getShippingAddress() {
		return shippingAddress;
	}

	/**
	 * Change this order's associated shipping address (Not its customer's shipping address)
	 * to the String passed into this method.
	 * 
	 * @return shippingAddress
	 */
	
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	/**
	 * Get the order's billing address
	 * 
	 * @return billingAddress
	 */
	
	public Address getBillingAddress() {
		return billingAddress;
	}

	/**
	 * Change this order's associated billing address (Not its customer's billing address)
	 * to the String passed into this method.
	 * 
	 * @return billingAddress
	 */
	
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	/**
	 * Get the order's shopping cart items
	 * 
	 * @return items
	 */
	
	public ArrayList<InvItem> getItems() {
		return items;
	}

	/**
	 * Replace all the items in this order's shopping cart with the ArrayList being
	 * passed into this method.
	 * 
	 * @return items
	 */
	
	public void setItems(ArrayList<InvItem> items) {
		this.items = items;
	}

	/**
	 * Get the sub total of the order
	 * 
	 * @return subtotal
	 */
	
	public double getSubtotal() {
		return subtotal;
	}

	/**
	 * Set the sub total for this order.
	 * 
	 * @return subtotal
	 */
	
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	/**
	 * Get the shipping cost of the order
	 * 
	 * @return shippingCost
	 */
	
	public double getShippingCost() {
		return shippingCost;
	}

	/**
	 * Set the shipping cost for this order
	 * 
	 * @return shippingCost
	 */
	
	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}

	/**
	 * Get the date the order was created
	 * 
	 * @return date
	 */
	
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Get the order's status. Status can be "Processed", "Confirmed", "Shipped", "Customer Picked Up", 
	 * "Returned" or "Cancelled".
	 * 
	 * @return orderStatus
	 */
	
	public String getOrderStatus() {
		return orderStatus;
	}

	/**
	 * Set the status for this order. Status can be "Processed", "Confirmed", "Shipped", "Customer Picked Up", 
	 * "Returned" or "Cancelled".
	 * 
	 * @return orderStatus
	 */
	
	public void setOrderStatus(String status) {
		this.orderStatus = status;
	}

	/**
	 * Get the total cost of the order.
	 * 
	 * @return total
	 */
	
	public double getTotal() {
		return total;
	}

	/**
	 * Set the total for this order.
	 * 
	 * @return total
	 */
	
	public void setTotal(double total) {
		this.total = total;
	}

	/**
	 * Get the order's shipping method. Can be "Standard Ground", "Second Day Express",
	 * "Next Day Air", or "Pickup In-Store".
	 * 
	 * @return shippingMethod
	 */
	
	public String getShippingMethod() {
		return shippingMethod;
	}

	/**
	 * Set shipping method for this order.
	 * 
	 * @return shippingMethod
	 */
	
	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	/**
	 * Get the order's invoice number integer value.
	 * 
	 * @return idCounter
	 */
	
	public static int getIdCounter() {
		return idCounter;
	}

	/**
	 * Get the date the order was created.
	 * 
	 * @return dateString
	 */
	
	public String getDateString() {
		return dateString;
	}

	/**
	 * Set the order's invoice number integer value
	 * 
	 * @return orderStatus
	 */

	public static void setIdCounter(int idCounter) {
		Invoice.idCounter = idCounter;
	}

	/**
	 * Get the customer assigned to this order.
	 * 
	 * @return customer
	 */
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	/**
	 * Get the order's first remark.
	 * 
	 * @return remark1
	 */
	
	public String getRemark1() {
		return remark1;
	}

	/**
	 * Overwrites the first remark for this order.
	 * 
	 * @return remark1
	 */
	
	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	/**
	 * Get the order's second remark.
	 * 
	 * @return remark2
	 */
	
	public String getRemark2() {
		return remark2;
	}

	/**
	 * Overwrites the first remark for this order.
	 * 
	 * @return remark1
	 */
	
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

}
