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
	private String orderStatus;
	private ArrayList<InvItem> items = new ArrayList<>();
	private double subtotal;
	private double shippingCost;
	private double total;
	private String shippingMethod;

	public Invoice(Customer customer, String orderStatus, ArrayList<InvItem> items, double subtotal,
			double shippingCost, double total) {
		this.invoiceNumber = "#HFH" + String.valueOf(idCounter++);
		this.customer = customer;
		this.orderStatus = orderStatus;
		this.items = items;
		this.subtotal = subtotal;
		this.shippingCost = shippingCost;
		this.total = total;
	}

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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	public double getshippingCost() {
		return shippingCost;
	}

	public void setshippingCost(double shippingCost) {
		this.shippingCost = shippingCost;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(String status){
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

}
