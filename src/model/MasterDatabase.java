package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MasterDatabase implements Serializable {

	private static final long serialVersionUID = 1L;

	private static MasterDatabase _master;

	private static HashMap<String, InvItem> inventory;
	private static HashMap<String, CustomerServiceRep> employeeDatabase;
	private static HashMap<String, Customer> customerDatabase;
	private static HashMap<String, Invoice> invoiceDatabase;

	private static Customer orderCustomer;
	private static CustomerServiceRep loggedEmployee;

	private MasterDatabase() {
		inventory = new HashMap<>();
		employeeDatabase = new HashMap<>();
		customerDatabase = new HashMap<>();
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

	public static void setInventory(HashMap<String, InvItem> database) {
		inventory = database;
	}

	public static HashMap<String, CustomerServiceRep> getEmployeeDatabase() {
		return employeeDatabase;
	}

	public static void setEmployeeDatabase(HashMap<String, CustomerServiceRep> database) {
		employeeDatabase = database;
	}

	public static HashMap<String, Customer> getCustomerDatabase() {
		return customerDatabase;
	}

	public static void setCustomerDatabase(HashMap<String, Customer> database) {
		customerDatabase = database;
	}

	public static HashMap<String, Invoice> getInvoiceDatabase() {
		return invoiceDatabase;
	}

	public static void setInvoiceDatabase(HashMap<String, Invoice> database) {
		invoiceDatabase = database;
	}

	public static Customer getOrderCustomer() {
		return orderCustomer;
	}

	public static void setOrderCustomer(Customer orderCustomer) {
		MasterDatabase.orderCustomer = orderCustomer;
	}

	public static CustomerServiceRep getLoggedEmployee() {
		return loggedEmployee;
	}

	public static void setLoggedEmployee(CustomerServiceRep loggedEmployee) {
		MasterDatabase.loggedEmployee = loggedEmployee;
	}

	public static void saveInventory() {
		System.out.println("All content saved!");
		FileOutputStream fileOutput = null;
		ObjectOutputStream objectOutput = null;
		try {
			fileOutput = new FileOutputStream("inventory.dat");
			objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(getInventory());
			objectOutput.writeInt(InvItem.getItemIdInt());
			objectOutput.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadInventory() {
		System.out.println("All content loaded!");
		FileInputStream fileInput = null;
		ObjectInputStream objectInput = null;
		try {
			fileInput = new FileInputStream("inventory.dat");
			objectInput = new ObjectInputStream(fileInput);
			setInventory((HashMap<String, InvItem>) objectInput.readObject());
			InvItem.setItemId(objectInput.readInt());
			objectInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void saveEmployees() {
		System.out.println("All content saved!");
		FileOutputStream fileOutput = null;
		ObjectOutputStream objectOutput = null;
		try {
			fileOutput = new FileOutputStream("employees.dat");
			objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(getEmployeeDatabase());
			objectOutput.writeInt(CustomerServiceRep.getIdInt());
			objectOutput.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadEmployees() {
		System.out.println("All content loaded!");
		FileInputStream fileInput = null;
		ObjectInputStream objectInput = null;
		try {
			fileInput = new FileInputStream("employees.dat");
			objectInput = new ObjectInputStream(fileInput);
			setEmployeeDatabase((HashMap<String, CustomerServiceRep>) objectInput.readObject());
			CustomerServiceRep.setIdInt(objectInput.readInt());
			objectInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static void saveCustomers() {
		System.out.println("All content saved!");
		FileOutputStream fileOutput = null;
		ObjectOutputStream objectOutput = null;
		try {
			fileOutput = new FileOutputStream("customers.dat");
			objectOutput = new ObjectOutputStream(fileOutput);
			objectOutput.writeObject(getCustomerDatabase());
			objectOutput.writeInt(Customer.getIdInt());
			objectOutput.writeInt(Invoice.getIdCounter());
			objectOutput.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadCustomers() {
		System.out.println("All content loaded!");
		FileInputStream fileInput = null;
		ObjectInputStream objectInput = null;
		try {
			fileInput = new FileInputStream("customers.dat");
			objectInput = new ObjectInputStream(fileInput);
			setCustomerDatabase((HashMap<String, Customer>) objectInput.readObject());
			Customer.setIdInt(objectInput.readInt());
			Invoice.setIdCounter(objectInput.readInt());
			objectInput.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
