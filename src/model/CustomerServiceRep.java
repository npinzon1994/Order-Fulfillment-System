package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.scene.image.Image;

public class CustomerServiceRep implements Serializable {

	private static final long serialVersionUID = 1L;

	private static int idInt = 1;

	private String id;
	private String firstName;
	private String lastName;
	private LocalDate date = LocalDate.now();
	private String hireDate;
	private String termDate;
	private String status;
	private String position;
	private transient Image image;
	private int storeLevel;

	public CustomerServiceRep(String firstName, String lastName, String password, String hireDate, String termDate,
			String status, String position) {
		this.id = "E" + String.valueOf(idInt++);
		this.firstName = firstName;
		this.lastName = lastName;
		this.hireDate = date.toString();
		setStoreLevel(1);
		setPosition("Customer Service Rep");
	}

	public CustomerServiceRep() {
		this.id = "E" + String.valueOf(idInt++);
		setStoreLevel(1);
		setPosition("Customer Service Rep");
		setHireDate(date.toString());
	}

	public String getId() {
		return id;
	}

	public static int getIdInt() {
		return idInt;
	}
	
	

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public static void setIdInt(int idInt) {
		CustomerServiceRep.idInt = idInt;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public int getStoreLevel() {
		return storeLevel;
	}

	public void setStoreLevel(int storeLevel) {
		this.storeLevel = storeLevel;
	}

	@Override
	public String toString() {
		return id + " - " + lastName + ", " + firstName;
	}

}
