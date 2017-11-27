package controller;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.InvItem;
import model.MasterDatabase;

public class MyCartController implements Initializable {

	@FXML
	private Label employee;

	@FXML
	private Label empId;

	@FXML
	private TableView<InvItem> table;

	@FXML
	private TableColumn<InvItem, String> itemColumn;

	@FXML
	private TableColumn<InvItem, String> priceColumn;

	@FXML
	private Label yourCart;

	@FXML
	private Label isEmpty;

	@FXML
	private Button removeBtn;

	@FXML
	private Button cancelBtn;

	@FXML
	private Label subtotal;

	@FXML
	private Label subtotalLabel;

	private double subTotal;
	
	ObservableList<InvItem> items;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		NumberFormat format = NumberFormat.getCurrencyInstance();
		items = FXCollections.observableArrayList();
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		subTotal = 0;
		subtotalLabel.setText(format.format(subTotal));
		if (MasterDatabase.getOrderCustomer().getCart().isEmpty()) {
			isEmpty.setVisible(true);
		} else {
			for (InvItem item : MasterDatabase.getOrderCustomer().getCart()) {
				items.add(item);
				subTotal += item.getPrice();
			}
			itemColumn.setCellValueFactory(
					cellData -> Bindings.createStringBinding(() -> cellData.getValue().getDescription()));
			priceColumn.setCellValueFactory(
					cellData -> Bindings.createStringBinding(() -> "$" + cellData.getValue().getPrice()));
			subtotalLabel.setText("$" + String.valueOf(subTotal));
			table.setItems(items);
		}
	}

	public void logout(ActionEvent event) {
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
	}

	public void cancel(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/CreateOrderTab2.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void removeFromCart() {
		NumberFormat format = NumberFormat.getCurrencyInstance();
		InvItem item = null;
		Iterator<InvItem> iter = MasterDatabase.getOrderCustomer().getCart().iterator();
		while(iter.hasNext()){
			item = iter.next();
			if(item.equals(table.getSelectionModel().getSelectedItem())){
				iter.remove();
				subTotal -= item.getPrice();
				subtotalLabel.setText(format.format(subTotal));
			}
		}
				
		
			
		
	}

}
