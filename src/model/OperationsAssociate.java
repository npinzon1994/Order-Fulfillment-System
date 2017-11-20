package model;

import java.time.LocalDate;

import javafx.scene.image.Image;

public class OperationsAssociate extends CustomerServiceRep {

	public OperationsAssociate(String firstName, String lastName, String password, String hireDate, String termDate,
			String status, String position, Image picture) {
		super(firstName, lastName, password, hireDate, termDate, status, position, picture);
		setStoreLevel(2);
		setPosition("Operations Associate");

	}

	public OperationsAssociate() {
		setStoreLevel(2);
		setPosition("Operations Associate");
	}

}
