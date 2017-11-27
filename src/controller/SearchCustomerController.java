package controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import model.Invoice;
import model.MasterDatabase;

public class SearchCustomerController implements Initializable {

	@FXML
	private Label employee;

	@FXML
	private Label empId;

	@FXML
	private Hyperlink logoutLink;

	@FXML
	private TextField idField;

	@FXML
	private TextField fNameField;

	@FXML
	private TextField lNameField;

	@FXML
	private TextField zipField;

	@FXML
	private TableView<Customer> table;

	@FXML
	private TableColumn<Customer, String> nameColumn;

	@FXML
	private TableColumn<Customer, String> addressColumn;

	@FXML
	private Button searchBtn;

	@FXML
	private Button cancelBtn;

	@FXML
	private Button viewOrdersBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		viewOrdersBtn.setVisible(false);
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
	}

	public void onClicked() {
		table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {

			@Override
			public void changed(ObservableValue<? extends Customer> observable, Customer oldValue, Customer newValue) {
				
				viewOrdersBtn.setVisible(true);
				MasterDatabase.setSearchCustomer(table.getSelectionModel().getSelectedItem());
				for (Customer customer : MasterDatabase.getCustomerDatabase().values()) {
					if (table.getSelectionModel().getSelectedItem().getId().equals(customer.getId()))
						for (Invoice invoice : customer.getOrders().values()) {
							System.out.println(invoice.getInvoiceNumber());
						}
				}

			}
		});
	}

	public void search() {
		ObservableList<Customer> customers = FXCollections.observableArrayList();
		for (Customer customer : MasterDatabase.getCustomerDatabase().values()) {
			if ((!customer.equals(null)) && (customer.getShippingAddress().getZip().contains(zipField.getText())
					&& !customers.contains(customer))) {
				customers.add(customer);
			}
		}
		nameColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(
				() -> cellData.getValue().getLastName() + ", " + cellData.getValue().getFirstName()));
		addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("shippingAddress"));
		table.setItems(customers);
		onClicked();
	}

	public void viewOrders(ActionEvent event) {
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

	public void cancel(ActionEvent event) {
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
