package controller;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import model.MasterDatabase;

public class AdminController implements Initializable, Serializable {

	private static final long serialVersionUID = 1L;

	@FXML
	private Hyperlink newUserLink;

	@FXML
	private Hyperlink newInvItemLink;

	@FXML
	private Hyperlink searchInventoryLink;

	// whenever button gets clicked, this method gets called
	public void openAddUserPane(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/NewEmployeeTab.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void openAddItemPane(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/NewInvItemTab.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
	
	public void openCreateOrderPane(ActionEvent event){
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/CreateOrderTab.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void openSearchInventoryPane(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/SearchInventoryTab.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e) {
			
		}

	}
	
	public void openSearchUserPane(ActionEvent event){
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/UserSearchTab.fxml"));
			Scene scene = new Scene(root);
			stage.setScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
