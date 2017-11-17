package view;

import java.time.LocalDate;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class NewEmployeeTab extends AnchorPane {

	private Tab tab;
	
	private VBox mainBox;

	private HBox fNameBox;
	private HBox lNameBox;
	private HBox emailBox;
	private HBox phoneBox;
	private HBox storeLevelHBox;

	private Label newEmployeeLbl;
	private Label firstNameLbl;
	private Label lastNameLbl;
	private Label emailLbl;
	private Label phoneLbl;
	private Label storeLevelLbl;

	private TextField customerIdField;
	private TextField firstNameField;
	private TextField lastNameField;
	private TextField emailField;
	private TextField phoneField;
	private ComboBox<String> storeLevelComboBox;

	private Button createBtn;

	private LocalDate startDate;

	public NewEmployeeTab() {
		tab = new Tab("Crate new Employee");

		mainBox = new VBox(15);
		mainBox.setPadding(new Insets(10));

		fNameBox = new HBox(40);
		lNameBox = new HBox(41);
		storeLevelHBox = new HBox(38);
		emailBox = new HBox(20);
		phoneBox = new HBox(69);

		newEmployeeLbl = new Label("Create New Employee");
		firstNameLbl = new Label("First Name");
		lastNameLbl = new Label("Last Name");
		emailLbl = new Label("Email Address");
		phoneLbl = new Label("Phone");
		storeLevelLbl = new Label("Store Level");

		firstNameField = new TextField();
		lastNameField = new TextField();
		emailField = new TextField();
		phoneField = new TextField();
		storeLevelComboBox = new ComboBox<>();

		createBtn = new Button("Create");

		storeLevelComboBox.getItems().addAll("1 - Customer Service Representative", "2 - Operations Associate",
				"3 - Administrator");

		fNameBox.getChildren().addAll(firstNameLbl, firstNameField);
		lNameBox.getChildren().addAll(lastNameLbl, lastNameField);
		emailBox.getChildren().addAll(emailLbl, emailField);
		phoneBox.getChildren().addAll(phoneLbl, phoneField);
		storeLevelHBox.getChildren().addAll(storeLevelLbl, storeLevelComboBox);

		mainBox.getChildren().addAll(newEmployeeLbl, fNameBox, lNameBox, storeLevelHBox, emailBox, phoneBox, createBtn);
		tab.setContent(mainBox);

	}

	public Tab getTab() {
		return this.tab;
	}

	public VBox getMainBox() {
		return mainBox;
	}

	public void setMainBox(VBox mainBox) {
		this.mainBox = mainBox;
	}

	public HBox getfNameBox() {
		return fNameBox;
	}

	public void setfNameBox(HBox fNameBox) {
		this.fNameBox = fNameBox;
	}

	public HBox getlNameBox() {
		return lNameBox;
	}

	public void setlNameBox(HBox lNameBox) {
		this.lNameBox = lNameBox;
	}

	public HBox getEmailBox() {
		return emailBox;
	}

	public void setEmailBox(HBox emailBox) {
		this.emailBox = emailBox;
	}

	public HBox getPhoneBox() {
		return phoneBox;
	}

	public void setPhoneBox(HBox phoneBox) {
		this.phoneBox = phoneBox;
	}

	public HBox getStoreLevelHBox() {
		return storeLevelHBox;
	}

	public void setStoreLevelHBox(HBox storeLevelHBox) {
		this.storeLevelHBox = storeLevelHBox;
	}

	public Label getNewEmployeeLbl() {
		return newEmployeeLbl;
	}

	public void setNewEmployeeLbl(Label newEmployeeLbl) {
		this.newEmployeeLbl = newEmployeeLbl;
	}

	public Label getFirstNameLbl() {
		return firstNameLbl;
	}

	public void setFirstNameLbl(Label firstNameLbl) {
		this.firstNameLbl = firstNameLbl;
	}

	public Label getLastNameLbl() {
		return lastNameLbl;
	}

	public void setLastNameLbl(Label lastNameLbl) {
		this.lastNameLbl = lastNameLbl;
	}

	public Label getEmailLbl() {
		return emailLbl;
	}

	public void setEmailLbl(Label emailLbl) {
		this.emailLbl = emailLbl;
	}

	public Label getPhoneLbl() {
		return phoneLbl;
	}

	public void setPhoneLbl(Label phoneLbl) {
		this.phoneLbl = phoneLbl;
	}

	public Label getStoreLevelLbl() {
		return storeLevelLbl;
	}

	public void setStoreLevelLbl(Label storeLevelLbl) {
		this.storeLevelLbl = storeLevelLbl;
	}

	public TextField getCustomerIdField() {
		return customerIdField;
	}

	public void setCustomerIdField(TextField customerIdField) {
		this.customerIdField = customerIdField;
	}

	public TextField getFirstNameField() {
		return firstNameField;
	}

	public void setFirstNameField(TextField firstNameField) {
		this.firstNameField = firstNameField;
	}

	public TextField getLastNameField() {
		return lastNameField;
	}

	public void setLastNameField(TextField lastNameField) {
		this.lastNameField = lastNameField;
	}

	public TextField getEmailField() {
		return emailField;
	}

	public void setEmailField(TextField emailField) {
		this.emailField = emailField;
	}

	public TextField getPhoneField() {
		return phoneField;
	}

	public void setPhoneField(TextField phoneField) {
		this.phoneField = phoneField;
	}

	public ComboBox<String> getStoreLevelComboBox() {
		return storeLevelComboBox;
	}

	public void setStoreLevelComboBox(ComboBox<String> storeLevelComboBox) {
		this.storeLevelComboBox = storeLevelComboBox;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public Button getCreateBtn() {
		return createBtn;
	}

	public void setCreateBtn(Button createBtn) {
		this.createBtn = createBtn;
	}

}
