package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.CustomerServiceRep;
import model.MasterDatabase;

public class ForgotPassword2Controller implements Initializable {

	@FXML
	private TextField oldPassword;
	@FXML
	private TextField newPassword;
	@FXML
	private TextField confirmPassword;
	@FXML
	private Label employeeNameLbl;
	@FXML
	private Button submitBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		employeeNameLbl.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
	}

	public void submitPassword(ActionEvent event) {
		if (MasterDatabase.getLoggedEmployee().getPassword().equals(oldPassword.getText())) {
			if ((!newPassword.getText().equals(oldPassword.getText())
					&& !confirmPassword.getText().equals(oldPassword.getText()))
					&& (newPassword.getText().equals(confirmPassword.getText()))) {
				for (CustomerServiceRep rep : MasterDatabase.getEmployeeDatabase().values()) {
					if (rep.getId().equals(MasterDatabase.getLoggedEmployee().getId())) {
						rep.setPassword(newPassword.getText());
					}
				}
			}
		}
		MasterDatabase.saveEmployees();
		clearFields();
	}
	
	public void clearFields(){
		oldPassword.clear();
		newPassword.clear();
		confirmPassword.clear();
	}

}
