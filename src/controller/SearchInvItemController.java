package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.InvItem;
import model.MasterDatabase;

public class SearchInvItemController implements Initializable {

	@FXML
	private Label employee;

	@FXML
	private Label empId;

	@FXML
	private ListView<InvItem> list;

	@FXML
	private TextField searchBar;

	@FXML
	private Button cancelBtn;

	@FXML
	private Button searchBtn;

	@FXML
	private Button removeBtn;

	@FXML
	private Label searchResults;

	@FXML
	private VBox vbox;

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
	private Button addBtn;

	@FXML
	private ImageView imageView;

	private ObservableList<InvItem> allItems, selectedItems;
	private ObservableList<InvItem> items;

	@FXML
	private Label weightValue;
	
	@FXML
	private Hyperlink logoutLink;
	
	private InvItem currentItem = MasterDatabase.getCurrentItem();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		removeBtn.setVisible(false);
		addBtn.setVisible(false);
		onClicked();
	}

	public void onClicked() {
		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<InvItem>() {
			
			public void changed(ObservableValue<? extends InvItem> observable, InvItem oldValue, InvItem newValue) {
				MasterDatabase.setCurrentItem(list.getSelectionModel().getSelectedItem());
				setFields();
				setLabelsVisible();
//				if(list.getSelectionModel().getSelectedItem().getImagePath() != null){
//					MasterDatabase.load(MasterDatabase.getCurrentItem().getImagePath(), imageView);
//					imageView.setVisible(true);
//				} else {
//					imageView.setVisible(false);
//				}
				
				if (list.getSelectionModel().getSelectedItem().getStatus().equals("In Stock")) {
					addBtn.setVisible(false);
					removeBtn.setVisible(true);
				} else if (list.getSelectionModel().getSelectedItem().getStatus().equals("Out of Stock")) {
					addBtn.setVisible(true);
					removeBtn.setVisible(false);
				}
				searchBtn.setDisable(true);

			}
		});
	}
	
	public void textFieldClicked(){
		searchBtn.setDisable(false);
	}

	public void searchItem() {
		items = FXCollections.observableArrayList();
		if (list.getSelectionModel().isEmpty()) {
			for (InvItem item : MasterDatabase.getInventory().values()) {
				if (item.getDescription().contains(searchBar.getText()) && !items.contains(item)) {
					items.add(item);
				}
			}
			list.setItems(items);
		}
	}

	public void removeItem() {
		InvItem item = MasterDatabase.getCurrentItem();
		allItems = list.getItems();
		Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
		confirmAlert.setHeaderText("Remove item?");
		confirmAlert.setContentText("If you are sure you want to remove this item, please select OK");
		Optional<ButtonType> result = confirmAlert.showAndWait();
			if (result.get() == ButtonType.CANCEL) {
				confirmAlert.close();
			} else if (result.get() == ButtonType.OK) {	
				MasterDatabase.getInventory().get(item.getItemId()).setStatus("Out of Stock");
				list.getItems().remove(item);
				
				setLabelsInvisible();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Item successfully removed from inventory!");
				alert.showAndWait();
				MasterDatabase.saveInventory();
				
			}

		

	}

	public void addItem() {
		InvItem selectedItem = MasterDatabase.getCurrentItem();
		allItems = list.getItems();
		Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
		confirmAlert.setHeaderText("Add item?");
		confirmAlert.setContentText("If you are sure you want to add this item back into inventory, please select OK");
		Optional<ButtonType> result = confirmAlert.showAndWait();
		
		if (result.get() == ButtonType.OK) {
	
			MasterDatabase.getInventory().get(selectedItem.getItemId()).setStatus("In Stock");
			System.out.println(list.getItems().size());
			list.getItems().remove(selectedItem);
			
			setLabelsInvisible();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Item successfully added back to inventory!");
			alert.showAndWait();
			MasterDatabase.saveInventory();
		} else if (result.get() == ButtonType.CANCEL) {
			confirmAlert.close();
		}

		
		
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
			// imageView.setImage(item.getImage());
			weightValue.setText(String.valueOf(item.getWeight()));
			statusLabel.setText(item.getStatus());
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

	}

	public void switchBackToHomeTab(ActionEvent event) {
		if (MasterDatabase.getLoggedEmployee().getStoreLevel() == 3) {
			switchToAdminTab(event);
		} else if (MasterDatabase.getLoggedEmployee().getStoreLevel() == 2) {
			switchToOperationsTab(event);
		} else if (MasterDatabase.getLoggedEmployee().getStoreLevel() == 1) {
			switchToCustomerServiceRepTab(event);
		}
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
