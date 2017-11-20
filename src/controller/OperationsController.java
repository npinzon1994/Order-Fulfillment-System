package controller;

import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class OperationsController implements Initializable, Serializable {

	private static final long serialVersionUID = 1L;
	
	@FXML //lets java know to link to our FXML file
	private Label numberLbl;
	
	//whenever button gets clicked, this method gets called
	public void getRandom(ActionEvent event) {
		
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		
	}
	

}
