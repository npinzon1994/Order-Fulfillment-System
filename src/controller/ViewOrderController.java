package controller;

import java.net.URL;
import java.text.NumberFormat;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
		customerLabel.setText(MasterDatabase.getOrderCustomer().getFirstName() + " "
				+ MasterDatabase.getOrderCustomer().getLastName());
		shipStreetAddress.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getStreetAddress());
		shipCityStateZip.setText(MasterDatabase.getOrderCustomer().getShippingAddress().getCity() + " "
				+ MasterDatabase.getOrderCustomer().getShippingAddress().getState() + ", "
				+ MasterDatabase.getOrderCustomer().getShippingAddress().getZip());
		billStreetAddress.setText(MasterDatabase.getOrderCustomer().getBillingAddress().getStreetAddress());
		billCityStateZip.setText(MasterDatabase.getOrderCustomer().getBillingAddress().getCity() + " "
				+ MasterDatabase.getOrderCustomer().getBillingAddress().getState() + ", "
				+ MasterDatabase.getOrderCustomer().getBillingAddress().getZip());
		shippingMethodLabel.setText(MasterDatabase.getCurrentOrder().getShippingMethod());
		populateTable();
		NumberFormat format = NumberFormat.getCurrencyInstance();
		subtotalLabel.setText(format.format(MasterDatabase.getCurrentOrder().getSubtotal()));
		shippingLabel.setText(format.format(MasterDatabase.getCurrentOrder().getshippingCost()));
		totalLabel.setText(format.format(MasterDatabase.getCurrentOrder().getTotal()));
		table.setSelectionModel(null);
		orderNumber.setText(MasterDatabase.getCurrentOrder().getInvoiceNumber());
		
	}
	
	public void populateTable() {
		ObservableList<InvItem> items = FXCollections.observableArrayList();
		for (InvItem item : MasterDatabase.getOrderCustomer().getCart()) {
			items.add(item);
		}
		itemColumn.setCellValueFactory(
				cellData -> Bindings.createStringBinding(() -> cellData.getValue().getDescription()));
		priceColumn.setCellValueFactory(new PropertyValueFactory<InvItem, String>("price"));
		table.setItems(items);
	}

}
