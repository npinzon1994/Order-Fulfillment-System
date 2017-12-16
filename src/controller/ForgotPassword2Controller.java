package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
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


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		employeeNameLbl.setText("Change Password For " + MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		toolTip1 = new Tooltip("Password doesn't match password on file!!");
		toolTip2 = new Tooltip("New password cannot be the same as the old password!!");
		toolTip3 = new Tooltip("Password confirmation doesn't match new password!");

		toolTip2.setWrapText(true);
		toolTip3.setWrapText(true);

		Tooltip.install(redX1, toolTip1);
		Tooltip.install(redX2, toolTip2);
		Tooltip.install(redX3, toolTip3);

		bindFieldsToButton();

	}

	public void bindFieldsToButton() {
		submitBtn.disableProperty().bind(Bindings.isEmpty(oldPassword.textProperty())
				.or(Bindings.isEmpty(newPassword.textProperty()).or(Bindings.isEmpty(confirmPassword.textProperty()))));
	}

	public void submitPassword(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		if (!MasterDatabase.getLoggedEmployee().getPassword().equals(oldPassword.getText())) {
			redX1.setVisible(true);
			redX2.setVisible(false);
			redX3.setVisible(false);
			 Node node1 = (Node) event.getSource();
			 Stage stage1 = (Stage) node1.getScene().getWindow();
			 toolTip1.show(stage1);
		} else if (MasterDatabase.getLoggedEmployee().getPassword().equals(oldPassword.getText())
				&& MasterDatabase.getLoggedEmployee().getPassword().equals(newPassword.getText())) {
			redX1.setVisible(false);
			redX2.setVisible(true);
			redX3.setVisible(false);
		} else if (MasterDatabase.getLoggedEmployee().getPassword().equals(oldPassword.getText())
				&& !MasterDatabase.getLoggedEmployee().getPassword().equals(newPassword.getText())
				&& !newPassword.getText().equals(confirmPassword.getText())) {
			redX1.setVisible(false);
			redX2.setVisible(false);
			redX3.setVisible(true);
		} else {
			MasterDatabase.getEmployeeDatabase().get(MasterDatabase.getLoggedEmployee().getId()).setPassword(newPassword.getText());
			MasterDatabase.saveEmployees();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Password changed successfully!");
			alert.showAndWait();
			stage.close();
		}

	}

	public void clearFields() {
		oldPassword.clear();
		newPassword.clear();
		confirmPassword.clear();
	}

}
