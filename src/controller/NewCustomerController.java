package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Address;
import model.Customer;
import model.MasterDatabase;
import model.Validation;

public class NewCustomerController implements Initializable {

	@FXML
	private TextField fNameField;

	@FXML
	private TextField lNameField;

	@FXML
	private TextField emailField;

	@FXML
	private TextField phoneField1;

	@FXML
	private TextField phoneField2;

	@FXML
	private TextField phoneField3;

	@FXML
	private TextField shippingStreetAddress;

	@FXML
	private TextField shippingCity;

	@FXML
	private ComboBox<String> shippingState;

	@FXML
	private TextField shippingZip;

	@FXML
	private CheckBox checkbox;

	@FXML
	private TextField billingStreetAddress;

	@FXML
	private TextField billingCity;

	@FXML
	private ComboBox<String> billingState;

	@FXML
	private TextField billingZip;

	@FXML
	private Button createBtn;

	@FXML
	private Button cancelBtn;

	@FXML
	private Hyperlink logoutLink;

	private Customer customer;

	private Address shippingAddress;
	private Address billingAddress;

	@FXML
	private Label employee;

	@FXML
	private Label empId;

	@FXML
	private Label emailErrorLbl;

	@FXML
	private Label emailExistsLbl;

	@FXML
	private Label star1;

	@FXML
	private Label star2;

	@FXML
	private Label star3;

	@FXML
	private Label star4;

	@FXML
	private ImageView emailX;

	@FXML
	private ImageView shipStateX;

	@FXML
	private ImageView billStateX;

	@FXML
	private ImageView shipZipX;

	@FXML
	private ImageView billZipX;

	@FXML
	private ImageView phoneX;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		bindFieldsToButton();
		initializeComboBoxes();
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		Validation.limitInputToPhoneField(phoneField1);
		Validation.limitInputToPhoneField(phoneField2);
		Validation.limitInputToPhoneField3(phoneField3);
		Validation.limitInputToZipField(shippingZip);
		Validation.limitInputToZipField(billingZip);
		shippingState.getSelectionModel().selectFirst();
		billingState.getSelectionModel().selectFirst();
	}

	public void bindFieldsToButton() {
		createBtn.disableProperty().bind(Bindings.isEmpty(fNameField.textProperty())
				.or(Bindings.isEmpty(lNameField.textProperty()).or(Bindings.isEmpty(emailField.textProperty())
						.or(Bindings.isEmpty(phoneField1.textProperty()).or(Bindings.isEmpty(phoneField2.textProperty())
								.or(Bindings.isEmpty(phoneField3.textProperty())
										.or(Bindings.isEmpty(shippingStreetAddress.textProperty())
												.or(Bindings.isEmpty(shippingCity.textProperty())
														.or(Bindings.isEmpty(shippingZip.textProperty()))))))))));
	}

	public void initializeComboBoxes() {
		billingState.getItems().addAll("State" , "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL",
				"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
				"NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA",
				"WV", "WI", "WY");
		shippingState.getItems().addAll("State", "AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL",
				"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
				"NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA",
				"WV", "WI", "WY");
	}

	public void checkboxClicked() {
		if (checkbox.isSelected()) {
			billingStreetAddress.setEditable(false);
			billingCity.setEditable(false);
			billingState.setDisable(true);
			billingZip.setEditable(false);
			billingStreetAddress.clear();
			billingCity.clear();
			billingZip.clear();
			billingState.getSelectionModel().select("State");
		} else {
			billingStreetAddress.setEditable(true);
			billingCity.setEditable(true);
			billingState.setDisable(false);
			billingZip.setEditable(true);
		}

	}

	public void logout(ActionEvent event) {
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
	}

	public void addCustomerToDatabase(ActionEvent event) {
		if ((Validation.isValidEmail(emailX, emailField.getText()))
				&& (Validation.isValidPhone(phoneX, phoneField1.getText(), phoneField2.getText(), phoneField3.getText())
						&& (Validation.isValidZipCode(shipZipX, shippingZip.getText())
								&& Validation.isValidState(shipStateX, shippingState.getValue()) && (Validation.isValidZipCode(billZipX, billingZip.getText())
										&& Validation.isValidState(billStateX, billingState.getValue()))))) {
			if (!emailExistsInDatabase()) {
				customer = new Customer();
				initializeAddresses();
				initializeCustomer();
				MasterDatabase.saveCustomers();
				System.out.println("Customer added!");
				clearFields();
				System.out.println(customer.getFirstName() + " " + customer.getLastName() + " added to system!");
				createAlert();
				goToNextPage(event);
			}

		}
	}

	public boolean emailExistsInDatabase() {
		for (Customer customer : MasterDatabase.getCustomerDatabase().values()) {
			if (customer.getEmail().equals(emailField.getText())) {
				emailX.setVisible(true);
				return true;
			}
		}
		return false;
	}

	public void initializeCustomer() {
		customer.setShippingAddress(shippingAddress);
		customer.setBillingAddress(billingAddress);
		customer.setFirstName(fNameField.getText());
		customer.setLastName(lNameField.getText());
		customer.setEmail(emailField.getText());
		customer.setPhone(phoneField1.getText() + phoneField2.getText() + phoneField3.getText());
		MasterDatabase.getCustomerDatabase().put(customer.getId(), customer);
	}

	public void goToNextPage(ActionEvent event) {
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

	public void initializeAddresses() {
		shippingAddress = new Address();
		shippingAddress.setStreetAddress(shippingStreetAddress.getText());
		shippingAddress.setState(shippingState.getValue());
		shippingAddress.setCity(shippingCity.getText());
		shippingAddress.setZip(shippingZip.getText());
		if (checkbox.isSelected()) {
			billingAddress = new Address(shippingAddress);
		} else {
			billingAddress = new Address();
			billingAddress.setStreetAddress(billingStreetAddress.getText());
			billingAddress.setCity(billingCity.getText());
			billingAddress.setState(billingState.getValue());
			billingAddress.setZip(billingZip.getText());
		}
	}

	public void clearFields() {
		fNameField.clear();
		lNameField.clear();
		emailField.clear();
		phoneField1.clear();
		phoneField2.clear();
		phoneField3.clear();
		shippingStreetAddress.clear();
		shippingCity.clear();
		shippingState.getSelectionModel().selectFirst();;
		shippingZip.clear();
		checkbox.setSelected(false);
		billingStreetAddress.clear();
		billingCity.clear();
		billingState.getSelectionModel().selectFirst();
		billingZip.clear();
	}

	public void createAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(customer.getFirstName() + " " + customer.getLastName() + " has been added to the system!");
		alert.showAndWait();
	}

}
