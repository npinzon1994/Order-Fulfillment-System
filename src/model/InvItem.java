package model;

import javafx.scene.image.Image;

public class InvItem {

	private String description;
	private String category;
	private double price;
	private String specs;
	private int quantity;
	private static int itemId = 1000;
	private String id;
	private String condition;
	private Image image;

	public InvItem(String description, String category, double price, String specs, int quantity, String condition,
			Image image) {
		super();
		this.description = description;
		this.category = category;
		this.price = price;
		this.specs = specs;
		this.quantity = quantity;
		this.id = String.valueOf(itemId++);
		this.condition = condition;
		this.image = image;
	}

	public InvItem() {
		this.id = String.valueOf(itemId++);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getSpecs() {
		return specs;
	}

	public void setSpecs(String specs) {
		this.specs = specs;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getItemId() {
		return id;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return description;
	}
	
	

}
