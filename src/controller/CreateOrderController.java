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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
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
	TableColumn<Customer, String> customerColumn;

	@FXML
	TableColumn<Customer, String> addressColumn;

	private Customer customer;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void logout(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
		} catch (IOException e) {
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void searchForCustomer() {
		ObservableList<Customer> customers = FXCollections.observableArrayList();
		for (Customer customer : MasterDatabase.getCustomerDatabase().values()) {
			if ((!customer.equals(null)) && (customer.getShippingAddress().getZip().contains(zipField.getText()))) {
				customers.add(customer);
			}
		}

		customerColumn.setCellValueFactory(cellData -> Bindings.createStringBinding(
				() -> cellData.getValue().getLastName() + ", " + cellData.getValue().getFirstName()));

		addressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("shippingAddress"));

		table.getItems().addAll(customers);
	}

	public void openCreateCustomerTab(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/NewCustomerOrderTab.fxml"));
		} catch (IOException e) {
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

		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

}
