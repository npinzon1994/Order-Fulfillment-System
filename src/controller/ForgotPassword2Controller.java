package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
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
	@FXML
	private ImageView redX1;
	@FXML
	private ImageView redX2;
	@FXML
	private ImageView redX3;
	
	private Tooltip toolTip1;
	private Tooltip toolTip2;
	private Tooltip toolTip3;
	
	private Stage stage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		employeeNameLbl.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		toolTip1 = new Tooltip("Password doesn't match password on file!!");
		toolTip2 = new Tooltip("New password cannot be the same as the old password!!");
		toolTip3 = new Tooltip("Password confirmation doesn't match new password!");
		
		toolTip2.setWrapText(true);
		toolTip3.setWrapText(true);
		
		Tooltip.install(redX1, toolTip1);
		Tooltip.install(redX2, toolTip2);
		Tooltip.install(redX3, toolTip3);
		
	}

	public void submitPassword(ActionEvent event) {
		if(!MasterDatabase.getLoggedEmployee().getPassword().equals(oldPassword.getText())){
			redX1.setVisible(true);
			Node node = (Node) event.getSource();
			stage = (Stage) node.getScene().getWindow();
			toolTip1.show(stage);
		} else if (MasterDatabase.getLoggedEmployee().getPassword().equals(oldPassword.getText())) {
			if ((!newPassword.getText().equals(oldPassword.getText())
					&& !confirmPassword.getText().equals(oldPassword.getText()))
					&& (newPassword.getText().equals(confirmPassword.getText()))) {
				for (CustomerServiceRep rep : MasterDatabase.getEmployeeDatabase().values()) {
					if (rep.getId().equals(MasterDatabase.getLoggedEmployee().getId())) {
						rep.setPassword(newPassword.getText());
						MasterDatabase.saveEmployees();
						clearFields();
					} else {
						
					}
				}
			} else {
				redX2.setVisible(true);
				Node node = (Node) event.getSource();
				stage = (Stage) node.getScene().getWindow();
				toolTip2.show(stage);
			}
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Password changed successfully!");
		alert.showAndWait();
		
	}
	
	public void clearFields(){
		oldPassword.clear();
		newPassword.clear();
		confirmPassword.clear();
	}

}
