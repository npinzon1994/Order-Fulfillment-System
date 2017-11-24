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

public class CreateOrderController implements Initializable {

	@FXML
	private Hyperlink logoutLink;

	@FXML
	private Hyperlink createCustomerLink;

	@FXML
	private TextField fNameField;

	@FXML
	private TextField lNameField;

	@FXML
	private TextField zipField;

	@FXML
	private Button searchBtn;

	@FXML
	private Button nextBtn;

	@FXML
	private Button cancelBtn;

	@FXML
	private TableView<Customer> table;

	@FXML
	private TableColumn<Customer, String> customerColumn;

	@FXML
	private TableColumn<Customer, String> addressColumn;

	@FXML
	private Label shippingAddress;

	@FXML
	private Label shippingStreetAddress;

	@FXML
	private Label shippingCityStateZip;

	@FXML
	private Label billingAddress;

	@FXML
	private Label billingStreetAddress;

	@FXML
	private Label billingCityStateZip;

	private Customer customer;
	private ObservableList<Customer> allCustomers, selectedCustomers;

	@FXML
	private Label employee;

	@FXML
	private Label empId;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
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

	public void searchForCustomer() {
		ObservableList<Customer> customers = FXCollections.observableArrayList();
		for (Customer customer : MasterDatabase.getCustomerDatabase().values()) {
			if ((!customer.equals(null)) && (customer.getShippingAddress().getZip().contains(zipField.getText())
					&& !customers.contains(customer))) {
				customers.add(customer);
			}
		}
		customerColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(
				() -> cellData.getValue().getLastName() + ", " + cellData.getValue().getFirstName()));
		addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("shippingAddress"));
		table.setItems(customers);
		onClicked();
	}

	public void onClicked() {
		table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Customer>() {

			@Override
			public void changed(ObservableValue<? extends Customer> observable, Customer oldValue, Customer newValue) {
				setShippingFields();
				setBillingFields();
				setLabelsVisible();
				MasterDatabase.setOrderCustomer(table.getSelectionModel().getSelectedItem());
			}
		});
	}

	public void setShippingFields() {
		selectedCustomers = table.getSelectionModel().getSelectedItems();
		for (Customer customer : selectedCustomers) {
			shippingStreetAddress.setText(customer.getShippingAddress().getStreetAddress());
			shippingCityStateZip.setText(customer.getShippingAddress().getCity() + " "
					+ customer.getShippingAddress().getState() + ", " + customer.getShippingAddress().getZip());
		}

	}

	public void setBillingFields() {
		selectedCustomers = table.getSelectionModel().getSelectedItems();
		for (Customer customer : selectedCustomers) {
			billingStreetAddress.setText(customer.getBillingAddress().getStreetAddress());
			billingCityStateZip.setText(customer.getBillingAddress().getCity() + " "
					+ customer.getBillingAddress().getState() + ", " + customer.getBillingAddress().getZip());
		}

	}

	public void setLabelsVisible() {
		shippingAddress.setVisible(true);
		shippingStreetAddress.setVisible(true);
		shippingCityStateZip.setVisible(true);
		billingAddress.setVisible(true);
		billingStreetAddress.setVisible(true);
		billingCityStateZip.setVisible(true);
	}

	public void openCreateCustomerTab(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/NewCustomerOrderTab.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void goToNextPage(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/CreateOrderTab2.fxml"));
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

}
