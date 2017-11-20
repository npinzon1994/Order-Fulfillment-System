package model;

import java.time.LocalDate;

import javafx.scene.image.Image;

public class Administrator extends CustomerServiceRep {

	public Administrator(String firstName, String lastName, String password, String hireDate, String termDate,
			String status, String position, Image picture) {
		super(firstName, lastName, password, hireDate, termDate, status, position, picture);
		setStoreLevel(3);
		setPosition("Administrator");

	}

	public Administrator() {
		setStoreLevel(3);
		setPosition("Administrator");
	}

}
