package controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import model.InvItem;
import model.Invoice;
import model.MasterDatabase;

public class ViewOrderDetailsController implements Initializable {

	@FXML
	private Label employee;
	@FXML
	private Label empId;
	@FXML
	private Label customer;
	@FXML
	private Hyperlink logoutLink;
	@FXML
	private Hyperlink returnLink;
	@FXML
	private Hyperlink cancelLink;
	@FXML
	private Label shipStreetAddress;
	@FXML
	private Label shipCityStateZip;
	@FXML
	private Label billStreetAddress;
	@FXML
	private Label billCityStateZip;
	@FXML
	private Label orderNumber;
	@FXML
	private Label orderStatus;
	@FXML
	private Label shippingMethodLbl;
	@FXML
	private TableView<InvItem> table;
	@FXML
	private TableColumn<InvItem, String> itemColumn;
	@FXML
	private TableColumn<InvItem, String> priceColumn;
	@FXML
	private Button backBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		table.setSelectionModel(null);
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		customer.setText(MasterDatabase.getSearchCustomer().getFirstName() + " "
				+ MasterDatabase.getSearchCustomer().getLastName());
		shipStreetAddress.setText(MasterDatabase.getSearchCustomer().getShippingAddress().getStreetAddress());
		shipCityStateZip.setText(MasterDatabase.getSearchCustomer().getShippingAddress().getCity() + " "
				+ MasterDatabase.getSearchCustomer().getShippingAddress().getState() + ", "
				+ MasterDatabase.getSearchCustomer().getShippingAddress().getZip());
		billStreetAddress.setText(MasterDatabase.getSearchCustomer().getBillingAddress().getStreetAddress());
		billCityStateZip.setText(MasterDatabase.getSearchCustomer().getBillingAddress().getCity() + " "
				+ MasterDatabase.getSearchCustomer().getBillingAddress().getState() + ", "
				+ MasterDatabase.getSearchCustomer().getBillingAddress().getZip());
		orderNumber.setText(MasterDatabase.getOrderBeingViewed().getInvoiceNumber());
		orderStatus.setText(MasterDatabase.getOrderBeingViewed().getOrderStatus());
		shippingMethodLbl.setText(MasterDatabase.getOrderBeingViewed().getShippingMethod());
		itemColumn.setCellValueFactory(
				cellData -> Bindings.createStringBinding(() -> cellData.getValue().getDescription()));
		priceColumn.setCellValueFactory(new PropertyValueFactory<InvItem, String>("price"));
		populateTable();

	}

	public void populateTable() {
		ObservableList<InvItem> items = FXCollections.observableArrayList();
		for (Invoice order : MasterDatabase.getSearchCustomer().getOrders().values()) {
			if (order.getInvoiceNumber().equals(MasterDatabase.getOrderBeingViewed().getInvoiceNumber())) {
				for (InvItem item : order.getItems()) {
					items.add(item);
				}
			}
		}
		table.setItems(items);
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

	public void goBack(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/ViewCustomerOrders.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void createReturn(ActionEvent event) {
		if (MasterDatabase.getOrderBeingViewed().getOrderStatus().equals("Returned to Store")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Order has already been returned!");
			alert.showAndWait();
		} else if (MasterDatabase.getOrderBeingViewed().getOrderStatus().equals("Processed")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Order has not yet shipped!");
			alert.setContentText("If you want to cancel, please hit cancel order.");
			alert.showAndWait();
		} else if (MasterDatabase.getOrderBeingViewed().getOrderStatus().equals("Confirmed")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Order has not yet shipped!");
			alert.setContentText("If you want to cancel, please hit cancel order.");
			alert.showAndWait();
		} else if (MasterDatabase.getOrderBeingViewed().getOrderStatus().equals("Shipped")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Order currently in transit!");
			alert.setContentText("An order in transit needs to arrive at its destination before it can be returned. "
					+ "Please cancel order instead.");
			alert.showAndWait();
		} else if (MasterDatabase.getOrderBeingViewed().getOrderStatus().equals("Cancelled")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Order has already been cancelled!");
			alert.showAndWait();
		} else {
			
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("/view/ReturnTabSub.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Scene scene = new Scene(root);
			stage.setScene(scene);
		}
	}

	public void cancelOrder(ActionEvent event) {
		if (MasterDatabase.getOrderBeingViewed().getOrderStatus().equals("Cancelled")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Order has already been cancelled!");
			alert.showAndWait();
		} else if (MasterDatabase.getOrderBeingViewed().getOrderStatus().equals("Returned to Store")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Order has already been returned!");
			alert.showAndWait();
		} else if (MasterDatabase.getOrderBeingViewed().getOrderStatus().equals("Ready for Pickup")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Ready-for-Pickup orders cannot be cancelled. Please return instead.");
			alert.showAndWait();
		} else if (MasterDatabase.getOrderBeingViewed().getOrderStatus().equals("Delivered")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Delivered orders cannot be cancelled. Please return instead.");
			alert.showAndWait();
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Are you sure you want to cancel this order?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				MasterDatabase.getOrderBeingViewed().setOrderStatus("Cancelled");
				for (Customer customer : MasterDatabase.getCustomerDatabase().values()) {
					if (customer.getId().equals(MasterDatabase.getSearchCustomer().getId())) {
						for (Invoice invoice : customer.getOrders().values()) {
							for (Invoice dataInvoice : MasterDatabase.getInvoiceDatabase().values()) {
								if (invoice.getInvoiceNumber()
										.equals(MasterDatabase.getOrderBeingViewed().getInvoiceNumber())) {
									invoice.setOrderStatus("Cancelled");
									if (dataInvoice.getInvoiceNumber()
											.equals(MasterDatabase.getOrderBeingViewed().getInvoiceNumber())) {
										dataInvoice.setOrderStatus("Cancelled");
									}
									for (InvItem item : invoice.getItems()) {
										for (InvItem databaseItem : MasterDatabase.getInventory().values()) {
											if (item.getItemId().equals(databaseItem.getItemId())) {
												databaseItem.setStatus("In Stock");
											}
										}
									}
								}
							}

						}
					}
				}
				MasterDatabase.saveCustomers();
				MasterDatabase.saveInventory();
				MasterDatabase.saveInvoices();
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
			} else {
				alert.close();
			}

		}

	}

}
