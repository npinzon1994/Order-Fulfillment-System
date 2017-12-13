package model;

public class ShippingLabel {

	private static int trackingNumberInt = 10000000;

	private String trackingNumber;
	private Address storeAddress;
	private Address customerAddress;
	private String weight;
	private String referenceField1;
	private String referenceField2;
	private String shippingMethod;

	public ShippingLabel(String trackingNumber, Address storeAddress, Address customerAddress, String weight,
			String referenceField1, String referenceField2, String shippingMethod) {
		this.trackingNumber = String.valueOf("1Z589Y4Y03" + trackingNumberInt++);
		this.storeAddress = storeAddress;
		this.customerAddress = customerAddress;
		this.weight = weight;
		this.referenceField1 = referenceField1;
		this.referenceField2 = referenceField2;
		this.shippingMethod = shippingMethod;
	}

	public ShippingLabel() {
		this.trackingNumber = String.valueOf("1Z589Y4Y03" + trackingNumberInt++);
	}

	public static int getTrackingNumberInt() {
		return trackingNumberInt;
	}

	public static void setTrackingNumberInt(int trackingNumberInt) {
		ShippingLabel.trackingNumberInt = trackingNumberInt;
	}

	public String getTrackingNumber() {
		return trackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		this.trackingNumber = trackingNumber;
	}

	public Address getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(Address storeAddress) {
		this.storeAddress = storeAddress;
	}

	public Address getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(Address customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getReferenceField1() {
		return referenceField1;
	}

	public void setReferenceField1(String referenceField1) {
		this.referenceField1 = referenceField1;
	}

	public String getReferenceField2() {
		return referenceField2;
	}

	public void setReferenceField2(String referenceField2) {
		this.referenceField2 = referenceField2;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public void setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
	}

}
