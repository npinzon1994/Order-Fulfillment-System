package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Invoice;
import model.MasterDatabase;
import model.ShippingLabel;

/**
 * This application is a final project for CSE248, an advanced object oriented programming course.
 * It is an order fulfillment/POS system for the Habitat for Humanity Restore, which is a non-profit
 * store where people donate home improvement items. A typical restore would contain items similar to 
 * those of Home Depot and Lowes.
 * 
 * The application has 3 main types of users: 
 * 
 * 1) Customer Service Representative - Assist customers in ordering/picking up items.
 * 2) Operations Associate - Responsible for documenting the information of donated items 
 *    and adding them to the system. Also responsible for picking (confirming) processed 
 *    orders, packing and shipping out confirmed orders, and maintaining an accurate inventory.
 * 3) Administrator - Can perform any function built into the program. Having never worked in 
 *    one of these restores myself, I'm not entirely certain how each job description is laid
 *    out. Regardless of the organization however, this software needs the ability to add and 
 *    remove users when someone gets hired, or either quits or gets fired. Adding and Removing 
 *    users is the main function of the Administrator.
 * 
 * To avoid confusing the first person, besides myself, who uses this application, here are some
 * key pieces of information:
 * 
 * --There is one of each type of employee accounts already created each with ID numbers E1, E3, and E4.
 * 	 These accounts all have the same password "habitat12" and are each an Administrator, Customer Service
 * 	 Representative, and Operations Associate respectively.
 * 
 * --Customer IDs start from C0. With each new customer created, the digit increases by 1.
 * 
 * --Invoice Numbers all start from #HFH1. With each new order, the digit increases by 1.
 * 
 * --Item IDs all start from 1000. With each new item, the number increases by 1.
 * 
 * @version 1.0.0 - December 15, 2017
 * @author Nick Pinzon
 *
 */

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
