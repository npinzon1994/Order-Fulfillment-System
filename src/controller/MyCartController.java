package controller;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Iterator;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.InvItem;
import model.MasterDatabase;

public class MyCartController implements Initializable {

	@FXML
	private Label employee;

	@FXML
	private Label empId;

	@FXML
	private Label customerId;

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

	@FXML
	private Label totalItemsLbl;

	private double subTotal;

	ObservableList<InvItem> items;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		NumberFormat format = NumberFormat.getCurrencyInstance();
		items = FXCollections.observableArrayList();
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		customerId.setText(MasterDatabase.getOrderCustomer().getFirstName() + " "
				+ MasterDatabase.getOrderCustomer().getLastName());
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
		totalItemsLbl.setText(String.valueOf(MasterDatabase.getOrderCustomer().getCart().size()));
		removeBtn.setDisable(true);
		onClicked();
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

	public void onClicked() {
		table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<InvItem>() {

			public void changed(ObservableValue<? extends InvItem> observable, InvItem oldValue, InvItem newValue) {
				removeBtn.setDisable(false);

			}
		});
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

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Remove Item?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			while (iter.hasNext()) {
				item = iter.next();
				if (item.equals(table.getSelectionModel().getSelectedItem())) {
					iter.remove();
					subTotal -= item.getPrice();
					subtotalLabel.setText(format.format(subTotal));
					totalItemsLbl.setText(String.valueOf(MasterDatabase.getOrderCustomer().getCart().size()));
				}
			}
		} else {
			alert.close();
		}

	}

}
