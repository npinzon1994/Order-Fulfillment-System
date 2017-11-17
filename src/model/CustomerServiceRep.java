package model;

import java.time.LocalDate;

public class CustomerServiceRep extends User {

	private static int idInt = 1;
	private String id;
	private LocalDate hireDate;

	public CustomerServiceRep(String firstName, String lastName, DateOfBirth dob, String email, String phone,
			String password, Address address, LocalDate hireDate) {
		super(firstName, lastName, dob, email, phone, password, address);
		this.id = "E" + String.valueOf(idInt++);
		this.hireDate= hireDate;
		setStoreLevel(1);
	}

	public CustomerServiceRep() {
		this.id = "E" + String.valueOf(idInt++);
		setStoreLevel(1);
	}

	public String getId(){
		return id;
	}
	
	public void setHireDate(LocalDate date){
		this.hireDate = date;
	}
	
	public LocalDate getHireDate(){
		return hireDate;
	}
	
}
