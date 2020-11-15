package transitapp;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class FXMLRegisterController extends ControllerParent implements Initializable {
	
	@FXML
	private TextField fullName;
	@FXML
	private TextField email;
	@FXML
	private TextField password;

	public void backButtonPush(ActionEvent event) throws IOException {

		changeScene(event, "FXMLMenu.FXML");
	}
	
	public void hasAccountButton(ActionEvent event) throws IOException {

		changeScene(event, "FXMLLogin.FXML");
	}
	
	public void regButtonPush(ActionEvent event) throws IOException {
		
		System.out.println(fullName.getText() + ", " + email.getText() + ", " + password.getText());
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}