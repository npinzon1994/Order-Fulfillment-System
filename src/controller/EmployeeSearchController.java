package controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.CustomerServiceRep;
import model.MasterDatabase;

public class EmployeeSearchController implements Initializable {

	@FXML
	private Button searchByNameBtn;

	@FXML
	private Button cancelBtn;

	@FXML
	private Button removeBtn;

	@FXML
	private ListView<CustomerServiceRep> list;

	@FXML
	private TextField searchByNameField;

	@FXML
	private Label nameLabel;

	@FXML
	private Label empIdLabel;

	@FXML
	private Label position;

	@FXML
	private Label positionLabel;

	@FXML
	private Label level;

	@FXML
	private Label levelLabel;

	@FXML
	private Label startDate;

	@FXML
	private Label startDateLabel;

	@FXML
	private Label status;

	@FXML
	private Label statusLabel;

	@FXML
	private Label termDate;

	@FXML
	private Label termDateLabel;

	private ObservableList<CustomerServiceRep> allUsers, usersSelected;

	private LocalDate date = LocalDate.now();

	@FXML
	private Label employee;

	@FXML
	private Label empId;

	@FXML
	private Hyperlink logoutLink;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		
	}

	public void switchBackToHomeTab(ActionEvent event) {
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

	public void onClicked() {
		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CustomerServiceRep>() {

			@Override
			public void changed(ObservableValue<? extends CustomerServiceRep> observable, CustomerServiceRep oldValue,
					CustomerServiceRep newValue) {
				setFields();
				setLabelsVisible();

			}
		});
	}

	public void searchUserByName() {
		ObservableList<CustomerServiceRep> users = FXCollections.observableArrayList();
		for (CustomerServiceRep user : MasterDatabase.getEmployeeDatabase().values()) {
			if ((user.getLastName().contains(searchByNameField.getText()))) {
				users.add(user);
			}
		}
		list.setItems(users);
		onClicked();
	}

	public void setFields() {
		usersSelected = list.getSelectionModel().getSelectedItems();
		for (CustomerServiceRep user : usersSelected) {
			nameLabel.setText(user.getFirstName() + " " + user.getLastName());
			empIdLabel.setText(user.getId());
			positionLabel.setText(user.getPosition());
			levelLabel.setText(String.valueOf(user.getStoreLevel()));
			statusLabel.setText(user.getStatus());
			startDateLabel.setText(user.getHireDate());
			if (user.getStatus().equals("Terminated")) {
				DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM-dd-YYYY");
				String dateString = date.format(dateFormat);
				termDateLabel.setText(dateString);
			}

		}

	}

	public boolean isEmployed() {
		boolean employed = true;
		for (CustomerServiceRep user : usersSelected) {
			if (user.getStatus().equals("Currently Employed")) {
				return employed;
			} else {
				return !employed;
			}
		}
		return employed;
	}

	public void setLabelsVisible() {
		nameLabel.setVisible(true);
		empId.setVisible(true);
		empIdLabel.setVisible(true);
		position.setVisible(true);
		positionLabel.setVisible(true);
		status.setVisible(true);
		statusLabel.setVisible(true);
		level.setVisible(true);
		levelLabel.setVisible(true);
		startDate.setVisible(true);
		startDateLabel.setVisible(true);
		termDate.setVisible(true);
		if (!isEmployed()) {
			termDateLabel.setVisible(true);
		} else {
			termDateLabel.setVisible(false);
		}

	}

	public void setLabelsInvisible() {
		nameLabel.setVisible(false);
		empId.setVisible(false);
		empIdLabel.setVisible(false);
		position.setVisible(false);
		positionLabel.setVisible(false);
		status.setVisible(false);
		statusLabel.setVisible(false);
		level.setVisible(false);
		levelLabel.setVisible(false);
		startDate.setVisible(false);
		startDateLabel.setVisible(false);
		termDate.setVisible(false);
		if (!isEmployed()) {
			termDateLabel.setVisible(false);
		} else {
			termDateLabel.setVisible(true);
		}

	}

	public void removeUser() {
		usersSelected = list.getSelectionModel().getSelectedItems();
		allUsers = list.getItems();
		for (CustomerServiceRep user : usersSelected) {
			if (user.getStatus().equals("Terminated")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Employee has already been removed!");
				alert.showAndWait();
			} else {
				if (MasterDatabase.getEmployeeDatabase().containsValue(user)) {
					Alert alert1 = new Alert(AlertType.CONFIRMATION);
					alert1.setHeaderText(
							"Remove " + user.getFirstName() + " " + user.getLastName() + " from the system?");
					Optional<ButtonType> result = alert1.showAndWait();
					if (result.get() == ButtonType.OK) {
						MasterDatabase.getEmployeeDatabase().get(user.getId()).setStatus("Terminated");
						MasterDatabase.getEmployeeDatabase().get(user.getId()).setTermDate(date.toString());

						allUsers.remove(user);
						setLabelsInvisible();
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setHeaderText("Employee successfully removed from system!");
						alert.showAndWait();
					} else {
						alert1.close();
					}

				}
			}
			MasterDatabase.saveEmployees();
		}
	}

	public void logout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Are you sure you want to logout?");
		alert.setContentText("All unsaved progress will be lost");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK){
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
		} else {
			alert.close();
		}
	}

}
