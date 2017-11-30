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
import javafx.stage.Stage;
import model.Customer;
import model.Invoice;
import model.MasterDatabase;

public class ReadyForPickupQueueController implements Initializable {

	@FXML
	private Label employee;
	@FXML
	private Label empId;
	@FXML
	private Hyperlink logoutLink;
	@FXML
	private TableView<Invoice> table;
	@FXML
	private TableColumn<Invoice, String> numberColumn;
	@FXML
	private TableColumn<Invoice, String> itemColumn;
	@FXML
	private TableColumn<Invoice, String> customerColumn;
	@FXML
	private Button backBtn;
	@FXML
	private Button nextBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Invoice> orders = FXCollections.observableArrayList();
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		for (Invoice order : MasterDatabase.getInvoiceDatabase().values()) {
			if (order.getOrderStatus().equals("Ready For Pickup")) {
				if (order.getShippingMethod().equals("Pickup In-Store")) {
					orders.add(order);
				}

			}
		}
		numberColumn.setCellValueFactory(
				cellData -> Bindings.createStringBinding(() -> cellData.getValue().getInvoiceNumber()));
		itemColumn.setCellValueFactory(
				cellData -> Bindings.createStringBinding(() -> cellData.getValue().getItems().get(0).toString()));
		customerColumn.setCellValueFactory(
				cellData -> Bindings.createStringBinding(() -> cellData.getValue().getCustomer().getLastName() + ", "
						+ cellData.getValue().getCustomer().getFirstName()));
		table.setItems(orders);

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

	public void pickup() {
		MasterDatabase.setOrderBeingViewed(table.getSelectionModel().getSelectedItem());
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Mark as picked up?");
		alert.setContentText("Hit yes only if the customer's drivers license matches their customer profile");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK){
			table.getItems().remove(MasterDatabase.getOrderBeingViewed());
			MasterDatabase.getOrderBeingViewed().setOrderStatus("Customer Picked Up");
			for(Customer customer : MasterDatabase.getCustomerDatabase().values()){
				if(customer.getId().equals(MasterDatabase.getOrderBeingViewed().getCustomer().getId())){
					for(Invoice invoice : customer.getOrders().values()){
						for(Invoice dataInvoice : MasterDatabase.getInvoiceDatabase().values()){
							if(dataInvoice.getInvoiceNumber().equals(invoice.getInvoiceNumber())){
								dataInvoice.setOrderStatus("Customer Picked Up");
								if(invoice.getInvoiceNumber().equals(MasterDatabase.getOrderBeingViewed().getInvoiceNumber())){
									invoice.setOrderStatus("Customer Picked Up");
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
		
		
	}

}
