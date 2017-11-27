package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
	}

}
