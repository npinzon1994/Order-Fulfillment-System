package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.MasterDatabase;

public class LoginController implements Initializable {

	@FXML
	private Button loginBtn;

	@FXML
	private TextField userIdField;

	@FXML
	private TextField passwordField;

	@FXML
	private Label invalidId;

	@FXML
	private Hyperlink forgotPasswordLink;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginBtn.disableProperty().bind(Bindings.isEmpty(passwordField.textProperty()));
	}

	public void login(ActionEvent event) {

		if (!MasterDatabase.getEmployeeDatabase().containsKey(userIdField.getText())
				|| MasterDatabase.getEmployeeDatabase().containsKey(userIdField.getText()) && MasterDatabase
						.getEmployeeDatabase().get(userIdField.getText()).getStatus().equals("Terminated")) {
			invalidId.setText("*Invald User ID");
			invalidId.setVisible(true);
		} else if (!MasterDatabase.getEmployeeDatabase().get(userIdField.getText()).getPassword()
				.equals(passwordField.getText())) {
			invalidId.setText("*Invalid Password");
			invalidId.setVisible(true);
		} else {
			if(MasterDatabase.getEmployeeDatabase().get(userIdField.getText()).getStoreLevel() == 3){
				MasterDatabase.setLoggedEmployee(MasterDatabase.getEmployeeDatabase().get(userIdField.getText()));
				switchToAdminPane(event);
			} else if(MasterDatabase.getEmployeeDatabase().get(userIdField.getText()).getStoreLevel() == 2){
				MasterDatabase.setLoggedEmployee(MasterDatabase.getEmployeeDatabase().get(userIdField.getText()));
				switchToOperationsPane(event);
			} else if(MasterDatabase.getEmployeeDatabase().get(userIdField.getText()).getStoreLevel() == 1){
				MasterDatabase.setLoggedEmployee(MasterDatabase.getEmployeeDatabase().get(userIdField.getText()));
				switchToCustomerServiceRepPane(event);
			}
			
		}
	}
	
	public void switchToAdminPane(ActionEvent event){
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
	
	public void switchToOperationsPane(ActionEvent event){
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
	
	public void switchToCustomerServiceRepPane(ActionEvent event){
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

	public void goToForgotPasswordPane() {
		Stage stage = new Stage();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/ForgotPasswordPane.fxml"));
		} catch (IOException e) {
			e.printStackTrace();

		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}

}
