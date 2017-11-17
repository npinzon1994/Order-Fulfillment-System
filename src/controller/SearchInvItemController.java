package controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.InvItem;
import model.MasterDatabase;

public class SearchInvItemController implements Initializable {

	// @FXML Parent parent;

	@FXML
	private ListView<InvItem> list;

	@FXML
	private Hyperlink link;

	@FXML
	private TextField searchBar;

	@FXML
	private Button cancelBtn;

	@FXML
	private Button searchBtn;

	@FXML
	private Button viewItemBtn;

	@FXML
	private Label searchResults;

	@FXML
	private VBox vbox;

	private ObservableList<InvItem> selectedItems, allItems;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void searchItem() {
		allItems = list.getItems();
		for (InvItem item : MasterDatabase.getInventory().values()) {
			if (item.getDescription().contains(searchBar.getText())) {
				allItems.add(item);
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Item not found!");
				alert.showAndWait();
			}
		}
	}
	
	public void viewItem(ActionEvent event){
		selectedItems = list.getSelectionModel().getSelectedItems();
		
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

}
