package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import model.InvItem;
import model.MasterDatabase;

public class InvItemController implements Initializable {

	@FXML
	private TextArea description;

	@FXML
	private ComboBox<String> category;

	@FXML
	private TextField priceDollars;

	@FXML
	private TextField priceCents;

	@FXML
	private ComboBox<String> condition;

	@FXML
	private TextArea specs;

	@FXML
	private Button cancelBtn;

	@FXML
	private Button submitBtn;

	@FXML
	private Button browseBtn;

	@FXML
	private ImageView imageView;

	private InvItem item;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		category.getItems().removeAll(category.getItems());
		category.getItems().addAll("Appliances", "Bath & Faucets", "Blinds & Window Treatments", "Building Materials",
				"Decor & Furniture", "Doors & Windows", "Electrical", "Flooring & Area Rugs", "Hardware",
				"Heating & Cooling", "Kitchen & Kitchenware", "Lawn & Garden", "Lighting & Ceiling Fans",
				"Outdoor Living", "Plumbing", "Storage & Organization", "Tools");
		category.getSelectionModel().selectFirst();
		condition.getItems().removeAll(condition.getItems());
		condition.getItems().addAll("Excellent", "Great", "Good", "Fair", "Poor");
		condition.getSelectionModel().selectFirst();
	}

	public void switchBackToHomeTab(ActionEvent event) {
		Node node = (Node) event.getSource();
		Stage stage = (Stage) node.getScene().getWindow();
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/view/HomePageAdmin.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}

	public void addItemToInventory(ActionEvent event) {
		item = new InvItem();
		initializeItem();
		MasterDatabase.getInventory().put(item.getItemId(), item);
		newCreatedAlert();
	}

	public void initializeItem() {
		double dollars = Double.parseDouble(priceDollars.getText());
		double cents = (Double.parseDouble(priceCents.getText())) / 100;
		double price = dollars + cents;
		item.setPrice(price);
		item.setCategory(category.getSelectionModel().getSelectedItem());
		item.setDescription(description.getText());
		item.setCondition(condition.getSelectionModel().getSelectedItem());
		item.setSpecs(specs.getText());
	}

	public void chooseImage() {
		FileChooser chooser = new FileChooser();
		ExtensionFilter jpeg = new ExtensionFilter("JPEG Files", "*.jpeg");
		ExtensionFilter png = new ExtensionFilter("PNG Files", "*.png");
		ExtensionFilter bitmap = new ExtensionFilter("Bitmap Files", "*.bmp");
		ExtensionFilter tif = new ExtensionFilter("TIF Files", "*.tif");

		chooser.getExtensionFilters().addAll(jpeg, png, bitmap, tif);
		File file = chooser.showOpenDialog(specs.getScene().getWindow());

		if (file != null) {
			BufferedImage bufferedImage = null;
			try {
				bufferedImage = ImageIO.read(file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Image image = SwingFXUtils.toFXImage(bufferedImage, null);
			imageView.setImage(image);
		}
	}

	public void newCreatedAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Item added successfully!");
		alert.showAndWait();
	}

	public void clearFields() {
		description.clear();
		category.getSelectionModel().selectFirst();
		priceDollars.clear();
		priceCents.clear();
		condition.getSelectionModel().selectFirst();
		specs.clear();
	}

}
