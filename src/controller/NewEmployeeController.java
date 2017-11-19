package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.Administrator;
import model.CustomerServiceRep;
import model.MasterDatabase;
import model.OperationsAssociate;

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
	
	@FXML
	private ImageView imageView;
	
	private CustomerServiceRep user;

	private LocalDate date = LocalDate.now();
	
	private Image tempImage;

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
		MasterDatabase.getUserDatabase().put(user.getId(), user);
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
		user.setStatus("Currently Employed");
		user.setHireDate(user.getHireDate());
		user.setPicture(tempImage);
	}

	public void clearFields() {
		fNameField.clear();
		lNameField.clear();
		emailField.clear();
		phoneField.clear();
		levelBox.getSelectionModel().selectFirst();
	}
	
	public void chooseImage() {
		FileChooser chooser = new FileChooser();
		ExtensionFilter png = new ExtensionFilter("PNG Files", "*.png");
		ExtensionFilter jpeg = new ExtensionFilter("JPEG Files", "*.jpeg");
		ExtensionFilter bitmap = new ExtensionFilter("Bitmap Files", "*.bmp");
		ExtensionFilter tif = new ExtensionFilter("TIF Files", "*.tif");

		chooser.getExtensionFilters().addAll(png, jpeg, bitmap, tif);
		File file = chooser.showOpenDialog(emailField.getScene().getWindow());
		if (file != null) {
			BufferedImage bufferedImage = null;
			try {
				bufferedImage = ImageIO.read(file);
			} catch (IOException e) {

			}
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			imageView.setImage(image);
			tempImage = image;
		}
	}

	public void createdNewEmployeeAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(
				user.getFirstName() + " " + user.getLastName() + " successfully added!\n\t\t  ID: " + user.getId());
		alert.showAndWait();
	}

	public Button getSubmitButton() {
		return submitBtn;
	}

	public Button getCancelButton() {
		return cancelBtn;
	}

}
