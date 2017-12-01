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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.MasterDatabase;

public class ForgotPasswordController implements Initializable {

	@FXML
	private TextField idField;
	@FXML
	private Button goBtn;
	@FXML
	private Label label;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		label.setVisible(false);
	}

	public void nextPane(ActionEvent event) {
		if (!MasterDatabase.getEmployeeDatabase().containsKey(idField.getText())) {
			label.setVisible(true);
		} else {
			MasterDatabase.setLoggedEmployee(MasterDatabase.getEmployeeDatabase().get(idField.getText()));
			Node node = (Node) event.getSource();
			Stage stage = (Stage) node.getScene().getWindow();
			Parent root = null;
			try {
				root = FXMLLoader.load(getClass().getResource("/view/ForgotPasswordPane2.fxml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			Scene scene = new Scene(root);
			stage.setScene(scene);
		}
		
	}

}
