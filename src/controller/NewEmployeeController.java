package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.beans.binding.Bindings;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
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
import model.Validation;

public class NewEmployeeController implements Initializable {

	@FXML
	private Label employee;

	@FXML
	private Label empId;

	@FXML
	private Button cancelBtn;

	@FXML
	private Button submitBtn;

	@FXML
	private Button browseBtn;

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
	private BufferedImage bufferedImage;
	private Image image;
	
	@FXML
	private ImageView phoneX;
	
	@FXML
	private ImageView emailX;
	
	@FXML
	private Hyperlink logoutLink;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		levelBox.getItems().removeAll(levelBox.getItems());
		levelBox.getItems().addAll("1 - Customer Service Representative", "2 - Operations Associate",
				"3 - Administrator");
		levelBox.getSelectionModel().selectFirst();
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		bindFieldsToButton();
		phoneX.setVisible(false);
		emailX.setVisible(false);
		Validation.limitInputToTenDigitPhoneField(phoneField);
	}

	public void bindFieldsToButton() {
		submitBtn.disableProperty()
				.bind(Bindings.isEmpty(fNameField.textProperty()).or(Bindings.isEmpty(lNameField.textProperty()).or(
						Bindings.isEmpty(phoneField.textProperty()).or(Bindings.isEmpty(emailField.textProperty())))));
	}

	public void switchBackToHomeTab(ActionEvent event) {
		if (fNameField.getText().isEmpty() && lNameField.getText().isEmpty() && emailField.getText().isEmpty()
				&& phoneField.getText().isEmpty()) {
			switchBackToAppropriateHomePage(event);
		} else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setHeaderText("Abandon changes?");
			alert.setContentText("New customer information will be discarded");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				switchBackToAppropriateHomePage(event);
			} else {
				alert.close();
			}
		}
	}
	
	public void switchBackToAppropriateHomePage(ActionEvent event) {
		if (MasterDatabase.getLoggedEmployee().getStoreLevel() == 3) {
			goToNextPage(event);
		} else if (MasterDatabase.getLoggedEmployee().getStoreLevel() == 2) {
			switchToOperationsTab(event);
		} else if (MasterDatabase.getLoggedEmployee().getStoreLevel() == 1) {
			switchToCustomerServiceRepTab(event);
		}
	}
	
	public void goToNextPage(ActionEvent event) {
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

	public void switchToOperationsTab(ActionEvent event) {
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

	public void switchToCustomerServiceRepTab(ActionEvent event) {
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

	public void addUserToDatabase(ActionEvent event) {
		if(Validation.isValidPhone(phoneX, phoneField.getText()) && Validation.isValidEmail(emailX, emailField.getText())){
			createEmployee();
			initializeEmployee();
			MasterDatabase.getEmployeeDatabase().put(user.getId(), user);
			MasterDatabase.saveEmployees();
			clearFields();
			createdNewEmployeeAlert();
			for (CustomerServiceRep rep : MasterDatabase.getEmployeeDatabase().values()) {
				System.out.println(rep.getFirstName() + " " + rep.getLastName());
			}
		}
		
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
		user.setStatus("Currently Employed");
		user.setHireDate(user.getHireDate());
		user.setImage(tempImage);
		user.setPassword("habitat12");
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
			bufferedImage = null;
			try {
				bufferedImage = ImageIO.read(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
			image = SwingFXUtils.toFXImage(bufferedImage, null);
			imageView.setImage(image);
			tempImage = image;
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
