package controller;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
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
import javafx.scene.control.Button;
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
		customerLabel.setText(MasterDatabase.getOrderCustomer().getFirstName() + " "
				+ MasterDatabase.getOrderCustomer().getLastName());
		shipStreetAddress.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getStreetAddress());
		shipCityStateZip.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getCity() + " "
				+ MasterDatabase.getOrderCustomer().getShippingAddress().getState() + ", "
				+ MasterDatabase.getOrderCustomer().getShippingAddress().getZip());
		billStreetAddress.setText(MasterDatabase.getOrderCustomer().getBillingAddress().getStreetAddress());
		billCityStateZip.setText(MasterDatabase.getOrderCustomer().getBillingAddress().getCity() + " "
				+ MasterDatabase.getOrderCustomer().getBillingAddress().getState() + ", "
				+ MasterDatabase.getOrderCustomer().getBillingAddress().getZip());
		shippingMethodLabel.setText(MasterDatabase.getOrderCustomer().getShippingMethod());
		populateTable();
		NumberFormat format = NumberFormat.getCurrencyInstance();
		totalLabel.setText(format.format(MasterDatabase.getOrderCustomer().getTotal()));
		table.setSelectionModel(null);

	}

	public void populateTable() {
		ObservableList<InvItem> items = FXCollections.observableArrayList();
		for (InvItem item : MasterDatabase.getOrderCustomer().getCart()) {
			items.add(item);
		}
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
			root = FXMLLoader.load(getClass().getResource("/view/CreateOrderTab4.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void createInvoice() {
		Invoice invoice = new Invoice();
		invoice.setShippingAddress(MasterDatabase.getOrderCustomer().getShippingAddress());
		invoice.setBillingAddress(MasterDatabase.getOrderCustomer().getBillingAddress());
		invoice.setOrderStatus("Processed");
		for (InvItem item : MasterDatabase.getOrderCustomer().getCart()) {
			invoice.getItems().add(item);
		}
		invoice.setSubtotal(MasterDatabase.getOrderCustomer().getSubtotal());
		invoice.setshippingCost(MasterDatabase.getOrderCustomer().getShippingCost());
		invoice.setTotal(MasterDatabase.getOrderCustomer().getTotal());
		invoice.setShippingMethod(MasterDatabase.getOrderCustomer().getShippingMethod());
		for(Customer customer : MasterDatabase.getCustomerDatabase().values()){
			if(customer.getId().equals(MasterDatabase.getOrderCustomer().getId())){
				customer.getOrders().put(invoice.getInvoiceNumber(), invoice);
			}
			
		}
	}

	public void placeOrder(ActionEvent event) {
		createInvoice();
		Stage stage = new Stage();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/ViewOrder.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		goToHomePage(event);
		stage.show();
		for(InvItem item : MasterDatabase.getOrderCustomer().getCart()){
			if(MasterDatabase.getInventory().containsValue(item)){
				item.setStatus("Out of Stock");
			}
		}
		MasterDatabase.getOrderCustomer().getCart().clear();
		MasterDatabase.getOrderCustomer().setShippingCost(0);
		MasterDatabase.getOrderCustomer().setSubtotal(0);
		MasterDatabase.getOrderCustomer().setTotal(0);
		MasterDatabase.saveInventory();
		MasterDatabase.saveCustomers();
	}

	public void goToHomePage(ActionEvent event) {
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

}
