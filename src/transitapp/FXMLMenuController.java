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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class FXMLMenuController extends ControllerParent implements Initializable{

	@FXML
	private Pane pane;

	public void loginButtonPush(ActionEvent event) throws IOException {
		
		changeScene(event, "FXMLLogin.FXML");
	}
	
	public void settingsButtonPush(ActionEvent event) throws IOException {
		
		changeScene(event, "FXMLSettings.FXML");
	}
	
	public void registerButtonPush(ActionEvent event) throws IOException {
		
		changeScene(event, "FXMLRegister.FXML");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO
	}

}
