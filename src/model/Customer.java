package model;

public class Customer extends User {

	private static int idInt = 1;
	private String id;

	public Customer(String firstName, String lastName, DateOfBirth dob, String email, String phone, String password,
			Address address) {
		super(firstName, lastName, dob, email, phone, password, address);
		this.id = "C" + String.valueOf(idInt++);
		setStoreLevel(1);

	}

	public Customer() {

	}

	public String getId() {
		return id;
	}

}
