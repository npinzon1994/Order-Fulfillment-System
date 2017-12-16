package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

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

	public Invoice() {
		this.invoiceNumber = "#HFH" + String.valueOf(idCounter++);
		this.dateString = date.toString();
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public ArrayList<InvItem> getItems() {
		return items;
	}

	public void setItems(ArrayList<InvItem> items) {
		this.items = items;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getShippingCost() {
		return shippingCost;
	}

	public void setShippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String status) {
		this.orderStatus = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public static int getIdCounter() {
		return idCounter;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public static void setIdCounter(int idCounter) {
		Invoice.idCounter = idCounter;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

}
