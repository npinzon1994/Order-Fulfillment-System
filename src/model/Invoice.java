package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Invoice {

	private final double SALES_TAX = 0.08625;
	private static int idCounter = 1;

	private String invoiceNumber;
	private LocalDate date;
	private Customer customer;
	private Address billingAddress;
	private Address shippingAddress;
	private String orderStatus;
	private InvItem[] items;
	private double subtotal;
	private double tax;
	private double total;

	public Invoice(Customer customer, Address billingAddress, Address shippingAddress, String orderStatus,
			InvItem[] items, double subtotal, double tax, double total) {
		this.invoiceNumber = "#HFH" + String.valueOf(idCounter++);
		this.date = LocalDate.now();
		this.customer = customer;
		this.billingAddress = billingAddress;
		this.shippingAddress = shippingAddress;
		this.orderStatus = orderStatus;
		this.items = items;
		this.subtotal = subtotal;
		this.tax = tax;
		this.total = total;
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

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Address getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public InvItem[] getItems() {
		return items;
	}

	public void setItems(InvItem[] items) {
		this.items = items;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public LocalDate getDate() {
		return date;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}
