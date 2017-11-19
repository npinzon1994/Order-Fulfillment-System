package model;

import java.util.HashMap;

import javafx.collections.ObservableList;

public class MasterDatabase {

	private static MasterDatabase _master;

	private static HashMap<String, InvItem> inventory;
	private static HashMap<String, CustomerServiceRep> userDatabase;
	private static HashMap<String, Invoice> invoiceDatabase;

	private static ObservableList<InvItem> allInvItems;
	private static ObservableList<InvItem> allSelectedInvItems;

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

	public static HashMap<String, CustomerServiceRep> getUserDatabase() {
		return userDatabase;
	}

	public static HashMap<String, Invoice> getInvoiceDatabase() {
		return invoiceDatabase;
	}

	public static ObservableList<InvItem> getAllInvItems() {
		return allInvItems;
	}

	public static void setAllInvItems(ObservableList<InvItem> allInvItems) {
		MasterDatabase.allInvItems = allInvItems;
	}

	public static ObservableList<InvItem> getAllSelectedInvItems() {
		return allSelectedInvItems;
	}

	public static void setAllSelectedInvItems(ObservableList<InvItem> allSelectedInvItems) {
		MasterDatabase.allSelectedInvItems = allSelectedInvItems;
	}

}
