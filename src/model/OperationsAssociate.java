package model;

import java.io.Serializable;
import java.time.LocalDate;

import javafx.scene.image.Image;

public class OperationsAssociate extends CustomerServiceRep implements Serializable {

	private static final long serialVersionUID = 1L;

	public OperationsAssociate(String firstName, String lastName, String password, String hireDate, String termDate,
			String status, String position) {
		super(firstName, lastName, password, hireDate, termDate, status, position);
		setStoreLevel(2);
		setPosition("Operations Associate");

	}

	public OperationsAssociate() {
		setStoreLevel(2);
		setPosition("Operations Associate");
	}

}
