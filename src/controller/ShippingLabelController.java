package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Customer;
import model.MasterDatabase;
import model.ShippingLabel;

public class ShippingLabelController implements Initializable {

	@FXML
	private Label customerName;
	@FXML
	private Label shippingMethodLeft;
	@FXML
	private Label shippingAddressLeft;
	@FXML
	private Label cityStateZipLeft;
	@FXML
	private Label trackingNumberLabelLeft;
	@FXML
	private Label companyName;
	@FXML
	private Label streetAddressRight;
	@FXML
	private Label cityStateZipRight;
	@FXML
	private Label weight;
	@FXML
	private Label shipToName;
	@FXML
	private Label shipToAddress;
	@FXML
	private Label shipToCityStateZip;
	@FXML
	private Label state;
	@FXML
	private Label zip;
	@FXML
	private Label shippingMethodRight;
	@FXML
	private Label trackingNumberLabelRight;
	@FXML
	private Label remarkLabel1;
	@FXML
	private Label remarkLabel2;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Customer customer = MasterDatabase.getOrderBeingViewed().getCustomer();
		ShippingLabel shippingLabel = MasterDatabase.getCurrentShippingLabel();
		customerName.setText(customer.getFirstName() + " " + customer.getLastName());
		shippingMethodLeft.setText(shippingLabel.getShippingMethod());
		shippingAddressLeft.setText(shippingLabel.getCustomerAddress().getStreetAddress());
		cityStateZipLeft.setText(shippingLabel.getCustomerAddress().getCity() + " "
				+ shippingLabel.getCustomerAddress().getState() + ", " + shippingLabel.getCustomerAddress().getZip());
		trackingNumberLabelLeft.setText(shippingLabel.getTrackingNumber());
		companyName.setText("Habitat For Humanity Restore");
		streetAddressRight.setText(shippingLabel.getStoreAddress().getStreetAddress());
		cityStateZipRight.setText(shippingLabel.getStoreAddress().getCity() + " "
				+ shippingLabel.getStoreAddress().getState() + ", " + shippingLabel.getStoreAddress().getZip());
		weight.setText(shippingLabel.getWeight());
		shipToName.setText(customer.getFirstName() + " " + customer.getLastName());
		shipToAddress.setText(shippingLabel.getCustomerAddress().getStreetAddress());
		shipToCityStateZip.setText(shippingLabel.getCustomerAddress().getCity() + " "
				+ shippingLabel.getCustomerAddress().getState() + ", " + shippingLabel.getCustomerAddress().getZip());
		state.setText(customer.getShippingAddress().getState());
		shippingMethodRight.setText(shippingLabel.getShippingMethod());
		trackingNumberLabelRight.setText(shippingLabel.getTrackingNumber());
		remarkLabel1.setText(shippingLabel.getReferenceField1());
		remarkLabel2.setText(shippingLabel.getReferenceField2());
	}

}
