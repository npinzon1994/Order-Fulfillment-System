package controller;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Customer;
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
	private Label customerLabel;

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
	private Button itemOutOfStock;

	@FXML
	private ListView<InvItem> list;

	private ObservableList<InvItem> allItems, selectedItems;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void onClicked() {
		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<InvItem>() {

			public void changed(ObservableValue<? extends InvItem> observable, InvItem oldValue, InvItem newValue) {
				setFields();
				setLabelsVisible();
			}
		});
	}

	public void goToPreviousPage(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/CreateOrderTab.fxml"));
		} catch (IOException e) {
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void goToNextPage() {

	}

	public void searchItem() {
		ObservableList<InvItem> items = FXCollections.observableArrayList();
		for (InvItem item : MasterDatabase.getInventory().values()) {
			item.getDescription().replace(" ", "");
			if ((!item.equals(null)) && item.getDescription().contains(searchField.getText())
					&& !items.contains(item)) {
				items.add(item);
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Item not found!");
				alert.showAndWait();
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
			// imageView.setImage(item.getImage());
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

	public void addItemToCart() {
		
	}

	public void cancelOrder(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/HomePageAdmin.fxml"));
		} catch (IOException e) {
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void logout(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
		} catch (IOException e) {
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void goToMyCart() {

	}

}