package controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import model.Invoice;
import model.MasterDatabase;

public class OrderSearchController implements Initializable {

	@FXML
	private Label employee;
	@FXML
	private Label empId;
	@FXML
	private TextField orderNumber;
	@FXML
	private TextField customerId;
	@FXML
	private TextField lastName;
	@FXML
	private TextField zipCode;
	@FXML
	private Hyperlink logoutLink;
	@FXML
	private TableView<Invoice> table;
	@FXML
	private TableColumn<Invoice, String> orderColumn;
	@FXML
	private TableColumn<Invoice, String> customerColumn;
	@FXML
	private TableColumn<Invoice, String> customerIdColumn;
	@FXML
	private Button searchBtn;
	@FXML
	private Button viewOrderBtn;
	@FXML
	private Button backBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		for(Invoice invoice : MasterDatabase.getInvoiceDatabase().values()){
			if(invoice.getInvoiceNumber().equals(orderNumber.getText())){
				MasterDatabase.setOrderBeingViewed(invoice);
			}
		}
		
	}

	public void orderSearch() {
		ObservableList<Invoice> orders = FXCollections.observableArrayList();
		for (Invoice invoice : MasterDatabase.getInvoiceDatabase().values()) {
			if (orderNumber.getText().equals(invoice.getInvoiceNumber()) && (!orders.contains(invoice))) {
				orders.add(invoice);
			}
				for (Customer customer : MasterDatabase.getCustomerDatabase().values()) {
					ObservableList<Invoice> customerOrders = FXCollections.observableArrayList();
					if (customerId.getText().equals(customer.getId())) {
						orders.addAll(customerOrders);
					} else if (customer.getLastName().contains(lastName.getText())
							|| customer.getBillingAddress().getZip().contains(zipCode.getText())) {
						orders.addAll(customerOrders);
					}

				}
			
		}
		orderColumn.setCellValueFactory(
				cellData -> Bindings.createStringBinding(() -> cellData.getValue().getInvoiceNumber()));
		customerColumn.setCellValueFactory(
				cellData -> Bindings.createStringBinding(() -> cellData.getValue().getCustomer().getLastName()
						+ cellData.getValue().getCustomer().getFirstName()));
		customerIdColumn.setCellValueFactory(
				cellData -> Bindings.createStringBinding(() -> cellData.getValue().getCustomer().getId()));

		table.setItems(orders);
	}

	public void viewOrder(ActionEvent event) {
		MasterDatabase.setOrderBeingViewed(table.getSelectionModel().getSelectedItem());
		MasterDatabase.setSearchCustomer(MasterDatabase.getOrderBeingViewed().getCustomer());
		System.out.println(MasterDatabase.getOrderBeingViewed().getInvoiceNumber());
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/ViewOrderDetailsTab.fxml"));
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
			root = FXMLLoader.load(getClass().getResource("/view/HomePageAdmin.fxml"));
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
