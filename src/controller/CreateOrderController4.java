package controller;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.InvItem;
import model.MasterDatabase;
import model.ShippingCost;
import model.Validation;

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
	private TextField numField4;

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

	@FXML
	private ImageView creditX;

	@FXML
	private ImageView cvvX;

	@FXML
	private Label cvvLbl;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		creditBox.getItems().addAll("Visa", "Master Card", "American Express", "Discover");
		monthBox.getItems().addAll("01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12");
		yearBox.getItems().addAll("2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024");
		monthBox.getSelectionModel().selectFirst();
		yearBox.getSelectionModel().select(1);
		creditBox.getSelectionModel().selectFirst();
		NumberFormat format = NumberFormat.getCurrencyInstance();

		
		subtotalLabel.setText(format.format(MasterDatabase.getOrderBeingViewed().getSubtotal()));
		shippingLabel.setText(format.format(MasterDatabase.getOrderBeingViewed().getShippingCost()));
		System.out.println("Shipping Cost" + MasterDatabase.getOrderBeingViewed().getShippingCost());
		totalLabel.setText(format.format(MasterDatabase.getOrderBeingViewed().getTotal()));
		limitTextFieldInputs();
		for(InvItem item : MasterDatabase.getOrderBeingViewed().getItems()){
			System.out.println(item.getDescription());
		}
	}
	

	public void limitTextFieldInputs() {
		Validation.limitInputToCreditCardField(numField1);
		Validation.limitInputToCreditCardField(numField2);
		Validation.limitInputToCreditCardField(numField3);
		Validation.limitInputToCreditCardField(numField4);
		Validation.limitInputToCreditCardField(cvvField);
	}

	public void limitCvvInput() {
		if (creditBox.getSelectionModel().getSelectedItem().equals("American Express")) {
			cvvLbl.setText("AMEX Id");
		} else {
			cvvLbl.setText("CVV");
		}
	}

	public void logout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Are you sure you want to logout?");
		alert.setContentText("All unsaved progress will be lost");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK){
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

	public void goToPreviousPage(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/CreateOrderTab3.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void switchToOrderSummaryTab(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/OrderSummaryTab.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void goToNextPage(ActionEvent event) {
		
		if (Validation.isValidCreditCard(creditX, numField1.getText(), numField2.getText(), numField3.getText(),
				numField4.getText()) && Validation.isValidCvv(cvvX, creditBox, cvvField.getText())) {
			switchToOrderSummaryTab(event);
		}
	}

	public void cancelOrder(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Cancel order?");
		alert.setContentText("All unsaved progress will be lost");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK){
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

}
