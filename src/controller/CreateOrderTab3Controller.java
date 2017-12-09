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
import model.MasterDatabase;
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
		customerLabel.setText(MasterDatabase.getOrderCustomer().getFirstName() + " "
				+ MasterDatabase.getOrderCustomer().getLastName());
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());

	}

	public void setBillingFields() {
		billStreetField.setText(MasterDatabase.getOrderCustomer().getBillingAddress().getStreetAddress());
		billCityField.setText(MasterDatabase.getOrderCustomer().getBillingAddress().getCity());
		billStateBox.getSelectionModel().select(MasterDatabase.getOrderCustomer().getBillingAddress().getState());
		billZipField.setText(MasterDatabase.getOrderCustomer().getBillingAddress().getZip());
	}

	public void setShippingFields() {
		shipStreetField.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getStreetAddress());
		shipCityField.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getCity());
		shipStateBox.getSelectionModel().select(MasterDatabase.getOrderCustomer().getShippingAddress().getState());
		shipZipField.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getZip());
		shippingMethodBox.getSelectionModel().selectFirst();
	}

	public boolean boxChecked() {
		if (checkBox.isSelected()) {
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
			return true;
		} else if (!checkBox.isSelected()) {
			shipStreetField.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getStreetAddress());
			shipCityField.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getCity());
			shipStateBox.getSelectionModel().select(MasterDatabase.getOrderCustomer().getShippingAddress().getState());
			shipZipField.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getZip());
			shipStreetField.setEditable(true);
			shipCityField.setEditable(true);
			shipStateBox.setDisable(false);
			shipZipField.setEditable(true);
			shippingMethodBox.setDisable(false);
			return false;
		}
		return true;
	}

	public boolean billShippedBoxChecked() {
		if (billShipBox.isSelected()) {
			shipStreetField.setText(billStreetField.getText());
			shipCityField.setText(billCityField.getText());
			shipStateBox.getSelectionModel().select(billStateBox.getValue());
			shipZipField.setText(billZipField.getText());
			shipStreetField.setEditable(false);
			shipCityField.setEditable(false);
			shipStateBox.setDisable(true);
			shipZipField.setEditable(false);
			shippingMethodBox.getSelectionModel().selectFirst();
			shippingMethodBox.setDisable(false);
			return true;
		} else if (!billShipBox.isSelected()) {
			shipStreetField.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getStreetAddress());
			shipCityField.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getCity());
			shipStateBox.getSelectionModel().select(MasterDatabase.getOrderCustomer().getShippingAddress().getState());
			shipZipField.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getZip());
			shipStreetField.setEditable(true);
			shipCityField.setEditable(true);
			shipStateBox.setDisable(false);
			shipZipField.setEditable(true);
			shippingMethodBox.setDisable(false);

			return false;
		}
		return true;
	}

	public boolean enterNewBillingAddressButtonSelected() {
		if (enterNewAddress.isSelected()) {
			shipStreetField.setEditable(true);
			shipCityField.setEditable(true);
			shipStateBox.setDisable(false);
			shipZipField.setEditable(true);
			billStreetField.setEditable(true);
			billCityField.setEditable(true);
			billStateBox.setDisable(false);
			billZipField.setEditable(true);

			return true;
		} else if (!enterNewAddress.isSelected()) {
			return false;
		}
		return true;
	}

	public void cancelOrder(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Cancel order?");
		alert.setContentText("All unsaved progress will be lost");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			MasterDatabase.getOrderCustomer().getCart().clear();
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

	public void goToNextPage(ActionEvent event) {
		if ((boxChecked() || billShippedBoxChecked() || enterNewBillingAddressButtonSelected()) && ((!billStreetField
				.getText().equals(MasterDatabase.getOrderCustomer().getBillingAddress().getStreetAddress())
				|| !billCityField.getText().equals(MasterDatabase.getOrderCustomer().getBillingAddress().getCity())
				|| !billStateBox.getValue().equals(MasterDatabase.getOrderCustomer().getBillingAddress().getState())
				|| !billZipField.getText().equals(MasterDatabase.getOrderCustomer().getBillingAddress().getZip())))) {
			promptUserToChangeInfo(event);
		} else {
			setOrderCustomerFields();
			switchToOrderPane4(event);
		}
	}

	public void promptUserToChangeInfo(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Change Address Information");
		alert.setContentText("Overwrite billing address for " + MasterDatabase.getOrderCustomer().getFirstName() + " "
				+ MasterDatabase.getOrderCustomer().getLastName() + "?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			setOrderCustomerFields();
			switchToOrderPane4(event);
		} else {
			alert.close();
		}
	}

	public void setOrderCustomerFields() {
		MasterDatabase.getOrderCustomer().setShippingMethod(shippingMethodBox.getValue());
		MasterDatabase.getOrderCustomer().getBillingAddress().setStreetAddress(billStreetField.getText());
		MasterDatabase.getOrderCustomer().getBillingAddress().setCity(billCityField.getText());
		MasterDatabase.getOrderCustomer().getBillingAddress().setState(billStateBox.getValue());
		MasterDatabase.getOrderCustomer().getBillingAddress().setZip(billZipField.getText());
		MasterDatabase.getOrderCustomer().getShippingAddress().setStreetAddress(shipStreetField.getText());
		MasterDatabase.getOrderCustomer().getShippingAddress().setCity(shipCityField.getText());
		MasterDatabase.getOrderCustomer().getShippingAddress().setState(shipStateBox.getValue());
		MasterDatabase.getOrderCustomer().getShippingAddress().setZip(shipZipField.getText());
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
