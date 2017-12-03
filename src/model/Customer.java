package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public Customer() {
		this.id = "C" + String.valueOf(idInt++);
		cart = new ArrayList<>();
		orders = new HashMap<>();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getId() {
		return id;
	}

	public static int getIdInt() {
		return idInt;
	}

	public static void setIdInt(int idInt) {
		Customer.idInt = idInt;
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

	public ArrayList<InvItem> getCart() {
		return cart;
	}

	public void setCart(ArrayList<InvItem> cart) {
		this.cart = cart;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

	public HashMap<String, Invoice> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<String, Invoice> orders) {
		this.orders = orders;
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return lastName + ", " + firstName;
	}

}
