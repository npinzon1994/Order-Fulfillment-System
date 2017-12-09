package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InvItem;
import model.Invoice;
import model.MasterDatabase;
import model.ShippingCost;

public class ShipOrderController implements Initializable {

	@FXML
	private Label employee;
	@FXML
	private Label empId;
	@FXML
	private Label costLbl;
	@FXML
	private TextField fNameField;
	@FXML
	private TextField lNameField;
	@FXML
	private TextField phoneField1;
	@FXML
	private TextField phoneField2;
	@FXML
	private TextField phoneField3;
	@FXML
	private TextField streetAddressField;
	@FXML
	private TextField cityField;
	@FXML
	private ComboBox<String> stateBox;
	@FXML
	private TextField zipField;
	@FXML
	private TextField emailField;
	@FXML
	private ComboBox<String> shippingMethodBox;
	@FXML
	private TextField remarkField1;
	@FXML
	private TextField remarkField2;
	@FXML
	private TextField weightField;
	@FXML
	private Button checkZipBtn;
	@FXML
	private Button calculateBtn;
	@FXML
	private Button shipBtn;
	@FXML
	private Button backBtn;

	private double cost;

	Invoice invoice;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		invoice = MasterDatabase.getOrderBeingViewed();
		DecimalFormat format = new DecimalFormat("0.00");
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		stateBox.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL", "IN",
				"IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ", "NM",
				"NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA", "WV",
				"WI", "WY");
		shippingMethodBox.getItems().addAll("Standard Ground", "Second Day Express", "Next Day Air");
		fNameField.setText(invoice.getCustomer().getFirstName());
		lNameField.setText(invoice.getCustomer().getLastName());
		phoneField1.setText(invoice.getCustomer().getPhone().substring(0, 3));
		phoneField2.setText(invoice.getCustomer().getPhone().substring(3, 6));
		phoneField3.setText(invoice.getCustomer().getPhone().substring(6, 10));
		streetAddressField.setText(invoice.getCustomer().getShippingAddress().getStreetAddress());
		cityField.setText(invoice.getCustomer().getShippingAddress().getCity());
		stateBox.getSelectionModel().select(invoice.getCustomer().getShippingAddress().getState());
		zipField.setText(invoice.getCustomer().getShippingAddress().getZip());
		shippingMethodBox.getSelectionModel().select(invoice.getCustomer().getShippingMethod());
		emailField.setText(invoice.getCustomer().getEmail());
		costLbl.setText(format.format(cost));
		double weight = 0;
		for(InvItem item : invoice.getItems()){
			weight += item.getWeight();
		}
		weightField.setText(String.valueOf(weight));
		calculateShipping();
		bindFieldsToButton();
	}

	public void bindFieldsToButton() {
		shipBtn.disableProperty().bind(Bindings.isEmpty(fNameField.textProperty()).or(Bindings
				.isEmpty(lNameField.textProperty())
				.or(Bindings.isEmpty(phoneField1.textProperty()).or(Bindings.isEmpty(phoneField2.textProperty())
						.or(Bindings.isEmpty(phoneField3.textProperty()).or(Bindings
								.isEmpty(streetAddressField.textProperty())
								.or(Bindings.isEmpty(cityField.textProperty())
										.or(Bindings.isEmpty(zipField.textProperty())
												.or(Bindings.isEmpty(emailField.textProperty())
														.or(Bindings.isEmpty(weightField.textProperty())))))))))));
	}

	public void logout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Cancel order?");
		alert.setContentText("All unsaved progress will be lost");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
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

	public void goBack(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/PackingQueue.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void calculateShipping() {
		DecimalFormat format = new DecimalFormat("0.00");
		double weight = Double.parseDouble(weightField.getText());
		double shippingCost = ShippingCost.calculate(stateBox.getValue(), shippingMethodBox.getValue(), weight);
		costLbl.setText(format.format(shippingCost));
	}

	public void setFields() {
		invoice.getCustomer().setFirstName(fNameField.getText());
		invoice.getCustomer().setLastName(lNameField.getText());
		invoice.getCustomer().setEmail(emailField.getText());
		invoice.getCustomer().setPhone(phoneField1.getText() + phoneField2.getText() + phoneField3.getText());
		invoice.getCustomer().getShippingAddress().setStreetAddress(streetAddressField.getText());
		invoice.getCustomer().getShippingAddress().setCity(cityField.getText());
		invoice.getCustomer().getShippingAddress().setState(stateBox.getValue());
		invoice.getCustomer().getShippingAddress().setZip(zipField.getText());
		invoice.setShippingMethod(shippingMethodBox.getValue());
		if (!remarkField1.equals(null)) {
			invoice.setRemark1(remarkField1.getText());
		}
		if (!remarkField2.equals(null)) {
			invoice.setRemark2(remarkField2.getText());
		}
		invoice.setshippingCost(Double.parseDouble(costLbl.getText()));
		invoice.setOrderStatus("Shipped");
	}

	public void ship(ActionEvent event) {
		setFields();
		for (Invoice invoice : MasterDatabase.getInvoiceDatabase().values()) {
			if (invoice.getInvoiceNumber().equals(this.invoice.getInvoiceNumber())) {
				invoice.getCustomer().setFirstName(fNameField.getText());
				invoice.getCustomer().setLastName(lNameField.getText());
				invoice.getCustomer().setEmail(emailField.getText());
				invoice.getCustomer().setPhone(phoneField1.getText() + phoneField2.getText() + phoneField3.getText());
				invoice.getCustomer().getShippingAddress().setStreetAddress(streetAddressField.getText());
				invoice.getCustomer().getShippingAddress().setCity(cityField.getText());
				invoice.getCustomer().getShippingAddress().setState(stateBox.getValue());
				invoice.getCustomer().getShippingAddress().setZip(zipField.getText());
				invoice.setShippingMethod(shippingMethodBox.getValue());
				if (!remarkField1.equals(null)) {
					invoice.setRemark1(remarkField1.getText());
				}
				if (!remarkField2.equals(null)) {
					invoice.setRemark2(remarkField2.getText());
				}
				invoice.setshippingCost(Double.parseDouble(costLbl.getText()));
				invoice.setOrderStatus("Shipped");
			}

		}
		clearFields();
		MasterDatabase.saveCustomers();
		MasterDatabase.saveInventory();
		MasterDatabase.saveInvoices();
		switchBackToPackingQueue(event);

	}

	public void switchBackToPackingQueue(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/PackingQueue.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void clearFields() {
		fNameField.clear();
		lNameField.clear();
		emailField.clear();
		phoneField1.clear();
		phoneField2.clear();
		phoneField3.clear();
		streetAddressField.clear();
		cityField.clear();
		stateBox.setPromptText("State");
		zipField.clear();
		shippingMethodBox.getSelectionModel().selectFirst();
		remarkField1.clear();
		remarkField2.clear();
		weightField.clear();
		costLbl.setText("0.00");
	}

}
