package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InvItem;
import model.MasterDatabase;

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
	private CheckBox checkBox;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		shippingMethodBox.getItems().addAll("Standard Ground", "Second Day Express", "Next Day Air");
		shipStateBox.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL",
				"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
				"NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA",
				"WV", "WI", "WY");
		billStateBox.getItems().addAll("AL", "AK", "AZ", "AR", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "ID", "IL",
				"IN", "IA", "KS", "KY", "LA", "ME", "MD", "MA", "MI", "MN", "MS", "MO", "MT", "NE", "NV", "NH", "NJ",
				"NM", "NY", "NC", "ND", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VT", "VA", "WA",
				"WV", "WI", "WY");
		customerLabel.setText(MasterDatabase.getOrderCustomer().getFirstName() + " "
				+ MasterDatabase.getOrderCustomer().getLastName());
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		shipStreetField.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getStreetAddress());
		shipCityField.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getCity());
		shipStateBox.getSelectionModel().select(MasterDatabase.getOrderCustomer().getShippingAddress().getState());
		shipZipField.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getZip());
		billStreetField.setText(MasterDatabase.getOrderCustomer().getBillingAddress().getStreetAddress());
		billCityField.setText(MasterDatabase.getOrderCustomer().getBillingAddress().getCity());
		billStateBox.getSelectionModel().select(MasterDatabase.getOrderCustomer().getBillingAddress().getState());
		billZipField.setText(MasterDatabase.getOrderCustomer().getBillingAddress().getZip());
	}

	public void boxChecked() {
		if (checkBox.isSelected()) {
			shipStreetField.setText("2111 Lakeland Ave");
			shipCityField.setText("Ronkonkoma");
			shipStateBox.getSelectionModel().select("NY");
			shipZipField.setText("11779");
			shipStreetField.setEditable(false);
			shipCityField.setEditable(false);
			shipStateBox.setDisable(true);
			shipZipField.setEditable(false);
			shippingMethodBox.getSelectionModel().select("Pickup In-Store");
			shippingMethodBox.setDisable(true);
		} else {
			shipStreetField.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getStreetAddress());
			shipCityField.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getCity());
			shipStateBox.getSelectionModel().select(MasterDatabase.getOrderCustomer().getShippingAddress().getState());
			shipZipField.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getZip());
			shipStreetField.setEditable(true);
			shipCityField.setEditable(true);
			shipStateBox.setDisable(false);
			shipZipField.setEditable(true);
			shippingMethodBox.setDisable(false);
		}
	}

	public void cancelOrder(ActionEvent event) {
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
		MasterDatabase.getOrderCustomer().setShippingMethod(shippingMethodBox.getValue());
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

}
