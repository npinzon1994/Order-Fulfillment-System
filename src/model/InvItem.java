package model;

import java.io.Serializable;

/**
 * 
 * This class is to be used to create items to be ordered by customers. All
 * items are unique because they are all donations from customers. Therefore
 * there is no quantity attribute.
 * 
 * @author Nick Pinzon
 * @version 1.0.0 -- December 15, 2017
 *
 */

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

	/**
	 * Used to construct an InvItem object.
	 * 
	 * @param description
	 *            Description of item
	 * @param category
	 *            The item's category
	 * @param price
	 *            Price of the item
	 * @param specs
	 *            Item's specifications
	 * @param status
	 *            Item's status. Can be either "In Stock" or "Out of Stock".
	 * @param condition
	 *            Condition of item
	 * @param imagePath
	 */

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

	/**
	 * No-arg constructor used for creating InvItem objects
	 */
	
	public InvItem() {
		this.id = String.valueOf(itemId++);
	}

	/**
	 * Get description of item
	 * 
	 * @return description
	 */
	
	public String getDescription() {
		return description;
	}

	/**
	 * Changes the item's description to the specified string passed into this method.
	 * 
	 * @param description
	 */
	
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Get item's category
	 * 
	 * @return category
	 */
	
	public String getCategory() {
		return category;
	}

	/**
	 * Changes the item's category to the specified string passed into this method.
	 * 
	 * @param category
	 */
	
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Get price of item
	 * 
	 * @return price
	 */
	
	public double getPrice() {
		return price;
	}

	/**
	 * Changes the item's price to the specified string passed into this method.
	 * 
	 * @param price
	 */
	
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Get item's specifications
	 * 
	 * @return specs
	 */
	
	public String getSpecs() {
		return specs;
	}

	/**
	 * Changes the item's specifications to the specified string passed into this method.
	 * 
	 * @param specs
	 */
	
	public void setSpecs(String specs) {
		this.specs = specs;
	}

	/**
	 * Get status of item
	 * 
	 * @return status
	 */
	
	public String getStatus() {
		return status;
	}

	/**
	 * Changes the item's status to the specified string passed into this method.
	 * 
	 * @param status
	 */
	
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Get item's ID number
	 * 
	 * @return id
	 */
	
	public String getItemId() {
		return id;
	}

	/**
	 * Get item's ID integer value
	 * 
	 * @return itemId
	 */
	
	public static int getItemIdInt() {
		return itemId;
	}

	/**
	 * Changes the item's ID integer value to the specified integer passed into this method.
	 * 
	 * @param itemId
	 */
	
	public static void setItemId(int itemId) {
		InvItem.itemId = itemId;
	}

	/**
	 * Get condition of item
	 * 
	 * @return condition
	 */
	
	public String getCondition() {
		return condition;
	}

	/**
	 * Changes the item's condition to the specified string passed into this method.
	 * 
	 * @param condition
	 */
	
	public void setCondition(String condition) {
		this.condition = condition;
	}

	/**
	 * Get weight of item
	 * 
	 * @return weight
	 */
	
	public double getWeight() {
		return weight;
	}

	/**
	 * Changes the item's weight to the specified double value passed into this method.
	 * 
	 * @param weight
	 */
	
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * Show item's description as a toString() method.
	 */
	
	@Override
	public String toString() {
		return description;
	}
}
