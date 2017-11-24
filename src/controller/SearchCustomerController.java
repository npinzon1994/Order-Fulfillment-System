package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.stage.Stage;
import model.Customer;

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

	}

	public void search() {

	}

	public void viewOrders() {

	}

	public void cancel() {

	}
	
	public void logout(ActionEvent event){
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
