package controller;

import java.io.IOException;
import java.net.URL;
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
import model.InvItem;
import model.MasterDatabase;

public class CreateOrderController2 implements Initializable {

	@FXML
	private Label descriptionLabel;

	@FXML
	private Label itemId;

	@FXML
	private Label itemIdLabel;

	@FXML
	private Label category;

	@FXML
	private Label categoryLabel;

	@FXML
	private Label price;

	@FXML
	private Label priceLabel;

	@FXML
	private Label condition;

	@FXML
	private Label conditionLabel;

	@FXML
	private Label specifications;

	@FXML
	private Label specsLabel;

	@FXML
	private Label status;

	@FXML
	private Label statusLabel;

	@FXML
	private Button closeBtn;

	@FXML
	private ImageView imageView;

	@FXML
	private Hyperlink myCart;

	@FXML
	private Hyperlink logoutLink;

	@FXML
	private Label numItems;

	@FXML
	private TextField searchField;

	@FXML
	private Button searchBtn;

	@FXML
	private Button previousBtn;

	@FXML
	private Button nextBtn;

	@FXML
	private Button cancelBtn;

	@FXML
	private Button addToCart;

	@FXML
	private ListView<InvItem> list;

	private ObservableList<InvItem> allItems, selectedItems;

	@FXML
	private Label employee;

	@FXML
	private Label empId;

	@FXML
	private Label customer;
	
	private InvItem item;
	
	@FXML
	private Label weightValue;
	
	@FXML
	private Label weight;
	
	@FXML
	private Label lbs;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		customer.setText(MasterDatabase.getOrderCustomer().getFirstName() + " "
				+ MasterDatabase.getOrderCustomer().getLastName());
		numItems.setText(String.valueOf(MasterDatabase.getOrderCustomer().getCart().size()));
		if(numItems.getText().equals("0")){
			nextBtn.setDisable(true);
		}

	}

	public void onClicked() {
		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<InvItem>() {

			public void changed(ObservableValue<? extends InvItem> observable, InvItem oldValue, InvItem newValue) {
				setFields();
				setLabelsVisible();
				item = list.getSelectionModel().getSelectedItem();
			}
		});
	}

	public void goToPreviousPage(ActionEvent event) {
		MasterDatabase.getOrderCustomer().getCart().clear();
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/CreateOrderTab.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void goToNextPage(ActionEvent event) {
		
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/CreateOrderTab3.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void searchItem() {
		ObservableList<InvItem> items = FXCollections.observableArrayList();
		for (InvItem item : MasterDatabase.getInventory().values()) {
			item.getDescription().replace(" ", "");
			if ((!item.equals(null)) && item.getDescription().contains(searchField.getText())
					&& item.getStatus().equals("In Stock") && !items.contains(item) && !MasterDatabase.getOrderCustomer().getCart().contains(item)) {
				items.add(item);
			}
		}
		list.setItems(items);
		onClicked();
	}

	public void setFields() {
		selectedItems = list.getSelectionModel().getSelectedItems();
		for (InvItem item : selectedItems) {
			descriptionLabel.setText(item.getDescription());
			itemIdLabel.setText(item.getItemId());
			categoryLabel.setText(item.getCategory());
			priceLabel.setText(String.valueOf(item.getPrice()));
			conditionLabel.setText(item.getCondition());
			specsLabel.setText(item.getSpecs());
			statusLabel.setText(item.getStatus());
			weightValue.setText(String.valueOf(item.getWeight()));
		}

	}

	public void setLabelsVisible() {
		descriptionLabel.setVisible(true);
		itemId.setVisible(true);
		itemIdLabel.setVisible(true);
		category.setVisible(true);
		categoryLabel.setVisible(true);
		price.setVisible(true);
		priceLabel.setVisible(true);
		condition.setVisible(true);
		conditionLabel.setVisible(true);
		specifications.setVisible(true);
		specsLabel.setVisible(true);
		imageView.setVisible(true);
		status.setVisible(true);
		statusLabel.setVisible(true);
		addToCart.setVisible(true);
		weightValue.setVisible(true);
		weight.setVisible(true);
		lbs.setVisible(true);

	}

	public void setLabelsInvisible() {
		descriptionLabel.setVisible(false);
		itemId.setVisible(false);
		itemIdLabel.setVisible(false);
		category.setVisible(false);
		categoryLabel.setVisible(false);
		price.setVisible(false);
		priceLabel.setVisible(false);
		condition.setVisible(false);
		conditionLabel.setVisible(false);
		specifications.setVisible(false);
		specsLabel.setVisible(false);
		imageView.setVisible(false);
		status.setVisible(false);
		statusLabel.setVisible(false);
		weightValue.setVisible(false);
		weight.setVisible(false);
		lbs.setVisible(false);

	}

	public void addItemToCart() {
		if(!MasterDatabase.getOrderCustomer().getCart().contains(item) && !list.getSelectionModel().isEmpty()){
			MasterDatabase.getOrderCustomer().getCart().add(item);
			list.getItems().remove(item);
			numItems.setText(String.valueOf(MasterDatabase.getOrderCustomer().getCart().size()));
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Item added to cart!");
			alert.showAndWait();
			nextBtn.setDisable(false);
		}
		
	}

	public void cancelOrder(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Are you sure you want to cancel?");
		alert.setContentText("All order progress will be lost");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK){
			if(!MasterDatabase.getOrderCustomer().getCart().isEmpty()){
				MasterDatabase.getOrderCustomer().getCart().clear();
			}
			if(MasterDatabase.getLoggedEmployee().getStoreLevel() == 3){
				switchToAdminTab(event);
			} else if(MasterDatabase.getLoggedEmployee().getStoreLevel() == 2){
				switchToOperationsTab(event);
			} else if(MasterDatabase.getLoggedEmployee().getStoreLevel() == 1){
				switchToCustomerServiceRepTab(event);
			}
		} else {
			alert.close();
		}
		
	}
	
	public void switchToAdminTab(ActionEvent event){
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

	public void logout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Are you sure you want to logout?");
		alert.setContentText("All unsaved progress will be lost");
		Optional<ButtonType> result = alert.showAndWait();
		if(result.get() == ButtonType.OK){
			MasterDatabase.getOrderCustomer().getCart().clear();
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

	public void goToMyCart(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/MyCartTab.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

}
