package model;

import java.util.HashMap;

public class MasterDatabase {

	private static MasterDatabase _master;
	
	private static HashMap<String, InvItem> inventory;
	private static HashMap<String, User> userDatabase;
	private static HashMap<String, Invoice> invoiceDatabase;

	private MasterDatabase() {
		inventory = new HashMap<>();
		userDatabase = new HashMap<>();
		invoiceDatabase = new HashMap<>();
	}
	
	public static MasterDatabase getMasterDatabase() {
		if (_master == null) {
			_master = new MasterDatabase();
		} else {
			System.out.println("A Master Database already exists");
		}
		return _master;
	}

	public static HashMap<String, InvItem> getInventory() {
		return inventory;
	}

	public static HashMap<String, User> getUserDatabase() {
		return userDatabase;
	}

	public static HashMap<String, Invoice> getInvoiceDatabase() {
		return invoiceDatabase;
	}

}
