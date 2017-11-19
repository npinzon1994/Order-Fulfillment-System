package model;

import java.time.LocalDate;

import javafx.scene.image.Image;

public class OperationsAssociate extends CustomerServiceRep {

	public OperationsAssociate(String firstName, String lastName, DateOfBirth dob, String email, String phone,
			String password, Address address, String hireDate, String termDate, String status, String position,
			Image picture) {
		super(firstName, lastName, dob, email, phone, password, address, hireDate, termDate, status, position, picture);
		setStoreLevel(2);
		setPosition("Operations Associate");

	}

	public OperationsAssociate() {
		setStoreLevel(2);
		setPosition("Operations Associate");
	}

}
