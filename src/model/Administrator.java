package model;

import java.io.Serializable;
import java.time.LocalDate;

import javafx.scene.image.Image;

public class Administrator extends CustomerServiceRep implements Serializable {

	private static final long serialVersionUID = 1L;

	public Administrator(String firstName, String lastName, String password, String hireDate, String termDate,
			String status, String position) {
		super(firstName, lastName, password, hireDate, termDate, status, position);
		setStoreLevel(3);
		setPosition("Administrator");

	}

	public Administrator() {
		setStoreLevel(3);
		setPosition("Administrator");
	}

}
