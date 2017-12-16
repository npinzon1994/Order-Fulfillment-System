package controller;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import model.InvItem;
import model.Invoice;
import model.MasterDatabase;

public class OrderSummaryController implements Initializable {

	@FXML
	private Hyperlink logoutLink;

	@FXML
	private Label employee;

	@FXML
	private Label empId;

	@FXML
	private Label customerLabel;

	@FXML
	private Label shippingMethodLabel;

	@FXML
	private Label shipStreetAddress;

	@FXML
	private Label shipCityStateZip;

	@FXML
	private Label billStreetAddress;

	@FXML
	private Label billCityStateZip;

	@FXML
	private Button cancelBtn;

	@FXML
	private Button previousBtn;

	@FXML
	private Button placeOrderBtn;

	@FXML
	private TableView<InvItem> table;

	@FXML
	private TableColumn<InvItem, String> itemColumn;

	@FXML
	private TableColumn<InvItem, String> priceColumn;

	@FXML
	private Label totalLabel;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		customerLabel.setText(MasterDatabase.getOrderBeingViewed().getCustomer().getFirstName() + " "
				+ MasterDatabase.getOrderCustomer().getLastName());
		shipStreetAddress.setText(MasterDatabase.getOrderBeingViewed().getShippingAddress().getStreetAddress());
		shipCityStateZip.setText(MasterDatabase.getOrderBeingViewed().getShippingAddress().getCity() + " "
				+ MasterDatabase.getOrderBeingViewed().getShippingAddress().getState() + ", "
				+ MasterDatabase.getOrderBeingViewed().getShippingAddress().getZip());
		billStreetAddress.setText(MasterDatabase.getOrderBeingViewed().getBillingAddress().getStreetAddress());
		billCityStateZip.setText(MasterDatabase.getOrderBeingViewed().getBillingAddress().getCity() + " "
				+ MasterDatabase.getOrderBeingViewed().getBillingAddress().getState() + ", "
				+ MasterDatabase.getOrderBeingViewed().getBillingAddress().getZip());
		shippingMethodLabel.setText(MasterDatabase.getOrderBeingViewed().getShippingMethod());
		populateTable();
		NumberFormat format = NumberFormat.getCurrencyInstance();
		totalLabel.setText(format.format(MasterDatabase.getOrderBeingViewed().getTotal()));

	}

	public void populateTable() {
		ObservableList<InvItem> items = FXCollections.observableArrayList();
		for (InvItem item : MasterDatabase.getOrderBeingViewed().getItems()) {
			items.add(item);
		}
		itemColumn.setCellValueFactory(
				cellData -> Bindings.createStringBinding(() -> cellData.getValue().getDescription()));
		priceColumn.setCellValueFactory(new PropertyValueFactory<InvItem, String>("price"));
		table.setItems(items);
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
		MasterDatabase.getOrderCustomer().getCart().clear();
		MasterDatabase.getOrderBeingViewed().getItems().clear();
	}

	public void cancelOrder(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Cancel order?");
		alert.setContentText("All unsaved progress will be lost");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
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
		MasterDatabase.getOrderCustomer().getCart().clear();
		MasterDatabase.getOrderBeingViewed().getItems().clear();
	}

	public void goToPreviousPage(ActionEvent event) {
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

	public void createInvoice() {
		Invoice invoice = MasterDatabase.getOrderBeingViewed();
		invoice.setOrderStatus("Processed");
		MasterDatabase.getInvoiceDatabase().put(invoice.getInvoiceNumber(), invoice);
		for (Customer customer : MasterDatabase.getCustomerDatabase().values()) {
			if (customer.getId().equals(MasterDatabase.getOrderBeingViewed().getCustomer().getId())) {
				customer.getOrders().put(invoice.getInvoiceNumber(), invoice);
			}

		}
	}

	// when place order button is actually clicked
	public void placeOrder(ActionEvent event) {
		createInvoice();
		for (InvItem item : MasterDatabase.getOrderBeingViewed().getItems()) {
			if (MasterDatabase.getInventory().containsValue(item)) {
				item.setStatus("Out of Stock");
			}
		}
		MasterDatabase.getOrderCustomer().getCart().clear();
		MasterDatabase.getOrderCustomer().setShippingCost(0);
		MasterDatabase.getOrderCustomer().setSubtotal(0);
		MasterDatabase.getOrderCustomer().setTotal(0);
		MasterDatabase.saveInventory();
		MasterDatabase.saveCustomers();
		MasterDatabase.saveInvoices();
		Stage stage = new Stage();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/ViewOrder.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		if (MasterDatabase.getLoggedEmployee().getStoreLevel() == 3) {
			switchToAdminTab(event);
		} else if (MasterDatabase.getLoggedEmployee().getStoreLevel() == 2) {
			switchToOperationsTab(event);
		} else if (MasterDatabase.getLoggedEmployee().getStoreLevel() == 1) {
			switchToCustomerServiceRepTab(event);
		}
		stage.show();
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
