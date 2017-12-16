package controller;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.InvItem;
import model.MasterDatabase;

public class ViewOrderController implements Initializable {

	@FXML
	private Label customerLabel;

	@FXML
	private Label shippingMethodLabel;

	@FXML
	private Label shipStreetAddress;

	@FXML
	private Label shipCityStateZip;

	@FXML
	private Label billStreetAddress;

	@FXML
	private Label billCityStateZip;

	@FXML
	private TableView<InvItem> table;

	@FXML
	private TableColumn<InvItem, String> itemColumn;

	@FXML
	private TableColumn<InvItem, String> priceColumn;

	@FXML
	private Label totalLabel;

	@FXML
	private Label subtotalLabel;

	@FXML
	private Label shippingLabel;

	@FXML
	private Label orderNumber;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		customerLabel.setText(MasterDatabase.getOrderBeingViewed().getCustomer().getFirstName() + " "
				+ MasterDatabase.getOrderBeingViewed().getCustomer().getLastName());
		shipStreetAddress.setText(MasterDatabase.getOrderBeingViewed().getShippingAddress().getStreetAddress());
		shipCityStateZip.setText(MasterDatabase.getOrderBeingViewed().getShippingAddress().getCity() + " "
				+ MasterDatabase.getOrderBeingViewed().getShippingAddress().getState() + ", "
				+ MasterDatabase.getOrderBeingViewed().getShippingAddress().getZip());
		billStreetAddress.setText(MasterDatabase.getOrderBeingViewed().getBillingAddress().getStreetAddress());
		billCityStateZip.setText(MasterDatabase.getOrderBeingViewed().getBillingAddress().getCity() + " "
				+ MasterDatabase.getOrderBeingViewed().getBillingAddress().getState() + ", "
				+ MasterDatabase.getOrderBeingViewed().getBillingAddress().getZip());
		shippingMethodLabel.setText(MasterDatabase.getOrderBeingViewed().getShippingMethod());
		populateTable();
		NumberFormat format = NumberFormat.getCurrencyInstance();
		subtotalLabel.setText(format.format(MasterDatabase.getOrderBeingViewed().getSubtotal()));
		shippingLabel.setText(format.format(MasterDatabase.getOrderBeingViewed().getShippingCost()));
		totalLabel.setText(format.format(MasterDatabase.getOrderBeingViewed().getTotal()));
		orderNumber.setText(MasterDatabase.getOrderBeingViewed().getInvoiceNumber());

	}

	public void populateTable() {
		ObservableList<InvItem> items = FXCollections.observableArrayList();
		for (InvItem item : MasterDatabase.getOrderBeingViewed().getItems()) {
			items.add(item);
		}
		itemColumn.setCellValueFactory(
				cellData -> Bindings.createStringBinding(() -> cellData.getValue().getDescription()));
		priceColumn.setCellValueFactory(new PropertyValueFactory<InvItem, String>("price"));
		table.setItems(items);
	}

}
