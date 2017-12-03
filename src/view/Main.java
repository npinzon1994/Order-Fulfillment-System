package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.CustomerServiceRep;
import model.Invoice;
import model.MasterDatabase;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			MasterDatabase.getMasterDatabase();
			MasterDatabase.loadInventory();
			MasterDatabase.loadEmployees();
			MasterDatabase.loadCustomers();
			//MasterDatabase.loadInvoices();
			Parent root = FXMLLoader.load(getClass().getResource("/view/LoginPage.fxml"));
			Scene scene = new Scene(root, 558, 367);
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
