package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Administrator;
import model.CustomerServiceRep;
import model.OperationsAssociate;
import model.User;

public class NewEmployeeController implements Initializable {

	@FXML
	private Button cancelBtn;

	@FXML
	private Button submitBtn;

	@FXML
	private TextField fNameField;

	@FXML
	private TextField lNameField;

	@FXML
	private TextField phoneField;

	@FXML
	private TextField emailField;

	@FXML
	private ComboBox<String> levelBox;

	private HashMap<String, User> userDatabase = new HashMap<String, User>();
	private CustomerServiceRep user;

	private LocalDate date = LocalDate.now();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		levelBox.getItems().removeAll(levelBox.getItems());
		levelBox.getItems().addAll("1 - Customer Service Representative", "2 - Operations Associate",
				"3 - Administrator");
		levelBox.getSelectionModel().selectFirst();
	}

	public void switchBackToHomeTab(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/HomePageAdmin.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void addUserToDatabase(ActionEvent event) {
		createEmployee();
		initializeEmployee();
		userDatabase.put(user.getId(), user);
		clearFields();
		createdNewEmployeeAlert();
	}

	public void createEmployee() {

		if (levelBox.getValue().equals("1 - Customer Service Representative")) {
			user = new CustomerServiceRep();
		} else if (levelBox.getValue().equals("2 - Operations Associate")) {
			user = new OperationsAssociate();
		} else if (levelBox.getValue().equals("3 - Administrator")) {
			user = new Administrator();
		}

	}

	public void initializeEmployee() {
		user.setFirstName(fNameField.getText());
		user.setLastName(lNameField.getText());
		user.setEmail(emailField.getText());
		user.setPhone(phoneField.getText());
		user.setHireDate(date);
	}

	public void clearFields() {
		fNameField.clear();
		lNameField.clear();
		emailField.clear();
		phoneField.clear();
		levelBox.getSelectionModel().selectFirst();
	}

	public void createdNewEmployeeAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(
				user.getFirstName() + " " + user.getLastName() + " successfully added!\n\t\tID: " + user.getId());
		alert.showAndWait();
	}

	public Button getSubmitButton() {
		return submitBtn;
	}

	public Button getCancelButton() {
		return cancelBtn;
	}

}
