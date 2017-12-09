package model;

import java.io.Serializable;

import javafx.scene.image.Image;

public class InvItem implements Serializable {

	private static final long serialVersionUID = 1L;

	private String description;
	private String category;
	private double price;
	private String specs;
	private String status;
	private static int itemId = 1000;
	private String id;
	private String condition;
	private double weight;

	public InvItem(String description, String category, double price, String specs, String status, String condition) {
		super();
		this.description = description;
		this.category = category;
		this.price = price;
		this.specs = specs;
		this.status = status;
		this.id = String.valueOf(itemId++);
		this.condition = condition;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getItemId() {
		return id;
	}

	public static int getItemIdInt() {
		return itemId;
	}

	public static void setItemId(int itemId) {
		InvItem.itemId = itemId;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Override
	public String toString() {
		return description;
	}

}
