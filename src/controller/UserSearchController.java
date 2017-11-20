package controller;

import java.io.IOException;
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

public class UserSearchController implements Initializable {

	@FXML
	private Button searchByIdBtn;

	@FXML
	private Button searchByNameBtn;

	@FXML
	private Button cancelBtn;

	@FXML
	private Button removeBtn;

	@FXML
	private ListView<CustomerServiceRep> list;

	@FXML
	private TextField searchByIdField;

	@FXML
	private TextField searchByNameField;

	@FXML
	private Label nameLabel;

	@FXML
	private Label empId;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

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

	public void searchUserById() {
		ObservableList<CustomerServiceRep> users = FXCollections.observableArrayList();
		for (CustomerServiceRep user : MasterDatabase.getUserDatabase().values()) {
			if ((!user.equals(null)) && user.getId().contains(searchByIdField.getText())) {
				users.add(user);
			} else if (searchByIdField.getText().isEmpty() || !(MasterDatabase.getUserDatabase().containsValue(user))) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("User not found!");
				alert.showAndWait();
			}
		}
		list.setItems(users);
		onClicked();
	}

	public void searchUserByName() {
		ObservableList<CustomerServiceRep> users = FXCollections.observableArrayList();
		for (CustomerServiceRep user : MasterDatabase.getUserDatabase().values()) {
			if ((!user.equals(null)) && (user.getFirstName().contains(searchByIdField.getText())
					|| user.getLastName().contains(searchByIdField.getText()))) {
				users.add(user);
			} else if (searchByIdField.getText().isEmpty() || !(MasterDatabase.getUserDatabase().containsValue(user))) {
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
			imageView.setImage(user.getPicture());
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
		imageView.setVisible(true);
		termDate.setVisible(true);
		if (!isEmployed()) {
			termDateLabel.setVisible(true);
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
		termDateLabel.setVisible(false);
		imageView.setVisible(false);

	}

	public void removeUser() {
		System.out.println();
		usersSelected = list.getSelectionModel().getSelectedItems();
		allUsers = list.getItems();
		for (CustomerServiceRep user : usersSelected) {
			if(user.getStatus().equals("Terminated")){
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("User has already been removed!");
				alert.showAndWait();
			} else {
				if (MasterDatabase.getUserDatabase().containsValue(user)) {
					MasterDatabase.getUserDatabase().get(user.getId()).setStatus("Terminated");
					MasterDatabase.getUserDatabase().get(user.getId()).setTermDate(date.toString());

					allUsers.remove(user);
					setLabelsInvisible();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setHeaderText("User successfully removed from system!");
					alert.showAndWait();

				}
			}
		}
	}

}
