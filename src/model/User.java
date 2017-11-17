package model;

public abstract class User {

	private String firstName;
	private String lastName;
	private DateOfBirth dob;
	private String email;
	private String phone;
	private String password;
	private int storeLevel;
	private Address address;

	public User(String firstName, String lastName, DateOfBirth dob, String email, String phone, String password,
			Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.address = address;
	}

	public User() {

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

	public DateOfBirth getDob() {
		return dob;
	}

	public void setDob(DateOfBirth dob) {
		this.dob = dob;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStoreLevel() {
		return storeLevel;
	}

	public void setStoreLevel(int storeLevel) {
		this.storeLevel = storeLevel;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

}
