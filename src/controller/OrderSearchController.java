package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Customer;
import model.InvItem;
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
		viewOrderBtn.setDisable(true);
		onClicked();
		
	}
	
	public void onClicked() {
		table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Invoice>() {

			@Override
			public void changed(ObservableValue<? extends Invoice> observable, Invoice oldValue, Invoice newValue) {
				viewOrderBtn.setDisable(false);
				MasterDatabase.setOrderBeingViewed(table.getSelectionModel().getSelectedItem());
				for(Customer customer : MasterDatabase.getCustomerDatabase().values()){
					if(customer.getOrders().containsKey(MasterDatabase.getOrderBeingViewed().getInvoiceNumber())){
						MasterDatabase.setSearchCustomer(customer);
					}
				}
			}
		});
	}

	public void orderSearch() {
		for(Customer customer : MasterDatabase.getCustomerDatabase().values()){
			System.out.println(customer.getOrders().values());
		}
		for (Invoice invoice : MasterDatabase.getInvoiceDatabase().values()) {
			System.out.println(invoice.getInvoiceNumber());
			
		}
		ObservableList<Invoice> orders = FXCollections.observableArrayList();
		for (Invoice invoice : MasterDatabase.getInvoiceDatabase().values()) {
			if (orderNumber.getText().equals(invoice.getInvoiceNumber()) && (!orders.contains(invoice))) {
				orders.add(invoice);
			}
			
		}
		orderColumn.setCellValueFactory(
				cellData -> Bindings.createStringBinding(() -> cellData.getValue().getInvoiceNumber()));
		customerColumn.setCellValueFactory(
				cellData -> Bindings.createStringBinding(() -> cellData.getValue().getCustomer().getFirstName()
						+ " " + cellData.getValue().getCustomer().getLastName()));
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
			root = FXMLLoader.load(getClass().getResource("/view/ViewOrderDetailsTab2.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void goBack(ActionEvent event) {
		if(MasterDatabase.getLoggedEmployee().getStoreLevel() == 3){
			switchToAdminTab(event);
		} else if(MasterDatabase.getLoggedEmployee().getStoreLevel() == 2){
			switchToOperationsTab(event);
		} else if(MasterDatabase.getLoggedEmployee().getStoreLevel() == 1){
			switchToCustomerServiceRepTab(event);
		}
	}
	
	public void switchToAdminTab(ActionEvent event){
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

	public void logout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Are you sure you want to logout?");
		alert.setContentText("All unsaved progress will be lost");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK){
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

}
