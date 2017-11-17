package model;

import java.time.LocalDate;

public class Administrator extends CustomerServiceRep {
	
	public Administrator(String firstName, String lastName, DateOfBirth dob, String email, String phone,
			String password, Address address, LocalDate hireDate) {
		super(firstName, lastName, dob, email, phone, password, address, hireDate);
		setStoreLevel(3);
		
	}

	public Administrator() {
		setStoreLevel(3);
	}

}
