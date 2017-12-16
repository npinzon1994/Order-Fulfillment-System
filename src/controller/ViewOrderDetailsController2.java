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
import model.Address;
import model.InvItem;
import model.MasterDatabase;

public class ViewOrderDetailsController2 implements Initializable {

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
		orderNumber.setText(MasterDatabase.getOrderBeingViewed().getInvoiceNumber());
		Address shippingAddress = MasterDatabase.getInvoiceDatabase().get(orderNumber.getText()).getShippingAddress();
		Address billingAddress = MasterDatabase.getInvoiceDatabase().get(orderNumber.getText()).getBillingAddress();
		ObservableList<InvItem> items = FXCollections.observableArrayList();
		for (InvItem item : MasterDatabase.getOrderBeingViewed().getItems()) {
			items.add(item);

		}
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		customer.setText(MasterDatabase.getSearchCustomer().getFirstName() + " "
				+ MasterDatabase.getSearchCustomer().getLastName());
		shipStreetAddress.setText(shippingAddress.getStreetAddress());
		shipCityStateZip.setText(shippingAddress.getCity() + " " + shippingAddress.getState() + ", " + shippingAddress.getZip());
		billStreetAddress.setText(billingAddress.getStreetAddress());
		billCityStateZip.setText(billingAddress.getCity() + " " + billingAddress.getState() + ", " + billingAddress.getZip());
		orderStatus.setText(MasterDatabase.getOrderBeingViewed().getOrderStatus());
		shippingMethodLbl.setText(MasterDatabase.getOrderBeingViewed().getShippingMethod());
		itemColumn.setCellValueFactory(
				cellData -> Bindings.createStringBinding(() -> cellData.getValue().getDescription()));
		priceColumn.setCellValueFactory(new PropertyValueFactory<InvItem, String>("price"));
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
			root = FXMLLoader.load(getClass().getResource("/view/OrderSearchTab.fxml"));
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
			alert.setContentText("An order in transit needs to arrive at its destination before it can be returned");
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
		} else if(MasterDatabase.getOrderBeingViewed().getOrderStatus().equals("Shipped")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Order currently in Transit!");
			alert.setContentText("Order cannot be cancelled until it has arrived at its destination");
			alert.showAndWait();
		} else if(MasterDatabase.getOrderBeingViewed().getOrderStatus().equals("Customer Picked Up")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Order has already been picked up by customer!");
			alert.setContentText("Please use return instead");
			alert.showAndWait();
		} else {
			for(InvItem item : MasterDatabase.getOrderBeingViewed().getItems()){
				System.out.println(item.getDescription());
			}
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Are you sure you want to cancel this order?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {

				MasterDatabase.getOrderBeingViewed().setOrderStatus("Cancelled");

				MasterDatabase.getInvoiceDatabase().get(MasterDatabase.getOrderBeingViewed().getInvoiceNumber())
						.setOrderStatus("Cancelled");

				MasterDatabase.getCustomerDatabase().get(MasterDatabase.getSearchCustomer().getId()).getOrders().put(
						MasterDatabase.getOrderBeingViewed().getInvoiceNumber(), MasterDatabase.getOrderBeingViewed());

				for(InvItem cartItem : MasterDatabase.getOrderBeingViewed().getItems()){
					for (InvItem item : MasterDatabase.getInventory().values()) {
						if (item.getItemId().equals(cartItem.getItemId())) {
							item.setStatus("In Stock");
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
