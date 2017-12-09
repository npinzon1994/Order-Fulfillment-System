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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import model.Invoice;
import model.MasterDatabase;

public class ViewCustomerOrdersController implements Initializable {

	@FXML
	private Label employee;

	@FXML
	private Label empId;

	@FXML
	private Label customer;

	@FXML
	private Hyperlink logoutLink;

	@FXML
	private TableView<Invoice> table;

	@FXML
	private TableColumn<Invoice, String> numberColumn;

	@FXML
	private TableColumn<Invoice, String> statusColumn;

	@FXML
	private Button viewBtn;

	@FXML
	private Button backBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList<Invoice> list = FXCollections.observableArrayList();
		for (Customer customer : MasterDatabase.getCustomerDatabase().values()) {
			if (customer.getId().equals(MasterDatabase.getSearchCustomer().getId())) {
				for (Invoice invoice : customer.getOrders().values()) {
					list.add(invoice);
				}
			}
		}

		viewBtn.disableProperty().bind(Bindings.isEmpty(table.getSelectionModel().getSelectedItems()));
		employee.setText(MasterDatabase.getLoggedEmployee().getFirstName() + " "
				+ MasterDatabase.getLoggedEmployee().getLastName());
		empId.setText(MasterDatabase.getLoggedEmployee().getId());
		customer.setText(MasterDatabase.getSearchCustomer().getFirstName() + " "
				+ MasterDatabase.getSearchCustomer().getLastName());
		numberColumn.setCellValueFactory(
				cellData -> Bindings.createStringBinding(() -> cellData.getValue().getInvoiceNumber()));
		statusColumn.setCellValueFactory(new PropertyValueFactory<Invoice, String>("orderStatus"));
		table.setItems(list);

	}
	
	public void onClicked() {
		table.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Invoice>() {

			@Override
			public void changed(ObservableValue<? extends Invoice> observable, Invoice oldValue, Invoice newValue) {
				viewBtn.setDisable(false);
				MasterDatabase.setOrderBeingViewed(table.getSelectionModel().getSelectedItem());
				

			}
		});
	}

	public void viewOrderDetails(ActionEvent event) {
		MasterDatabase.setOrderBeingViewed(table.getSelectionModel().getSelectedItem());
		System.out.println(MasterDatabase.getOrderBeingViewed().getInvoiceNumber());
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/ViewOrderDetailsTab.fxml"));
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
			root = FXMLLoader.load(getClass().getResource("/view/SearchCustomerTab.fxml"));
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
