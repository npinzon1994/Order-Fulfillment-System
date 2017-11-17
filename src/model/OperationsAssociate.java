package model;

import java.time.LocalDate;

public class OperationsAssociate extends CustomerServiceRep {

	public OperationsAssociate(String firstName, String lastName, DateOfBirth dob, String email, String phone,
			String password, Address address, LocalDate hireDate) {
		super(firstName, lastName, dob, email, phone, password, address, hireDate);
		setStoreLevel(2);

	}

	public OperationsAssociate() {
		setStoreLevel(2);
	}

}
