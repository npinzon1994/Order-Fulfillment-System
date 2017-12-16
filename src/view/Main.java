package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Invoice;
import model.MasterDatabase;
import model.ShippingLabel;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			MasterDatabase.getMasterDatabase();
			MasterDatabase.loadInventory();
			MasterDatabase.loadEmployees();
			MasterDatabase.loadCustomers();
			MasterDatabase.loadInvoices();
			MasterDatabase.loadShippingLabels();
			for(ShippingLabel label : MasterDatabase.getShippingLabelDatabase().values()){
				System.out.println(label.getTrackingNumber());
			}
			
			for(Invoice invoice : MasterDatabase.getInvoiceDatabase().values()){
				System.out.println(invoice.getInvoiceNumber());
				System.out.println(invoice.getCustomer().getFirstName());
				System.out.println(invoice.getCustomer().getLastName());
			}
			Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
