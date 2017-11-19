package model;

import java.time.LocalDate;

import javafx.scene.image.Image;

public class CustomerServiceRep extends User {

	private static int idInt = 1;
	private String id;
	private LocalDate hireDate;
	private LocalDate termDate;
	private String status;
	private String position;
	private Image picture;

	public CustomerServiceRep(String firstName, String lastName, DateOfBirth dob, String email, String phone,
			String password, Address address, LocalDate hireDate, LocalDate termDate, String status, String position,
			Image picture) {
		super(firstName, lastName, dob, email, phone, password, address);
		this.id = "E" + String.valueOf(idInt++);
		this.hireDate = hireDate;
		setStoreLevel(1);
		setPosition("Customer Service Rep");
	}

	public CustomerServiceRep() {
		this.id = "E" + String.valueOf(idInt++);
		setStoreLevel(1);
		setPosition("Customer Service Rep");
	}

	public String getId() {
		return id;
	}

	public void setHireDate(LocalDate date) {
		this.hireDate = date;
	}

	public LocalDate getHireDate() {
		return hireDate;
	}

	public LocalDate getTermDate() {
		return termDate;
	}

	public void setTermDate(LocalDate termDate) {
		this.termDate = termDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Image getPicture() {
		return picture;
	}

	public void setPicture(Image picture) {
		this.picture = picture;
	}

	@Override
	public String toString() {
		return id + " - " + getLastName() + ", " + getFirstName();
	}
	
	

}
