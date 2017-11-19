package model;

import java.time.LocalDate;

import javafx.scene.image.Image;

public class Administrator extends CustomerServiceRep {
	
	public Administrator(String firstName, String lastName, DateOfBirth dob, String email, String phone,
			String password, Address address, String hireDate, String termDate, String status, String position,
			Image picture) {
		super(firstName, lastName, dob, email, phone, password, address, hireDate, termDate, status, position, picture);
		setStoreLevel(3);
		setPosition("Administrator");
		
	}

	public Administrator() {
		setStoreLevel(3);
		setPosition("Administrator");
	}

}
