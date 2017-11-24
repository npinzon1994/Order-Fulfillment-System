package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

	@FXML
	private ImageView imageView;

	private ObservableList<CustomerServiceRep> allUsers, usersSelected;

	private LocalDate date = LocalDate.now();
	
	@FXML
	private Label employee;
	
	@FXML
	private Label empId;

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
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("User not found!");
				alert.showAndWait();
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
			imageView.setImage(user.getImage());
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
		empIdLabel.setVisible(true);
		position.setVisible(true);
		positionLabel.setVisible(true);
		status.setVisible(true);
		statusLabel.setVisible(true);
		level.setVisible(true);
		levelLabel.setVisible(true);
		startDate.setVisible(true);
		startDateLabel.setVisible(true);
		imageView.setVisible(true);
		termDate.setVisible(true);
		if (!isEmployed()) {
			termDateLabel.setVisible(true);
		}

	}

	public void setLabelsInvisible() {
		nameLabel.setVisible(false);
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
		termDateLabel.setVisible(false);
		imageView.setVisible(false);

	}

	public void removeUser() {
		usersSelected = list.getSelectionModel().getSelectedItems();
		allUsers = list.getItems();
		for (CustomerServiceRep user : usersSelected) {
			if(user.getStatus().equals("Terminated")){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Employee has already been removed!");
				alert.showAndWait();
			} else {
				if (MasterDatabase.getEmployeeDatabase().containsValue(user)) {
					MasterDatabase.getEmployeeDatabase().get(user.getId()).setStatus("Terminated");
					MasterDatabase.getEmployeeDatabase().get(user.getId()).setTermDate(date.toString());

					allUsers.remove(user);
					setLabelsInvisible();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("Employee successfully removed from system!");
					alert.showAndWait();

				}
			}
		}
	}

}
