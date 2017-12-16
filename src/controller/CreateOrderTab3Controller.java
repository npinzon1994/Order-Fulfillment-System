package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.Address;
import model.InvItem;
import model.MasterDatabase;
import model.ShippingCost;
import model.Validation;

public class CreateOrderTab3Controller implements Initializable {

	@FXML
	private Hyperlink logoutLink;

	@FXML
	private Label employee;

	@FXML
	private Label empId;

	@FXML
	private Label customer;

	@FXML
	private Label customerLabel;

	@FXML
	private ComboBox<String> shippingMethodBox;

	@FXML
	private TextField shipStreetField;

	@FXML
	private TextField shipCityField;

	@FXML
	private ComboBox<String> shipStateBox;

	@FXML
	private TextField shipZipField;

	@FXML
	private TextField billStreetField;

	@FXML
	private TextField billCityField;

	@FXML
	private ComboBox<String> billStateBox;

	@FXML
	private TextField billZipField;

	@FXML
	private Button nextBtn;

	@FXML
	private Button cancelBtn;

	@FXML
	private Button previousBtn;

	@FXML
	private RadioButton enterNewAddress;

	@FXML
	private RadioButton checkBox;

	@FXML
	private RadioButton billShipBox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeComboBoxes();
		setUserLabels();
		setBillingFields();
		setShippingFields();
		Validation.limitInputToZipField(shipZipField);
		Validation.limitInputToZipField(billZipField);
		ToggleGroup group = new ToggleGroup();
		checkBox.setToggleGroup(group);
		billShipBox.setToggleGroup(group);
		enterNewAddress.setToggleGroup(group);
		billShipBox.setSelected(true);
		fillFieldsWhenBillShippedBoxIsChecked();
		shippingMethodBox.getSelectionModel().selectFirst();
		for(InvItem item : MasterDatabase.getOrderBeingViewed().getItems()){
			System.out.println(item.getDescription());
		}

	}

	public void initializeComboBoxes() {
		shippingMethodBox.getItems().addAll("Standard Ground", "Second Day Express", "Next Day Air");
		shipStateBox.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL",
				"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
				"NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA",
				"WV", "WI", "WY");
		billStateBox.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL",
				"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
				"NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA",
				"WV", "WI", "WY");
	}

	public void setUserLabels() {
		customerLabel.setText(MasterDatabase.getOrderBeingViewed().getCustomer().getFirstName() + " "
				+ MasterDatabase.getOrderBeingViewed().getCustomer().getLastName());
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());

	}

	public void setBillingFields() {
		billStreetField.setText(MasterDatabase.getOrderBeingViewed().getCustomer().getBillingAddress().getStreetAddress());
		billCityField.setText(MasterDatabase.getOrderBeingViewed().getCustomer().getBillingAddress().getCity());
		billStateBox.getSelectionModel().select(MasterDatabase.getOrderBeingViewed().getCustomer().getBillingAddress().getState());
		billZipField.setText(MasterDatabase.getOrderBeingViewed().getCustomer().getBillingAddress().getZip());
	}

	public void setShippingFields() {
		shipStreetField.setText(MasterDatabase.getOrderBeingViewed().getCustomer().getShippingAddress().getStreetAddress());
		shipCityField.setText(MasterDatabase.getOrderBeingViewed().getCustomer().getShippingAddress().getCity());
		shipStateBox.getSelectionModel().select(MasterDatabase.getOrderBeingViewed().getCustomer().getShippingAddress().getState());
		shipZipField.setText(MasterDatabase.getOrderBeingViewed().getCustomer().getShippingAddress().getZip());
	}

	public void fillFieldsWhenPickupCheckboxIsChecked() {
		Address address = new Address();
		address.setStreetAddress("2111 Lakeland Ave");
		address.setCity("Ronkonkoma");
		address.setState("NY");
		address.setZip("11779");

		Address address2 = new Address();
		address2.setStreetAddress(shipStreetField.getText());
		address2.setCity(shipCityField.getText());
		address2.setState(shipStateBox.getValue());
		address2.setZip(shipZipField.getText());

		MasterDatabase.getOrderBeingViewed().setBillingAddress(address2);
		MasterDatabase.getOrderBeingViewed().setShippingAddress(address);
		shipStreetField.setText("2111 Lakeland Ave");
		shipCityField.setText("Ronkonkoma");
		shipStateBox.getSelectionModel().select("NY");
		shipZipField.setText("11779");
		shippingMethodBox.setValue("Pickup In-Store");
		shipStreetField.setEditable(false);
		shipCityField.setEditable(false);
		shipStateBox.setDisable(true);
		shipZipField.setEditable(false);
		shippingMethodBox.setDisable(true);
		billStreetField.setEditable(false);
		billCityField.setEditable(false);
		billStateBox.setDisable(true);
		billZipField.setEditable(false);
		MasterDatabase.getOrderBeingViewed().setShippingMethod("Pickup In-Store");
	}

	public void fillFieldsWhenBillShippedBoxIsChecked() {
		Address address1 = new Address();
		address1.setStreetAddress(billStreetField.getText());
		address1.setCity(billCityField.getText());
		address1.setState(billStateBox.getValue());
		address1.setZip(billZipField.getText());

		Address address2 = new Address();
		address2.setStreetAddress(shipStreetField.getText());
		address2.setCity(shipCityField.getText());
		address2.setState(shipStateBox.getValue());
		address2.setZip(shipZipField.getText());

		MasterDatabase.getOrderBeingViewed().setBillingAddress(address1);
		MasterDatabase.getOrderBeingViewed().setShippingAddress(address2);

		shipStreetField.setText(billStreetField.getText());
		shipCityField.setText(billCityField.getText());
		shipStateBox.getSelectionModel().select(billStateBox.getValue());
		shipZipField.setText(billZipField.getText());
		shipStreetField.setEditable(false);
		shipCityField.setEditable(false);
		shipStateBox.setDisable(true);
		shipZipField.setEditable(false);
		billStreetField.setEditable(false);
		billCityField.setEditable(false);
		billStateBox.setDisable(true);
		billZipField.setEditable(false);
		shippingMethodBox.setDisable(false);
		shippingMethodBox.getSelectionModel().selectFirst();
		MasterDatabase.getOrderBeingViewed().setShippingMethod(shippingMethodBox.getValue());
	}
	
	public void setTotals() {
		double subtotal = 0;
		double total = 0;
		for (InvItem item : MasterDatabase.getOrderBeingViewed().getItems()) {
			subtotal += item.getPrice();
		}
		total = subtotal + determineShippingCost();
		MasterDatabase.getOrderBeingViewed().setSubtotal(subtotal);
		MasterDatabase.getOrderBeingViewed().setShippingCost(determineShippingCost());
		MasterDatabase.getOrderBeingViewed().setTotal(total);
	}

	public double determineShippingCost() {
		double shippingCost = 0;
		if (MasterDatabase.getOrderBeingViewed().getShippingMethod().equals("Pickup In-Store")) {
			shippingCost = 0;
			return shippingCost;
		} else {
			shippingCost = ShippingCost.calculate(MasterDatabase.getOrderBeingViewed().getShippingAddress().getState(),
					MasterDatabase.getOrderBeingViewed().getShippingMethod(), getWeightOfOrder());
		}
		return shippingCost;
	}
	
	public double getWeightOfOrder(){
		double weight = 0;
		for(InvItem item : MasterDatabase.getOrderBeingViewed().getItems()){
			weight += item.getWeight();
		}
		return weight;
	}

	public void setAllFieldsEditable() {

		shipStreetField.setEditable(true);
		shipCityField.setEditable(true);
		shipStateBox.setDisable(false);
		shipZipField.setEditable(true);
		billStreetField.setEditable(true);
		billCityField.setEditable(true);
		billStateBox.setDisable(false);
		billZipField.setEditable(true);
		shippingMethodBox.setDisable(false);
		shippingMethodBox.getSelectionModel().selectFirst();
		MasterDatabase.getOrderBeingViewed().setShippingMethod(shippingMethodBox.getValue());
	}

	public void goToNextPage(ActionEvent event) {
		if (enterNewAddress.isSelected() && ((!billStreetField.getText()
				.equals(MasterDatabase.getOrderBeingViewed().getCustomer().getBillingAddress().getStreetAddress())
				|| !billCityField.getText().equals(MasterDatabase.getOrderBeingViewed().getCustomer().getBillingAddress().getCity())
				|| !billStateBox.getValue().equals(MasterDatabase.getOrderBeingViewed().getCustomer().getBillingAddress().getState())
				|| !billZipField.getText().equals(MasterDatabase.getOrderBeingViewed().getCustomer().getBillingAddress().getZip())
				|| !shipStreetField.getText()
						.equals(MasterDatabase.getOrderBeingViewed().getCustomer().getShippingAddress().getStreetAddress())
				|| !shipCityField.getText().equals(MasterDatabase.getOrderBeingViewed().getCustomer().getShippingAddress().getCity())
				|| !shipStateBox.getValue().equals(MasterDatabase.getOrderBeingViewed().getCustomer().getShippingAddress().getState())
				|| !shipZipField.getText().equals(MasterDatabase.getOrderBeingViewed().getCustomer().getShippingAddress().getZip())))) {
			promptUserToChangeInfo(event);
		} else {
			MasterDatabase.getOrderBeingViewed().setShippingMethod(shippingMethodBox.getValue());
			double weight = 0;
			for (InvItem item : MasterDatabase.getOrderBeingViewed().getItems()) {
				weight += item.getWeight();
			}
			MasterDatabase.getOrderBeingViewed().setShippingCost(
					ShippingCost.calculate(MasterDatabase.getOrderBeingViewed().getCustomer().getShippingAddress().getState(),
							MasterDatabase.getOrderBeingViewed().getShippingMethod(), weight));
			setTotals();
			switchToOrderPane4(event);
		}
	}

	public void cancelOrder(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Cancel order?");
		alert.setContentText("All unsaved progress will be lost");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			MasterDatabase.getOrderCustomer().getCart().clear();
			MasterDatabase.getOrderBeingViewed().getItems().clear();
			if (MasterDatabase.getLoggedEmployee().getStoreLevel() == 3) {
				switchToAdminTab(event);
			} else if (MasterDatabase.getLoggedEmployee().getStoreLevel() == 2) {
				switchToOperationsTab(event);
			} else if (MasterDatabase.getLoggedEmployee().getStoreLevel() == 1) {
				switchToCustomerServiceRepTab(event);
			}
		} else {
			alert.close();
		}

	}

	public void switchToAdminTab(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/HomePageAdmin.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void switchToOperationsTab(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/HomePageOperations.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void switchToCustomerServiceRepTab(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/HomePageCustServiceRep.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void goToPreviousPage(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/CreateOrderTab2.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void promptUserToChangeInfo(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Change Address Information");
		alert.setContentText("Overwrite billing address for " + MasterDatabase.getOrderBeingViewed().getCustomer().getFirstName() + " "
				+ MasterDatabase.getOrderBeingViewed().getCustomer().getLastName() + "?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			setOrderCustomerBillingInfo();
			setOrderCustomerShippingInfo();
			setTotals();
			switchToOrderPane4(event);
		} else {
			alert.close();
		}
	}

	public void setOrderCustomerBillingInfo() {
		Address address1 = new Address();
		address1.setStreetAddress(billStreetField.getText());
		address1.setCity(billCityField.getText());
		address1.setState(billStateBox.getValue());
		address1.setZip(billZipField.getText());
		MasterDatabase.getOrderBeingViewed().setBillingAddress(address1);
		MasterDatabase.getOrderBeingViewed().setShippingMethod(shippingMethodBox.getValue());
		MasterDatabase.getOrderBeingViewed().getCustomer().getBillingAddress().setStreetAddress(billStreetField.getText());
		MasterDatabase.getOrderBeingViewed().getCustomer().getBillingAddress().setCity(billCityField.getText());
		MasterDatabase.getOrderBeingViewed().getCustomer().getBillingAddress().setState(billStateBox.getValue());
		MasterDatabase.getOrderBeingViewed().getCustomer().getBillingAddress().setZip(billZipField.getText());
	}

	public void setOrderCustomerShippingInfo() {
		Address address2 = new Address();
		address2.setStreetAddress(shipStreetField.getText());
		address2.setCity(shipCityField.getText());
		address2.setState(shipStateBox.getValue());
		address2.setZip(shipZipField.getText());
		MasterDatabase.getOrderBeingViewed().setShippingAddress(address2);
		MasterDatabase.getOrderBeingViewed().setShippingMethod(shippingMethodBox.getValue());
		MasterDatabase.getOrderBeingViewed().getCustomer().setShippingMethod(shippingMethodBox.getValue());
		MasterDatabase.getOrderBeingViewed().getCustomer().getShippingAddress().setStreetAddress(shipStreetField.getText());
		MasterDatabase.getOrderBeingViewed().getCustomer().getShippingAddress().setCity(shipCityField.getText());
		MasterDatabase.getOrderBeingViewed().getCustomer().getShippingAddress().setState(shipStateBox.getValue());
		MasterDatabase.getOrderBeingViewed().getCustomer().getShippingAddress().setZip(shipZipField.getText());
	}

	public void switchToOrderPane4(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/CreateOrderTab4.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void logout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Are you sure you want to logout?");
		alert.setContentText("All unsaved progress will be lost");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			MasterDatabase.getOrderCustomer().getCart().clear();
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Scene scene = new Scene(root);
			stage.setScene(scene);
		} else {
			alert.close();
		}

	}

}
