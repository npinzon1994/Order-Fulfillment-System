package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


import javafx.beans.binding.Bindings;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.InvItem;
import model.MasterDatabase;
import model.Validation;

public class InvItemController implements Initializable {

	@FXML
	private TextArea description;

	@FXML
	private ComboBox<String> category;

	@FXML
	private TextField priceDollars;

	@FXML
	private TextField priceCents;

	@FXML
	private ComboBox<String> condition;

	@FXML
	private TextArea specs;

	@FXML
	private Button cancelBtn;

	@FXML
	private Button submitBtn;

	@FXML
	private Button browseBtn;

	@FXML
	private ImageView imageView;

	private InvItem item;

	@FXML
	private Label employee;

	@FXML
	private Label empId;

	@FXML
	private TextField weightField;

	@FXML
	private Hyperlink logoutLink;
	
	@FXML
	private TextField imagePathField;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		category.getItems().removeAll(category.getItems());
		category.getItems().addAll("Appliances", "Bath & Faucets", "Blinds & Window Treatments", "Building Materials",
				"Decor & Furniture", "Doors & Windows", "Electrical", "Flooring & Area Rugs", "Hardware",
				"Heating & Cooling", "Kitchen & Kitchenware", "Lawn & Garden", "Lighting & Ceiling Fans",
				"Outdoor Living", "Plumbing", "Storage & Organization", "Tools");
		category.getSelectionModel().selectFirst();
		condition.getItems().removeAll(condition.getItems());
		condition.getItems().addAll("Excellent", "Great", "Good", "Fair", "Poor");
		condition.getSelectionModel().selectFirst();
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		Validation.limitInputToCentsField(priceCents);
		Validation.limitInputToNumbers(priceDollars);
		Validation.limitInputToNumbers(weightField);
		bindFieldsToButton();
	}

	public void bindFieldsToButton() {
		submitBtn.disableProperty().bind(Bindings.isEmpty(description.textProperty())
				.or(Bindings.isEmpty(priceDollars.textProperty()).or(Bindings.isEmpty(priceCents.textProperty())
						.or(Bindings.isEmpty(specs.textProperty()).or(Bindings.isEmpty(weightField.textProperty()))))));
	}

	public void switchBackToHomeTab(ActionEvent event) {
		if (description.getText().isEmpty() && priceDollars.getText().isEmpty() && priceCents.getText().isEmpty()
				&& specs.getText().isEmpty()) {
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

	public void switchToAdminTab(ActionEvent event) {
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

	public void addItemToInventory(ActionEvent event) {
		item = new InvItem();
		initializeItem();
		MasterDatabase.getInventory().put(item.getItemId(), item);
		MasterDatabase.saveInventory();
		clearFields();
		newCreatedAlert();
	}

	public void initializeItem() {
		double dollars = Double.parseDouble(priceDollars.getText());
		double cents = (Double.parseDouble(priceCents.getText())) / 100;
		double price = dollars + cents;
		item.setPrice(price);
		item.setCategory(category.getSelectionModel().getSelectedItem());
		item.setDescription(description.getText());
		item.setCondition(condition.getSelectionModel().getSelectedItem());
		item.setSpecs(specs.getText());
//		if(imagePathField != null){
//			item.setImagePath(imagePathField.getText());
//			System.out.println(imagePathField.getText());
//			MasterDatabase.saveImage(imagePathField.getText());
//		}
		
		
		item.setWeight(Double.parseDouble(weightField.getText()));
		item.setStatus("In Stock");

	}

	public void newCreatedAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Item added successfully!");
		alert.showAndWait();
	}

	public void clearFields() {
		description.clear();
		category.getSelectionModel().selectFirst();
		priceDollars.clear();
		priceCents.clear();
		condition.getSelectionModel().selectFirst();
		specs.clear();
		weightField.clear();
	}

	public void logout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Are you sure you want to logout?");
		alert.setContentText("All unsaved progress will be lost");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
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
