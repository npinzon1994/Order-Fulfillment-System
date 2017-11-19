package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.image.Image;

public class CustomerServiceRep extends User {

	private static int idInt = 1;
	private String id;
	private LocalDate date = LocalDate.now();
	private String hireDate;
	private String termDate;
	private String status;
	private String position;
	private Image picture;
	private DateTimeFormatter dateFormat;
	private String dateString;

	public CustomerServiceRep(String firstName, String lastName, DateOfBirth dob, String email, String phone,
			String password, Address address, String hireDate, String termDate, String status, String position,
			Image picture) {
		super(firstName, lastName, dob, email, phone, password, address);
		this.id = "E" + String.valueOf(idInt++);
		dateFormat = DateTimeFormatter.ofPattern("MM-dd-YYYY");
		dateString = date.format(dateFormat);
		this.hireDate = hireDate;
		setStoreLevel(1);
		setPosition("Customer Service Rep");
	}

	public CustomerServiceRep() {
		this.id = "E" + String.valueOf(idInt++);
		setStoreLevel(1);
		setPosition("Customer Service Rep");
		dateFormat = DateTimeFormatter.ofPattern("MM-dd-YYYY");
		dateString = date.format(dateFormat);
		setHireDate(dateString);
	}

	public String getId() {
		return id;
	}

	public void setHireDate(String date) {
		this.hireDate = date;
	}

	public String getHireDate() {
		return hireDate;
	}

	public String getTermDate() {
		return termDate;
	}

	public void setTermDate(String termDate) {
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
