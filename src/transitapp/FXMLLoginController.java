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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class FXMLLoginController extends ControllerParent implements Initializable {

	@FXML
	private Button loginButton;
	@FXML
	private TextField email;
	@FXML
	private TextField password;
	

	public void backButtonPush(ActionEvent event) throws IOException {

		changeScene(event, "FXMLMenu.FXML");
	}

	public void noAccountButton(ActionEvent event) throws IOException {

		changeScene(event, "FXMLRegister.FXML");
	}
	
	public void loginButtonPush(ActionEvent event) throws IOException {
		
		System.out.println(email.getText() + ", " + password.getText());
		
		changeScene(event, "FXMLDashboard.FXML");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}
