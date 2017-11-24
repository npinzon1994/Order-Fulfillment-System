package controller;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.InvItem;
import model.MasterDatabase;

public class CreateOrderController4 implements Initializable {

	@FXML
	private Label employee;

	@FXML
	private Label empId;

	@FXML
	private Hyperlink logoutLink;

	@FXML
	private Label subtotalLabel;

	@FXML
	private Label shippingLabel;

	@FXML
	private Label totalLabel;

	@FXML
	private ComboBox<String> creditBox;

	@FXML
	private TextField numField1;

	@FXML
	private TextField numField2;

	@FXML
	private TextField numField3;

	@FXML
	private ComboBox<String> monthBox;

	@FXML
	private ComboBox<String> yearBox;

	@FXML
	private TextField cvvField;

	@FXML
	private Button previousBtn;

	@FXML
	private Button continueBtn;

	@FXML
	private Button cancelBtn;
	
	private double subtotal;
	private double total;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		creditBox.getItems().addAll("Visa", "Master Card", "American Express", "Discover");
		monthBox.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
		yearBox.getItems().addAll("2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024");
		NumberFormat format = NumberFormat.getCurrencyInstance();
		
		setTotals();
		subtotalLabel.setText(format.format(MasterDatabase.getOrderCustomer().getSubtotal()));
		shippingLabel.setText(format.format(MasterDatabase.getOrderCustomer().getShippingCost()));
		totalLabel.setText(format.format(MasterDatabase.getOrderCustomer().getTotal()));

	}

	public void setTotals() {
		subtotal = 0;
		total = 0;
		for (InvItem item : MasterDatabase.getOrderCustomer().getCart()) {
			subtotal += item.getPrice();
		}
		total = subtotal + determineShippingCost();
		MasterDatabase.getOrderCustomer().setSubtotal(subtotal);
		MasterDatabase.getOrderCustomer().setShippingCost(determineShippingCost());
		MasterDatabase.getOrderCustomer().setTotal(total);
	}

	public double determineShippingCost() {
		double shippingCost = 0;
		if (MasterDatabase.getOrderCustomer().getShippingMethod().equals("Pickup In-Store")) {
			shippingCost = 0;
			return shippingCost;
		} else {

		}
		return shippingCost;
	}

	public void logout(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
		} catch (IOException e) {
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void goToPreviousPage(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/CreateOrderTab3.fxml"));
		} catch (IOException e) {
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void goToNextPage(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/OrderSummaryTab.fxml"));
		} catch (IOException e) {
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void cancelOrder(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/HomePageAdmin.fxml"));
		} catch (IOException e) {
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

}
