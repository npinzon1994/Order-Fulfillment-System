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
import model.Address;
import model.Customer;
import model.InvItem;
import model.Invoice;
import model.MasterDatabase;

public class PickOrderController implements Initializable {

	@FXML
	private Label employee;
	@FXML
	private Label empId;
	@FXML
	private Label orderLbl;
	@FXML
	private Label shippingMethodLbl;
	@FXML
	private Label fNameLbl;
	@FXML
	private Label lNameLbl;
	@FXML
	private Label shipStreetAddress;
	@FXML
	private Label shipCityStateZip;
	@FXML
	private Label billStreetAddress;
	@FXML
	private Label billCityStateZip;
	@FXML
	private Label phoneLbl;
	@FXML
	private Label emailLbl;
	@FXML
	private ListView<InvItem> list;
	@FXML
	private TextField itemIdField;
	@FXML
	private Button pickBtn;
	@FXML
	private Button backBtn;
	@FXML
	private Button confirmPickBtn;
	@FXML
	private Hyperlink logoutLink;
	@FXML
	private ImageView greenCheck;
	@FXML
	private ImageView redX;
	@FXML
	private Button cancelItemBtn;
	private Invoice invoice;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<InvItem> items = FXCollections.observableArrayList();
		invoice = MasterDatabase.getOrderBeingViewed();
		Address shippingAddress = invoice.getCustomer().getShippingAddress();
		Address billingAddress = invoice.getCustomer().getBillingAddress();
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		orderLbl.setText(invoice.getInvoiceNumber());
		shippingMethodLbl.setText(invoice.getShippingMethod());
		fNameLbl.setText(invoice.getCustomer().getFirstName());
		lNameLbl.setText(invoice.getCustomer().getLastName());
		shipStreetAddress.setText(shippingAddress.getStreetAddress());
		shipCityStateZip.setText(
				shippingAddress.getCity() + " " + shippingAddress.getState() + ", " + shippingAddress.getZip());
		billStreetAddress.setText(billingAddress.getStreetAddress());
		billCityStateZip
				.setText(billingAddress.getCity() + " " + billingAddress.getState() + ", " + billingAddress.getZip());
		phoneLbl.setText(invoice.getCustomer().getPhone());
		emailLbl.setText(invoice.getCustomer().getEmail());
		for (InvItem item : invoice.getItems()) {
			items.add(item);
		}
		list.setItems(items);
		pickBtn.setDisable(true);
		cancelItemBtn.setDisable(true);
		enableButtonsWhenSelected();
		confirmPickBtn.setDisable(true);

	}

	public void onClicked() {
		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<InvItem>() {

			@Override
			public void changed(ObservableValue<? extends InvItem> observable, InvItem oldValue, InvItem newValue) {
				if (list.getSelectionModel().isSelected(0)) {
					greenCheck.setVisible(false);
					redX.setVisible(false);
				}
				greenCheck.setVisible(false);
				redX.setVisible(false);
			}
		});
	}

	public void enableButtonsWhenSelected() {
		list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<InvItem>() {

			@Override
			public void changed(ObservableValue<? extends InvItem> observable, InvItem oldValue, InvItem newValue) {
				pickBtn.setDisable(false);
				cancelItemBtn.setDisable(false);
			}
		});
	}

	public void logout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Cancel order?");
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

	public void pick() {
		if (itemIdField.getText().equals(list.getSelectionModel().getSelectedItem().getItemId())) {
			redX.setVisible(false);
			greenCheck.setVisible(true);
			list.getItems().remove(list.getSelectionModel().getSelectedItem());
			list.getSelectionModel().clearSelection();
			pickBtn.setDisable(true);
			if(list.getItems().isEmpty()){
				confirmPickBtn.setDisable(false);
				cancelItemBtn.setDisable(true);
			}
		} else {
			greenCheck.setVisible(false);
			redX.setVisible(true);
		}
	}

	public void confirmPick(ActionEvent event) {
		if (greenCheck.isVisible() && !redX.isVisible()) {
			for (Invoice invoice : MasterDatabase.getInvoiceDatabase().values()) {
				for (Customer customer : MasterDatabase.getCustomerDatabase().values()) {
					for (Invoice order : customer.getOrders().values()) {
						if (invoice.getInvoiceNumber().equals(order.getInvoiceNumber()) && invoice.getInvoiceNumber()
								.equals(MasterDatabase.getOrderBeingViewed().getInvoiceNumber())) {
							if (invoice.getShippingMethod().equals("Pickup In-Store")) {
								invoice.setOrderStatus("Ready For Pickup");
								order.setOrderStatus("Ready For Pickup");
							} else {
								invoice.setOrderStatus("Confirmed");
								order.setOrderStatus("Confirmed");
							}

						}
					}
				}

			}
		}
		MasterDatabase.saveCustomers();
		MasterDatabase.saveInventory();
		MasterDatabase.saveInvoices();
		if (MasterDatabase.getLoggedEmployee().getStoreLevel() == 3) {
			switchToAdminTab(event);
		} else if (MasterDatabase.getLoggedEmployee().getStoreLevel() == 2) {
			switchToOperationsTab(event);
		} else if (MasterDatabase.getLoggedEmployee().getStoreLevel() == 1) {
			switchToCustomerServiceRepTab(event);
		}
	}

	public void cancelItem(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setHeaderText("Cancel Item?");
		alert.setContentText("Note that the order will be cancelled if this is the last item");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			MasterDatabase.getInventory().get(list.getSelectionModel().getSelectedItem().getItemId()).setStatus("In Stock");
			list.getItems().remove(list.getSelectionModel().getSelectedItem());
			MasterDatabase.getOrderBeingViewed().getItems().remove(list.getSelectionModel().getSelectedItem());
			if(list.getItems().isEmpty()){
				MasterDatabase.getOrderBeingViewed().setOrderStatus("Cancelled");
				MasterDatabase.getInvoiceDatabase().get(MasterDatabase.getOrderBeingViewed().getInvoiceNumber()).setOrderStatus("Cancelled");
				for(Invoice invoice : MasterDatabase.getOrderBeingViewed().getCustomer().getOrders().values()){
					if(invoice.getInvoiceNumber().equals(MasterDatabase.getOrderBeingViewed().getInvoiceNumber())){
						invoice.setOrderStatus("Cancelled");
						Node node = (Node) event.getSource();
						Stage stage = (Stage) node.getScene().getWindow();
						Parent root = null;
						try {
							root = FXMLLoader.load(getClass().getResource("/view/PickingQueue.fxml"));
						} catch (IOException e) {
							e.printStackTrace();
						}
						Scene scene = new Scene(root);
						stage.setScene(scene);
					}
				}
			}
		} else {
			alert.close();
		}
		MasterDatabase.saveInventory();
		MasterDatabase.saveInvoices();
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

	public void goBack(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/PickingQueue.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);

	}

}
